package frameworkandroid.tan.com.bottomframework.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import frameworkandroid.tan.com.bottomframework.R;
import frameworkandroid.tan.com.bottomframework.adapter.AdapterBase;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Proch;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.User;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;
import frameworkandroid.tan.com.bottomframework.model.BaseModel;
import frameworkandroid.tan.com.bottomframework.util.PublicUtil;
import frameworkandroid.tan.com.bottomframework.view.TopBarView;
import frameworkandroid.tan.com.bottomframework.web.UserWeb;

/**
 * 各种控件的测试文本，为页面服务
 */
public class MyWidgetTextActivity extends BaseActivity1 implements
        TopBarView.OnTopbarLeftButtonListener, TopBarView.OnTopbarRightButtonListener {
    private MobanModel mMobanModel = null;
    private ListView list_data;
    private TextView tv_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_widget_text);
        init();
    }
    private void init() {
         tv_name = (TextView) findViewById(R.id.tv_name);

        TopBarView topbar = (TopBarView) findViewById(R.id.loginTopBar);
        topbar.setTopbarTitle("控件测试页面");
        topbar.setOnTopbarLeftButtonListener(this);
        topbar.setOnTopbarRightButtonListener(this);
        topbar.setLeftButtonGone();


        if (mMobanModel == null) {
            mMobanModel = new MobanModel(this);
        }
        onMyItemClick();
    }
//    listView的点击事件
    private void onMyItemClick() {
        list_data= (ListView) findViewById(R.id.list_data);
        List<String> list=new ArrayList<>();
        list.add("进入主页面");
        list.add("ceshi1");
        list.add("ceshi2");
        list_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (("进入主页面".equals(adapterView.getAdapter().getItem(i)))) {
                   finish();
                } else if (("ceshi1".equals(adapterView.getAdapter().getItem(i)))) {
                    p((String) (adapterView.getAdapter().getItem(i)));
                } else if (("ceshi2".equals(adapterView.getAdapter().getItem(i)))) {
                    p((String) (adapterView.getAdapter().getItem(i)));
                }
            }
        });
        // 适配listView
        list_data.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list_data, list));
    }

    @Override
    public void onTopbarLeftButtonSelected() {

    }

    @Override
    public void onTopbarRightButtonSelected() {

    }
    //这个是主导器
    private class MobanModel<T> extends BaseModel {
        private MybanAdapter mDistrictAdapter;

        private UserWeb mStatisWeb;
        private int start = 0;

        public MobanModel(Context mContext) {
            super(mContext);
            mDistrictAdapter = new MybanAdapter(mContext);
            mStatisWeb = new UserWeb(mContext);
        }

        public ListAdapter getDistrictAdapter() {
            return mDistrictAdapter;
        }





     /*   public void selectDistrict(int position) {
             Intent intent = new Intent();
            intent.setClass(ReceActivity.this, ShipmentInfoActivity.class);
            intent.putExtra("prochsn", merge_orderSn);
            intent.putExtra("is_merge", "2");
            startActivity(intent);

            }*/

    }
    private static class MybanAdapter extends AdapterBase {
        private static Context mContext;
        private static ImageLoader mImageLoader;

        public MybanAdapter(Context baseContext) {
            super(baseContext);
            this.mContext = context;

            mImageLoader = PublicUtil.getImageLoader();
        }

        @Override
        protected View getExView(int position, View convertView, ViewGroup parent) {
            //getItem是父类中的方法 从父类中的方法里得到数据 在这里运用
            Proch address = (Proch) getItem(position);
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_rece_proch, null);
                ViewUtils.inject(holder, convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.update(address);
            return convertView;
        }

        private static class ViewHolder {

           /* @ViewInject(R.id.iv_stock)
            private ImageView iv_stock;       */

            Proch address;

            void update(Proch address) {
                this.address = address;
                // tv_data.setText(address.getSeller());
            }

           /* @OnClick(R.id.ck_selecked)
            void buttonDelClick(View v) {
            }*/
        }
    }
}
