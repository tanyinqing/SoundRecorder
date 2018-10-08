package frameworkandroid.tan.com.bottomframework.impl;
import android.os.Handler;

import java.util.List;

import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.User;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Porch_info;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Shipment_pack;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Shipment_proch_pack;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Proch_lists_download;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;


public interface IShipmentWeb {
	/**
	 * 待发货清单
	 * @param
	 */
	public void shipment_lists(String start, String page_num, String pur_order_sn, final DataListener<Proch_lists_download> dataListener);
	/**
	 *
	 * @param dataListener
	 */
	public void shipment_info(String prochsn, final DataListener<List<Porch_info>> dataListener);
	/**
	 *
	 * @param dataListener
	 */
	public void shipment_info1(String prochsn, final DataListener<List<Porch_info>> dataListener);
	/**
	 *
	 * @param
	 */
	public void shipment_proch_pack(String prochsn, final DataListener<List<Shipment_proch_pack>> dataListener);
	/**
	 *完成扫码包装
	 * @param
	 */
	public void shipment_pack(String qrcode, String imgs, String prochsn, String goods_info, String is_merge, final DataListener<Shipment_pack> dataListener);
	/**
	 *已弃用）获取可合并的采购单
	 * @param dataListener
	 */
	public void shipment_merge(String prochsn, final DataListener<List<String>> dataListener);
	/**
	 *
	 * @param dataListener
	 */
	public void shipment_scan(String long_code, final DataListener<String> dataListener);

}
