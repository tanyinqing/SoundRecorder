package frameworkandroid.tan.com.bottomframework.web;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import frameworkandroid.tan.com.bottomframework.entity_warehouse.PhotoValue;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Porch_info;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Shipment_pack;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Shipment_proch_pack;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Proch_lists_download;
import frameworkandroid.tan.com.bottomframework.impl.IShipmentWeb;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;


public class ShipmentWeb extends BaseWeb implements IShipmentWeb {

	private static String OrderUrl = BaseUrl + "shipment/";

	private static String shipment_listsUrl = OrderUrl + "lists";
	private static String shipment_infoUrl = OrderUrl + "info";
	private static String shipment_proch_packUrl = OrderUrl + "proch/pack";
	private static String shipment_packUrl = OrderUrl + "pack";
	private static String shipment_mergeUrl = OrderUrl + "merge";
	private static String uploadPhotoUrl= "http://file.api.umijoy.com/upload";


	private static String shipment_scanUrl = OrderUrl + "scan";
	private Context context;

	public ShipmentWeb(Context context) {
		super(context);
		this.context=context;
	}


	@Override
	public void shipment_lists(String start, String page_num, String pur_order_sn,final DataListener<Proch_lists_download> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(shipment_listsUrl+"?start="+start+"&page_num="+page_num+"&pur_order_sn="+pur_order_sn, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<Proch_lists_download>() {
				}.getType();
				parse(json, type, dataListener);
			}

			@Override
			public void onFailed() {
				dataListener.onFailed();
			}
		});
	}

	@Override
	public void shipment_info(String prochsn,final DataListener<List<Porch_info>> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(shipment_infoUrl+"?prochsn="+prochsn, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<List<Porch_info>>() {
				}.getType();
				parse(json, type, dataListener);
			}

			@Override
			public void onFailed() {
				dataListener.onFailed();
			}
		});
	}
	@Override
	public void shipment_info1(String prochsn,final DataListener<List<Porch_info>> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(shipment_infoUrl+"?prochsn="+prochsn, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<List<Porch_info>>() {
				}.getType();
				parse(json, type, dataListener);
			}

			@Override
			public void onFailed() {
				dataListener.onFailed();
			}
		});
	}

	@Override
	public void shipment_proch_pack(String prochsn,final DataListener<List<Shipment_proch_pack>> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(shipment_proch_packUrl+"?prochsn="+prochsn, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<List<Shipment_proch_pack>>() {
				}.getType();
				parse(json, type, dataListener);
			}

			@Override
			public void onFailed() {
				dataListener.onFailed();
			}
		});
	}

	@Override
	public void shipment_pack(String qrcode, String imgs, String prochsn, String goods_info, String is_merge,final DataListener<Shipment_pack> dataListener) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("qrcode",qrcode);
		params.addBodyParameter("imgs",imgs);
		params.addBodyParameter("prochsn",prochsn);
		params.addBodyParameter("goods_info",goods_info);
		params.addBodyParameter("is_merge",is_merge);
		post(shipment_packUrl, params, HttpRequest.HttpMethod.POST, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<Shipment_pack>() {
				}.getType();
				parse(json, type, dataListener);
			}

			@Override
			public void onFailed() {
				dataListener.onFailed();
			}
		});
	}

	@Override
	public void shipment_merge(String prochsn,final DataListener<List<String>> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(shipment_mergeUrl+"?prochsn="+prochsn, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<List<String>>() {
				}.getType();
				parse(json, type, dataListener);
			}

			@Override
			public void onFailed() {
				dataListener.onFailed();
			}
		});
	}

	@Override
	public void shipment_scan(String long_code,final DataListener<String> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(shipment_scanUrl+"?long_code="+long_code, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<String>() {
				}.getType();
				parse(json, type, dataListener);
			}

			@Override
			public void onFailed() {
				dataListener.onFailed();
			}
		});
	}

	public void uploadPhoto(File photo,final DataListener<PhotoValue> dataListener) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("photo",photo,"multipart/form-data");
		post(uploadPhotoUrl, params, HttpRequest.HttpMethod.POST, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
//					MyLog.ShowLog(json);
				Type type = new TypeToken<PhotoValue>() {
				}.getType();
				parse(json, type, dataListener);
			}

			@Override
			public void onFailed() {
				dataListener.onFailed();
			}
		});
	}
}
