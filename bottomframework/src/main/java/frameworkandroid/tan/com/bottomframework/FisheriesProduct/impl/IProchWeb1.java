package frameworkandroid.tan.com.bottomframework.FisheriesProduct.impl;
import android.os.Handler;

import java.util.List;

import frameworkandroid.tan.com.bottomframework.entity_warehouse.Good;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Porch_info;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Proch_all;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Purchase_lists;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Proch_lists_download;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Statistics_pur;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;


public interface IProchWeb1 {
	/**
	 * 采购申请
	 * @param
	 */
	public void proch_lists(String proch_status, String start, String page_num, String proch_order_sn, final DataListener<Proch_lists_download> dataListener);
	/**
	 *
	 * @param dataListener
	 */
	public void porch_info(String prochsn, final DataListener<Porch_info> dataListener);
	/**
	 *
	 * @param
	 */
	public void proch_passed(final String prochsn, final Handler handler);
	/**
	 *
	 * @param
	 */
	public void proch_reject(final String prochsn, final String note, final Handler handler);
	/**
	 *
	 * @param dataListener
	 */
	public void proch_all(final DataListener<List<Proch_all>> dataListener);
	/**
	 *
	 * @param dataListener
	 */
	public void proch_start(String sku, final DataListener<String> dataListener);/**
	 *
	 * @param dataListener
	 */
	public void purchase_lists(final DataListener<List<Purchase_lists>> dataListener);
	/*
	 * @param dataListener
	 */
	public void purchase_info(String prochsn, final DataListener<List<Good>> dataListener);
		/*
	 * @param dataListener
	 */
	public void purchase_stop(final String prochsn, final String sku, final Handler handler);	/*
	 * @param dataListener
	 */
	public void proch_search(String name, final DataListener<List<Good>> dataListener);	/*
	 * @param dataListener
	 */
	public void statistics_pur(String beginTime, String endTime, String sku, String goods_id, String goods_type, final DataListener<Statistics_pur> dataListener);



}
