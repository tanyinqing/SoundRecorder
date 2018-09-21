package com.tyq.soundrecorder.activities;

import android.content.Context;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tyq.soundrecorder.R;

import frameworkandroid.tan.com.bottomframework.Constant;
import frameworkandroid.tan.com.bottomframework.ServiceApplication;
import frameworkandroid.tan.com.bottomframework.activity.DevelopActivity;
import frameworkandroid.tan.com.bottomframework.activity.TitleBarActivity;
import frameworkandroid.tan.com.bottomframework.adapter.AdapterBase;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Proch;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Proch_lists_download;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;
import frameworkandroid.tan.com.bottomframework.model.BaseModel;
import frameworkandroid.tan.com.bottomframework.util.DabaCopy;
import frameworkandroid.tan.com.bottomframework.util.PublicUtil;
import frameworkandroid.tan.com.bottomframework.web.ShipmentWeb;

@ContentView(R.layout.activity_splash)
public class SplashActivity extends TitleBarActivity {

    private MobanModel mMobanModel = null;

    @ViewInject(R.id.btn_click)
    private Button btn_click;
    /*@OnClick(R.id.btn_click)
    void btn_click(View v) {
        startActivityByClass(MainActivity.class);
    }*/

    void Comment_block(){

    /*@ViewInject(R.id.re_adopt)
    private LinearLayout mPullRefreshView;
    @OnClick(R.id.re_adopt)
    void Click_Re_adopt(View v) {
    }*/

    /*  @ViewInject(R.id.listProducts)
      private ListView listDistrict;*/

    /*    @OnItemClick(R.id.listProducts)
      public void SelectDistrict1(AdapterView<?> parent, View view,int position, long id){
          mMobanModel.selectDistrict(position);
      }*/

    /* @ViewInject(R.id.mPullRefreshView)
     private AbPullToRefreshView mPullRefreshView;*/
    }
    @Override
    protected void initDatas() {
        setTitle("demo");
        setButtonRight("", 1);
//        setButtonLeft(R.drawable.icon_back);

        if (mMobanModel == null) {
            mMobanModel = new MobanModel(this);
        }
//        startActivityByClass(MainActivity.class);
        btn_click.setOnClickListener(new View.OnClickListener() {
            //需要监听几次点击事件数组的长度就为几
            //如果要监听双击事件则数组长度为2，如果要监听3次连续点击事件则数组长度为3...
            long[] mHints = new long[2];//初始全部为0

            @Override
            public void onClick(View v) {
                //将mHints数组内的所有元素左移一个位置
                System.arraycopy(mHints, 1, mHints, 0,
                        mHints.length - 1);
                //获得当前系统已经启动的时间
                mHints[mHints.length - 1] =
                        SystemClock.uptimeMillis();
//                半秒钟之内
                if (SystemClock.uptimeMillis() - mHints[0] <= 500) {
                    //跳转到自定义的开发者页面
//                      startActivityByClass(Develope.class);


//                        startActivityByClass(MyWidgetTextActivity.class);
                    // 默认是false
                    if (Constant.CeShiBan_true) {
                        /*Constant.CeShiBan_true =false;
                        ServiceApplication.getInstance().getPreferenceUtil().putSetting("CeShiBan_true", false);*/
//                        Toast.makeText(SplashActivity.this, "正式版打开", Toast.LENGTH_SHORT).show();
                        finish();
                    }else  {
                       /* Constant.CeShiBan_true =true;
                        ServiceApplication.getInstance().getPreferenceUtil().putSetting("CeShiBan_true", true);*/
                        startActivityByClass(MainActivity.class);
//                        Toast.makeText(SplashActivity.this, "测试版打开", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

//        mMobanModel.requestDistrict(null);
//        listDistrict.setAdapter(mMobanModel.getDistrictAdapter());
    }
    @Override
    protected void LeftButtonClicked() {
        finish();
    }
    @Override
    protected void RightButtonClicked() {

    }
    @Override
    protected void setListeners() {
        //调动数据 连接网络 初始化数据  sort_type是排列方式是升序还是降序  order_type是排序条件
      /*  mPullRefreshView.setOnHeaderRefreshListener(new AbPullToRefreshView.OnHeaderRefreshListener() {

            @Override
            public void onHeaderRefresh(final AbPullToRefreshView view) {
                view.onHeaderRefreshFinish();//取消上面的进度条
                mMobanModel.requestDistrict(new DataListener() {
                    @Override
                    public void onSuccess() {
                        //网络访问成功了 结束上面的进度条
                        view.onHeaderRefreshFinish();
                    }
                });
            }
        });


        mPullRefreshView.setOnFooterLoadListener(new AbPullToRefreshView.OnFooterLoadListener() {

            @Override
            public void onFooterLoad(final AbPullToRefreshView view) {


                mMobanModel.loadMoreGoods( new DataListener() {
                    @Override
                    public void onSuccess() {
                        view.onFooterLoadFinish();
                    }
                });

            }
        });*/
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    //这个是主导器
    private class MobanModel<T> extends BaseModel {
        private MybanAdapter mDistrictAdapter;

        private ShipmentWeb mStatisWeb;
        private int start = 0;

        public MobanModel(Context mContext) {
            super(mContext);
            mDistrictAdapter = new MybanAdapter(mContext);
            mStatisWeb = new ShipmentWeb(mContext);
        }

        public ListAdapter getDistrictAdapter() {
            return mDistrictAdapter;
        }

        public void requestDistrict(final DataListener dataListener) {

          /*  String json = AssetsUtils.getAssetsData(mContext, "FisheriesProduct/user/BaseDistinctionActivity.txt");
            Type type = new TypeToken<List<Entity6>>() {
            }.getType();
            List<Entity6>  entitys = (List<Entity6>) JsonUtil.convertJsonToObject1(json, type);
            mDistrictAdapter.appendToListAndClear(entitys);*/

            mStatisWeb.shipment_lists(Constant.DEFAULT_START, Constant.DEFAULT_LIMIT, "", new DataListener<Proch_lists_download>() {


                @Override
                public void onSuccess(Proch_lists_download data) {

                    if (null != data) {

                        mDistrictAdapter.appendToListAndClear(data.getList());
                        if (data != null) {
                            start = mDistrictAdapter.getCount();
                        }
                        //如果接口不为0，就回调
                        if (dataListener != null) {
                            dataListener.onSuccess();
                        }
                        if (data.getList().size() == 0) {
                            PublicUtil.ShowToast("没有相关数据！");
                        }
                    }
                }

                @Override
                public void onFailed() {

                }
            });

        }

        public void loadMoreGoods(final DataListener dataListener) {
            mStatisWeb.shipment_lists("" + start, Constant.DEFAULT_LIMIT, "", new DataListener<Proch_lists_download>() {
                @Override
                public void onSuccess(Proch_lists_download data) {

                    if (null != data) {
                        mDistrictAdapter.appendToList(data.getList());
                        if (data != null) {
                            start = mDistrictAdapter.getCount();
                        }
                        //如果接口不为0，就回调
                        if (dataListener != null) {
                            dataListener.onSuccess();
                        }
                        if (data.getList().size() == 0) {
                            PublicUtil.ShowToast("数据已经全部加载！");
                        }
                    }
                }

                @Override
                public void onFailed() {

                }
            });

        }

     /*   public void selectDistrict(int position) {
             Intent intent = new Intent();
            intent.setClass(ReceActivity.this, ShipmentInfoActivity.class);
            intent.putExtra("prochsn", merge_orderSn);
            intent.putExtra("is_merge", "2");
            startActivity(intent);

            }*/

    }
    //这个是适配器
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
//            convertView = mInflater.inflate(R.layout.item_rece_proch, null);
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
            private TextView iv_stock;       */

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
//返回的数据
// {"success":true,"errors":[],"data":{"goods_name":"\u6843\u674e\u9187\u719f\u5168\u9ea6\u5207\u7247\u9762\u5305 130g","sku_id":"68","location":[{"location_id":8,"location_name":"L-S-01-01","amount":80,"unit":"\u888b"},{"location_id":2,"location_name":"T-S-120","amount":50,"unit":"\u888b"},{"location_id":5,"location_name":"\u96f6\u98df\u4e00\u533a","amount":50,"unit":"\u888b"},{"location_id":9,"location_name":"\u6d4b\u8bd5\u5e93\u4f4d","amount":20,"unit":"\u888b"},{"location_id":3,"location_name":"\u996e\u6599\u533a","amount":200,"unit":"\u888b"}]}}
