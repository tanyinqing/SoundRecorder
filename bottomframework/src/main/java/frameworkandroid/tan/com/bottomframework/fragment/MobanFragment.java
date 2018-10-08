package frameworkandroid.tan.com.bottomframework.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.lidroid.xutils.ViewUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import frameworkandroid.tan.com.bottomframework.Constant;
import frameworkandroid.tan.com.bottomframework.adapter.AdapterBase;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Proch;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Proch_lists_download;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;
import frameworkandroid.tan.com.bottomframework.model.BaseModel;
import frameworkandroid.tan.com.bottomframework.util.PublicUtil;
import frameworkandroid.tan.com.bottomframework.web.ShipmentWeb;


//主页面的片段--主页
public class MobanFragment extends BaseFragment {
    private View view;
//    private Context context;

    private MobanModel mMobanModel = null;
   /* @ViewInject(R.id.listProducts)
    private ListView listDistrict;
    @OnItemClick(R.id.listProducts)
    public void SelectDistrict1(AdapterView<?> parent, View view,int position, long id){
//        mMobanModel.selectDistrict(position);
        startActivityByClass(DynamicDetailActivity.class);
    }*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
//         view = inflater.inflate(R.layout.fragment_main, container, false);
        ViewUtils.inject(this, view);

        if (mMobanModel == null) {
            mMobanModel = new MobanModel(getActivity());
        }
        /*mMobanModel.requestDistrict(null);
        listDistrict.setAdapter(mMobanModel.getDistrictAdapter());*/

        return view;
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

        public void peizhiyemian(int i) {
            /*switch (i) {
                case 1:
                    // iv_acticle.setBackground(R.drawable.wifi_con_hover);
                    //   textView1.setTextColor(PublicUtil.getResColor(R.color.biaoTiLan_color));
                    main_home.setImageResource(R.drawable.home_selected);

                    record.setImageResource(R.drawable.record);

                    me.setImageResource(R.drawable.me);
                    break;
                case 2:
                    main_home.setImageResource(R.drawable.home);

                    record.setImageResource(R.drawable.record_selected);

                    me.setImageResource(R.drawable.me);
                    break;
                case 3:
                    main_home.setImageResource(R.drawable.home);

                    record.setImageResource(R.drawable.record);

                    me.setImageResource(R.drawable.me_selected);
                    break;
            }*/
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
                convertView = mInflater.inflate(frameworkandroid.tan.com.bottomframework.R.layout.item_rece_proch, null);
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
