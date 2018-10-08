package frameworkandroid.tan.com.bottomframework;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


import java.util.ArrayList;
import java.util.List;

import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.User;
import frameworkandroid.tan.com.bottomframework.exception.CrashHandler;
import frameworkandroid.tan.com.bottomframework.util.PreferenceUtil;
import frameworkandroid.tan.com.bottomframework.util_Jar.Utils;

//@Modules({"app", "useframework"})
public class ServiceApplication extends Application {

	// 登录用户
	public User mUser = null;

	public boolean userPasswordRemember = false;
	public boolean ad = false;
	public boolean isFirstStart = true;
	private static ServiceApplication instance;
	private List<Activity> mActivityList;
	private PreferenceUtil mPrefUtil;  //用于配制信息的业务类  继成了各种方法

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		//对登录信息进行初始化
		initLoginParams();
		//这个是对长连接的信息进行初始化
		initIMConfig();

		//将捕捉未知异常的类初始化
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
		//对缓存数据的存储
		mPrefUtil = new PreferenceUtil(this, Constant.prefName, Context.MODE_PRIVATE);
		mActivityList = new ArrayList<Activity>();

		// 对工具包的内容允许调用
		Utils.init(this);

		//        打开是否是测试版本  在全局变量中初始化
		Constant.CeShiBan_true=mPrefUtil.getBoolean("CeShiBan_true");
	}
	public static ServiceApplication getInstance() {
		return instance;
	}
	public  PreferenceUtil getPreferenceUtil() {
		return mPrefUtil;
	}

	/**
	 *  保存和读取session信息
	 * @param
	 */
	public void saveSession(String session)
	{
		mPrefUtil.putSetting(Constant.session_pref, session);
	}

	public String readSession()
	{
		return mPrefUtil.getStrSetting(Constant.session_pref);
	}
	/**
	 * 保存用户信息
	 * @param u
	 */
	public void saveUser(User u)
	{
		mPrefUtil.putSetting(Constant.user_pref, u);
		if (u!= null) {
			mPrefUtil.putSetting(Constant.token,u.getToken());
		/*	mPrefUtil.putSetting("name",u.getName());*/
		}
	}
	/**
	 * 读取用户信息
	 * @return
	 */
	public User readUser()
	{
		User user = (User) mPrefUtil.readObject(Constant.user_pref);
		return (User) mPrefUtil.readObject(Constant.user_pref);
	}
	/**
	 * 上次登录参数
	 */
	private void initLoginParams() {
		/*SharedPreferences preferences = getSharedPreferences(
				AbAppConfig.SHARED_PATH, Context.MODE_PRIVATE);
		String userName = preferences.getString(Constant.USERNAMECOOKIE, null);
		String userPwd = preferences.getString(Constant.USERPASSWORDCOOKIE,
				null);
		Boolean userPwdRemember = preferences.getBoolean(
				Constant.USERPASSWORDREMEMBERCOOKIE, false);
		if (userName != null) {
			mUser = new User_andbass();
			mUser.setUserName(userName);
			mUser.setPassword(userPwd);
			userPasswordRemember = userPwdRemember;
		}*/
	}

	//记忆常规登录参数
	/*public void updateLoginParams(User_andbass FisheriesProduct.user) {
		mUser = FisheriesProduct.user;
		if (userPasswordRemember) {
			Editor editor = mSharedPreferences.edit();
			editor.putString(Constant.USERNAMECOOKIE, FisheriesProduct.user.getUserName());
			editor.putString(Constant.USERPASSWORDCOOKIE, FisheriesProduct.user.getPassword());
			editor.putBoolean(Constant.ISFIRSTSTART, false);
			editor.commit();
		} else {
			Editor editor = mSharedPreferences.edit();
			editor.putBoolean(Constant.ISFIRSTSTART, false);
			editor.commit();
		}
		isFirstStart = false;
	}*/

/*	*//**
	 * 清空上次登录参数
	 *//*
	public void clearLoginParams() {
		Editor editor = mSharedPreferences.edit();
		editor.clear();
		editor.commit();
		mUser = null;
	}*/

	/**
	 * IM配置  这个是对长连接的信息进行初始化
	 */
	public void initIMConfig() {
	 /*   IMConfig mIMConfig = new IMConfig();

	    mIMConfig.setXmppHost(mSharedPreferences.getString(
				IMConstant.XMPP_HOST,
				getResources().getString(R.string.xmpp_host)));

	    mIMConfig.setXmppPort(mSharedPreferences.getInt(
				IMConstant.XMPP_PORT,
				getResources().getInteger(R.integer.xmpp_port)));

	    mIMConfig.setXmppServiceName(mSharedPreferences.getString(
				IMConstant.XMPP_SEIVICE_NAME,
				getResources().getString(R.string.xmpp_service_name)));

	    mIMConfig.setNovisible(mSharedPreferences.getBoolean(
				IMConstant.IS_NOVISIBLE,
				getResources().getBoolean(R.bool.is_novisible)));
		
		IMUtil.setIMConfig(this.getApplicationContext(),mIMConfig);*/
		
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	/**
	 * 注册和退出Activity
	 * @param
	 */
	public void register(Activity activity)
	{
		mActivityList.add(activity);
	}

	public void exit()
	{
		try {
			for (Activity activity : mActivityList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

}
