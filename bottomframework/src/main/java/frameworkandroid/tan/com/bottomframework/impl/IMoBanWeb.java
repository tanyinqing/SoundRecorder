package frameworkandroid.tan.com.bottomframework.impl;
import android.os.Handler;

import java.util.List;

import frameworkandroid.tan.com.bottomframework.entity_warehouse.Good;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Porch_info;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Proch_all;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Purchase_lists;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Proch_lists_download;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Statistics_pur;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;


public interface IMoBanWeb {
	/**
	 * 采购申请
	 * @param
	 */
	public void proch_lists(String proch_order_sn, final DataListener<Proch_lists_download> dataListener);


}
