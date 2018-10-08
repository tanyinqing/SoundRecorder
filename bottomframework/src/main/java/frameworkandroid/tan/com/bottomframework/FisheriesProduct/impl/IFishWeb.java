package frameworkandroid.tan.com.bottomframework.FisheriesProduct.impl;

import java.util.List;

import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.NewsEventList;
import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.NewsDetailModel;
import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.User;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;


public interface IFishWeb {
	/**
	 *
	 * @param dataListener
	 */
	public void newsEvent(String id, final DataListener<NewsDetailModel> dataListener);
	/**
	 * 登录
	 * @param dataListener
	 */
	public void login(String name, String password,final DataListener<User> dataListener);
	/**
	 * 新闻列表
	 * @param dataListener
	 */
	public void newsEventList(String defaultStart, String defaultLimit,final DataListener<List<NewsEventList>> dataListener);
}
