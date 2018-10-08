package frameworkandroid.tan.com.bottomframework.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.User;
import frameworkandroid.tan.com.bottomframework.ServiceApplication;
import frameworkandroid.tan.com.bottomframework.util.PreferenceUtil;


public class BaseModel {

    protected Context mContext;

   protected ServiceApplication serviceApplication;//全局 成员变量 成员方法
    public PreferenceUtil mPrefUtil;
    //protected RiZhiDB riZhiDB;

    public BaseModel(Context mContext)
    {
        this.mContext = mContext;
        serviceApplication = ServiceApplication.getInstance();
        mPrefUtil= ServiceApplication.getInstance().getPreferenceUtil();
        //riZhiDB=new RiZhiDB(mContext);
    }


    public boolean isLogin()//判断是否登录 登录返回true 未登录返回false
    {
        User user = serviceApplication.readUser();
        if(null != user)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    protected User getUser()//获取用户
    {
        return serviceApplication.readUser();
    }

    /**
     * 关闭当前activity
     */
    protected void closeActivity()
    {
        ((Activity)mContext).finish();
    }

    /**
     * 跳转到其他activity
     * @param cls
     */
    protected void startAcitityByClass(Class cls)
    {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        mContext.startActivity(intent);
    }


}