package frameworkandroid.tan.com.bottomframework.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;

import com.lidroid.xutils.ViewUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.User;
import frameworkandroid.tan.com.bottomframework.R;
import frameworkandroid.tan.com.bottomframework.adapter.AdapterBase;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Proch;
import frameworkandroid.tan.com.bottomframework.model.BaseModel;
import frameworkandroid.tan.com.bottomframework.util.PublicUtil;
import frameworkandroid.tan.com.bottomframework.view.TopBarView;
import frameworkandroid.tan.com.bottomframework.web.UserWeb;


public class ModifyPassWordActivity extends BaseActivity1 implements
        TopBarView.OnTopbarLeftButtonListener, TopBarView.OnTopbarRightButtonListener{
    private MobanModel mMobanModel = null;


    private EditText editOld;
    private EditText editNew;
    private EditText editConfirm;
    private Button buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pass_word);
        init();
    }

    private void init() {
        TopBarView topbar = (TopBarView) findViewById(R.id.loginTopBar);
        topbar.setTopbarTitle("修改密码");
        topbar.setOnTopbarLeftButtonListener(this);
        topbar.setOnTopbarRightButtonListener(this);
        topbar.setLeftButtonGone();

        editOld = (EditText) findViewById(R.id.editOld);
        editNew = (EditText) findViewById(R.id.editNew);
        editConfirm = (EditText) findViewById(R.id.editConfirm);
        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new MyViewOnclicklistener());

        if (mMobanModel == null) {
            mMobanModel = new MobanModel(this);
        }
    }


    @Override
    public void onTopbarLeftButtonSelected() {

    }

    @Override
    public void onTopbarRightButtonSelected() {

    }
    class MyViewOnclicklistener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            final int rid = v.getId();
            if (rid == R.id.buttonConfirm) {
                mMobanModel.modifypwd(editOld.getText().toString(), editNew.getText().toString(), editConfirm.getText().toString());
            }else if(false){

            }else {

            }

        }
    }
    //这个是主导器
    private class MobanModel<T> extends BaseModel {
        private MybanAdapter mDistrictAdapter;

        private UserWeb mStatisWeb;
        private int start = 0;

        private Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        PublicUtil.ShowToast("修改密码成功");
                        closeActivity();
                        break;
                    case 2:
                        PublicUtil.ShowToast((String) msg.obj);
                        break;
                    case 3:
                        PublicUtil.ShowToast("请重新登录！");
                       startActivityByClass(LoginActivity_aar.class);
                        break;
                }
            }
        };

        public MobanModel(Context mContext) {
            super(mContext);
            mDistrictAdapter = new MybanAdapter(mContext);
            mStatisWeb = new UserWeb(mContext);
        }

        public ListAdapter getDistrictAdapter() {
            return mDistrictAdapter;
        }

        public void modifypwd(String old_pwd,String new_pwd,String confirm_pwd)
        {
            User user = serviceApplication.readUser();
            if(null == user)
            {
                PublicUtil.ShowToast("请先登录");
                return;
            }
            old_pwd = old_pwd.trim();
            new_pwd = new_pwd.trim();
            confirm_pwd = confirm_pwd.trim();
            if(!isLegal(old_pwd, new_pwd, confirm_pwd))
            {
                return;
            }

            mStatisWeb.user_updatepwd(old_pwd, new_pwd, handler);

        }

        private boolean isLegal(String old_pwd,String new_pwd,String confirm_pwd)
        {
            old_pwd = old_pwd.trim();
            new_pwd = new_pwd.trim();
            confirm_pwd = confirm_pwd.trim();
            System.out.println("new_pwd --> "+new_pwd);
            System.out.println("confirm_pwd --> "+confirm_pwd);
            if(TextUtils.isEmpty(old_pwd) || old_pwd.length() < 6)
            {
                PublicUtil.ShowToast("请输入原密码！");
                return false;
            }
            if(TextUtils.isEmpty(new_pwd) || new_pwd.length() < 6)
            {
                PublicUtil.ShowToast("请输入6-12位的新密码！");
                return false;
            }
            if(!new_pwd.equals(confirm_pwd))
            {
                PublicUtil.ShowToast("两次输入密码不一致！");
                return false;
            }
            return true;
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
