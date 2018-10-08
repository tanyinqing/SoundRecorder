package frameworkandroid.tan.com.bottomframework.model;

import android.content.Context;
import android.content.Intent;

import frameworkandroid.tan.com.bottomframework.util.PublicUtil;
import frameworkandroid.tan.com.bottomframework.web.ProchWeb;


public class LoginModel extends BaseModel{

	private ProchWeb mUserWeb;

	public LoginModel(Context mContext) {
		super(mContext);
		mUserWeb = new ProchWeb(mContext);
	}
	
	
	/**
	 * 登陆
	 * @param acc
	 * @param pwd
	 */
	public void login(String acc,String pwd,String imei)
	{
		if(!isLegal(acc, pwd))
		{
			return;
		}
		/*mUserWeb.login(acc, pwd,imei, new DataListener<User>(){
			@Override
			public void onSuccess(User data) {
				serviceApplication.saveUser(data);
				//隐式调用
				Intent intent=new Intent("tyq.com.worktracking.activity.MainActivity");
				mContext.startActivity(intent);
				closeActivity();
			}

			@Override
			public void onFailed() {
			}
			
		});*/
	}
	
	/**
	 * 跳转到注册界面
	 */
	public void gotoRegister()
	{
		//startAcitityByClass(RegisterAcitivty.class);
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

}
