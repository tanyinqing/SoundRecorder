package frameworkandroid.tan.com.bottomframework.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;

import frameworkandroid.tan.com.bottomframework.ServiceApplication;
import frameworkandroid.tan.com.bottomframework.util.MyLog;
import frameworkandroid.tan.com.bottomframework.util.PreferenceUtil;
import frameworkandroid.tan.com.bottomframework.util.PublicUtil;
import frameworkandroid.tan.com.bottomframework.util_Jar.Utils;


/**
 * Created by Administrator on 2017/11/13 0013.
 */
public abstract class BaseActivity extends FragmentActivity {
    protected  static Context context;
    protected ServiceApplication application = null;
    protected Bundle savedInstanceState = null;
    public PreferenceUtil mPrefUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        application = ServiceApplication.getInstance();
        application.register(this);
        this.savedInstanceState=savedInstanceState;
        mPrefUtil= ServiceApplication.getInstance().getPreferenceUtil();
        context = this;
//        对工具类进行初始化
        Utils.init(this);
        //这是进行注解绑定的初始化  xUtils-2.6.14.jar中的方法
        ViewUtils.inject(this);
        // riZhiDB=new RiZhiDB(this);

        createView();

        setListeners();

        initDatas();
    }
    /**
     * 通过Class启动Activity
     *
     * @param cls
     */
    protected void startActivityByClass(Class cls)
    {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

    /**
     * 设置事件监听
     */
    abstract protected void setListeners();

    /**
     * 初始化设置
     */
    abstract protected void initDatas();

    /**
     * 初始化
     */
    abstract void createView();

    @Override
    protected void onStart()
    {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void p(String title,String msg) {
        if (true) {
            new AlertDialog.Builder(BaseActivity.this).setTitle(title).setMessage(msg).show();
        }
    }

    public  void tt(String msg) {
        if (true) {
            Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }
    public  void p(String msg) {
        PublicUtil.ShowToast(msg);
    }
    public  void m(String msg) {
        MyLog.ShowLog(msg);
    }
}
