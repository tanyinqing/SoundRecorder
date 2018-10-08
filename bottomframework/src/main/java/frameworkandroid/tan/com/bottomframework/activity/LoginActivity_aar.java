package frameworkandroid.tan.com.bottomframework.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;

import com.lidroid.xutils.ViewUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import frameworkandroid.tan.com.bottomframework.R;
import frameworkandroid.tan.com.bottomframework.adapter.AdapterBase;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Proch;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.User;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;
import frameworkandroid.tan.com.bottomframework.model.BaseModel;
import frameworkandroid.tan.com.bottomframework.util.PublicUtil;
import frameworkandroid.tan.com.bottomframework.view.TopBarView;
import frameworkandroid.tan.com.bottomframework.web.UserWeb;


public class LoginActivity_aar extends BaseActivity1 implements
        TopBarView.OnTopbarLeftButtonListener, TopBarView.OnTopbarRightButtonListener{
    private MobanModel mMobanModel = null;

    private EditText editPhone;
    private EditText editPassword;
    private Button buttonLogin;
    private Button buttonForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        TopBarView topbar = (TopBarView) findViewById(R.id.loginTopBar);
        topbar.setTopbarTitle("登录");
        topbar.setOnTopbarLeftButtonListener(this);
        topbar.setOnTopbarRightButtonListener(this);
        topbar.setLeftButtonGone();

        editPhone = (EditText) findViewById(R.id.editPhone);
        editPassword = (EditText) findViewById(R.id.editPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        //buttonForget = (Button) findViewById(R.id.login_forget);

        buttonLogin.setOnClickListener(new MyViewOnclicklistener());
       // buttonForget.setOnClickListener(new MyViewOnclicklistener());

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
            if (rid == R.id.buttonLogin) {
                mMobanModel.login(editPhone.getText().toString(), editPassword.getText().toString());
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

        public MobanModel(Context mContext) {
            super(mContext);
            mDistrictAdapter = new MybanAdapter(mContext);
            mStatisWeb = new UserWeb(mContext);
        }

        public ListAdapter getDistrictAdapter() {
            return mDistrictAdapter;
        }

        /**
         * 登陆
         * @param acc
         * @param pwd
         */
        public void login(String acc,String pwd)
        {
            if(!isLegal(acc, pwd))
            {
                return;
            }
            mStatisWeb.user_login(acc, pwd, new DataListener<User>() {
                @Override
                public void onSuccess(User data) {
//                    serviceApplication.saveUser(data);
                    closeActivity();
                    Intent intent=new Intent();
                    intent.setAction("com.tyq.warehouse.activity.MainActivity");
                    startActivity(intent);
                }

                @Override
                public void onFailed() {
                }

            });
        }

        /**
         * 检测输入合法性
         * @param acc
         * @param pwd
         * @return
         */
        private boolean isLegal(String acc,String pwd)
        {

		/*
		检测手机号输入的合法性
			if(!RegexUtil.isPhone(acc))
		{
			PublicUtil.ShowToast("请输入正确的手机号！");
			return false;
		}
		 */

            if("".equals(pwd) || pwd.length() < 6)
            {
                PublicUtil.ShowToast("请输入6-12位的秘密！");
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
