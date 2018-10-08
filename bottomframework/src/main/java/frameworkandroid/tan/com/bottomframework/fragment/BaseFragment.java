package frameworkandroid.tan.com.bottomframework.fragment;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.lidroid.xutils.ViewUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

import frameworkandroid.tan.com.bottomframework.Constant;
import frameworkandroid.tan.com.bottomframework.ServiceApplication;
import frameworkandroid.tan.com.bottomframework.adapter.AdapterBase;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Proch;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Proch_lists_download;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;
import frameworkandroid.tan.com.bottomframework.model.BaseModel;
import frameworkandroid.tan.com.bottomframework.util.MyLog;
import frameworkandroid.tan.com.bottomframework.util.PreferenceUtil;
import frameworkandroid.tan.com.bottomframework.util.PublicUtil;
import frameworkandroid.tan.com.bottomframework.web.ShipmentWeb;


//主页面的片段--主页
public class BaseFragment extends Fragment {
    public View view;
    public Context context;
    public PreferenceUtil mPrefUtil;
    public ServiceApplication application;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        application=ServiceApplication.getInstance();
        mPrefUtil= application.getPreferenceUtil();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 通过Class启动Activity
     *
     * @param cls
     */
    protected void startActivityByClass(Class cls)
    {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        startActivity(intent);
    }

    public  void p(String msg) {
        PublicUtil.ShowToast(msg);
    }
    public  void m(String msg) {
        MyLog.ShowLog(msg);
    }
}
