package frameworkandroid.tan.com.bottomframework.impl;

import android.os.Handler;

import java.util.List;

import frameworkandroid.tan.com.bottomframework.entity_warehouse.User;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;


public interface IUserWeb {
	

	
	/**
	 * 登陆
	 * @param phoneNum
	 * @param password
	 * @param dataListener
	 */
	public void user_login(String phoneNum, String password,
						   final DataListener<User> dataListener);
	

	/**
	 * 修改密码
	 * @param
	 * @param oldPassword
	 * @param newPassword
	 * @param
	 */
	public void user_updatepwd(String oldPassword, String newPassword, final Handler handler);



	/**
	 * 添加评论
	 * @param
	 * @param
	 * @param dataListener
	 */
	public void user_logout(final DataListener dataListener);
}
