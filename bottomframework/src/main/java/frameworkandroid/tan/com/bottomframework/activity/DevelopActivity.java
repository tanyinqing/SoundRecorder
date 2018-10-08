package frameworkandroid.tan.com.bottomframework.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import frameworkandroid.tan.com.bottomframework.Constant;
import frameworkandroid.tan.com.bottomframework.R;
import frameworkandroid.tan.com.bottomframework.ServiceApplication;
import frameworkandroid.tan.com.bottomframework.db.DevelopDB;
import frameworkandroid.tan.com.bottomframework.model.BaseModel;
import frameworkandroid.tan.com.bottomframework.service.AutoCleanService;
import frameworkandroid.tan.com.bottomframework.util.utils_log.LogTxt;
import frameworkandroid.tan.com.bottomframework.util.PublicUtil;
import frameworkandroid.tan.com.bottomframework.view.TopBarView;


public class DevelopActivity extends BaseActivity1 implements
        TopBarView.OnTopbarLeftButtonListener, TopBarView.OnTopbarRightButtonListener {
    private DevelopDB developDB;
    private DevelopModel developModel;
    private TextView tv_information;
    private ListView list_data;
    private List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_develop);
        init();
        initViews();
        if (developModel == null) {
            developModel = new DevelopModel(this);
        }
        developDB = new DevelopDB(this);
        tv_information = (TextView) findViewById(R.id.tv_information);
        onMyItemClick();
    }

    private void initViews() {
        views=new ArrayList<>();
        Button btn_word_delete= (Button) findViewById(R.id.btn_word_delete);
        Button btn_weightText= (Button) findViewById(R.id.btn_weightText);
        views.add(btn_word_delete);
        views.add(btn_weightText);
    }

    private void onMyItemClick() {

        list_data= (ListView) findViewById(R.id.list_data);
        List<String> list=new ArrayList<>();
        list.add("是否进入控件页面");
        list.add("调试录音没有正常关闭");
        list.add("ceshi2");
        list_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              // 判断点击的是那个选项  通过点击的内容进行判断
                if (("进入主页面".equals(adapterView.getAdapter().getItem(i)))) {
                   finish();
                }else if (("调试录音没有正常关闭".equals(adapterView.getAdapter().getItem(i)))) {
                    if (Constant.CeShiBan_true) {
                        Constant.CeShiBan_true =false;
                        ServiceApplication.getInstance().getPreferenceUtil().putSetting("CeShiBan_true", false);
                        Toast.makeText(DevelopActivity.this, "设置为false", Toast.LENGTH_SHORT).show();
                       /* finish();*/
                    }else  {
                        Constant.CeShiBan_true =true;
                        ServiceApplication.getInstance().getPreferenceUtil().putSetting("CeShiBan_true", true);
                       /* startActivityByClass(MainActivity.class);*/
                        Toast.makeText(DevelopActivity.this, "设置为true", Toast.LENGTH_SHORT).show();
                    }
                }else if (("是否进入控件页面".equals(adapterView.getAdapter().getItem(i)))) {
                    if (mPrefUtil.getBoolean(Constant.isMyWidgetTextActivity)) {
                        mPrefUtil.putSetting(Constant.isMyWidgetTextActivity, false);
                        tv_information.setText("不进入控件页面");
                    } else {
                        mPrefUtil.putSetting(Constant.isMyWidgetTextActivity, true);
                        tv_information.setText("进入控件页面");
                    }
                } else if (("ceshi1".equals(adapterView.getAdapter().getItem(i)))) {
                    for (View view1 : views) {
                        if (view1.getId() == R.id.btn_weightText) {
                            view1.setVisibility(View.VISIBLE);
                        } else {
                            view1.setVisibility(View.GONE);
                        }
                    }
                } else if (("ceshi2".equals(adapterView.getAdapter().getItem(i)))) {
                    for (View view1 : views) {
                        if (view1.getId() == R.id.btn_word_delete) {
                            view1.setVisibility(View.VISIBLE);
                        } else {
                            view1.setVisibility(View.GONE);
                        }
                    }
                }
            }
        });

        list_data.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list_data, list));

        if ( mPrefUtil.getBoolean(Constant.isMyWidgetTextActivity)) {
            tv_information.setText("进入控件页面");
        }else {
            tv_information.setText("不进入控件页面");
        }
    }

    private void init() {
        TopBarView topbar = (TopBarView) findViewById(R.id.loginTopBar);
        topbar.setTopbarTitle("开发者调试");
        topbar.setOnTopbarLeftButtonListener(this);
        topbar.setOnTopbarRightButtonListener(this);
        topbar.setLeftButtonGone();
    }

    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_word_delete) {
           /* developDB.delete_work();
            developModel.delete_file();
            PublicUtil.ShowToast("work表清除成功");*/
        } else if (i == R.id.btn_weightText) {
           startActivityByClass(MyWidgetTextActivity.class);
        }else if (i == R.id.kill_process) {
            startService(new Intent(this, AutoCleanService.class));
            PublicUtil.ShowToast("清除进程的服务启动成功");
        } else if (i == R.id.delete_db) {
            if (developDB.delete_db(this)) {
                PublicUtil.ShowToast("数据库删除成功");
            }
        } else if (i == R.id.cache_data) {
            tv_information.setText(ServiceApplication.getInstance().getPreferenceUtil().getAllDate());
            PublicUtil.ShowToast("缓存数据显示完毕");
        }
    }

    @Override
    public void onTopbarLeftButtonSelected() {

    }

    @Override
    public void onTopbarRightButtonSelected() {

    }

    private class DevelopModel<T> extends BaseModel {

        public DevelopModel(Context mContext) {
            super(mContext);
        }

        public void delete_file() {
            File sdCard = Environment.getExternalStorageDirectory();
            //这种是目录的表达方式 文件的表达方式是不一样的
            File file = new File(sdCard.getAbsolutePath() + "/" + "工作跟踪");
            if (file.exists()) {
                LogTxt.delete(file);
            }
        }

    }
}
