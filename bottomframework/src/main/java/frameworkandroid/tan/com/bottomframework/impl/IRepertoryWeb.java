package frameworkandroid.tan.com.bottomframework.impl;
import android.os.Handler;

import java.util.List;

import frameworkandroid.tan.com.bottomframework.entity_warehouse.Location_goods;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Repertory_goods_info_download;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Repertory_lists_download;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;


public interface IRepertoryWeb {
	/**
	 * 商品库存
	 * @param
	 */
	public void repertory_lists(String start, String page_num, final DataListener<Repertory_lists_download> dataListener);
	/**
	 *库存商品搜索
	 * @param dataListener
	 */
	public void repertory_lists_search(String start, String page_num, String keywords, final DataListener<Repertory_lists_download> dataListener);

	/**
	 *
	 * @param
	 */
	public void repertory_lists_wait(String start, String page_num, final DataListener<Repertory_lists_download> dataListener);

	/**
	 *
	 * @param
	 */
	public void repertory_goods(String start, String page_num, String area_id, final DataListener<Repertory_lists_download> dataListener);

	/**
	 *
	 * @param dataListener
	 */
	public void repertory_goods_info(String sku_id, final DataListener<Repertory_goods_info_download> dataListener);
	/**
	 *
	 * @param dataListener
	 */
	public void repertory_location(final DataListener<List<Location_goods>> dataListener);
	/**
	 *
	 * @param
	 */
	public void retertory_goods_location(String sku_id, String location, final Handler handler);

	/*
	 * @param dataListener
	 */
	public void retertory_goods_remove(String form_location, String to_location, String amount, String sku_id, final Handler handler);


}
