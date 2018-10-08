package frameworkandroid.tan.com.bottomframework.web;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import frameworkandroid.tan.com.bottomframework.ServiceApplication;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Location_goods;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Repertory_goods_info_download;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Repertory_lists_download;
import frameworkandroid.tan.com.bottomframework.impl.IRepertoryWeb;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;
import frameworkandroid.tan.com.bottomframework.util.HttpPatch;
import frameworkandroid.tan.com.bottomframework.util.MyLog;


public class RepertoryWeb extends BaseWeb implements IRepertoryWeb {

	private static String OrderUrl = BaseUrl + "repertory/";

	private static String repertory_listsUrl = OrderUrl + "lists";
	private static String repertory_lists_searchUrl = OrderUrl + "lists/search";
	private static String repertory_lists_waitUrl = OrderUrl + "lists/wait";
	private static String repertory_goodsUrl = OrderUrl + "goods";
	private static String repertory_goods_infoUrl = OrderUrl + "goods/info";
	private static String repertory_locationUrl = OrderUrl + "location";
	private static String retertory_goods_locationUrl = OrderUrl + "goods/location";
	private static String retertory_goods_removeUrl = OrderUrl + "goods/remove";

	private Context context;
	public RepertoryWeb(Context context) {
		super(context);
		this.context=context;
	}


	@Override
	public void repertory_lists(String start, String page_num,final DataListener<Repertory_lists_download> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(repertory_listsUrl+"?start="+start+"&page_num="+page_num, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<Repertory_lists_download>() {
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
	public void repertory_lists_search(String start, String page_num, String keywords,final DataListener<Repertory_lists_download> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(repertory_lists_searchUrl+"?start="+start+"&page_num="+page_num+"&keywords="+keywords, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<Repertory_lists_download>() {
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
	public void repertory_lists_wait(String start, String page_num,final DataListener<Repertory_lists_download> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(repertory_lists_waitUrl+"?start="+start+"&page_num="+page_num, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<Repertory_lists_download>() {
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
	public void repertory_goods(String start, String page_num, String area_id,final DataListener<Repertory_lists_download> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(repertory_goodsUrl+"?start="+start+"&page_num="+page_num+"&area_id="+area_id, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<Repertory_lists_download>() {
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
	public void repertory_goods_info(String sku_id,final DataListener<Repertory_goods_info_download> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(repertory_goods_infoUrl+"?sku_id="+sku_id, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<Repertory_goods_info_download>() {
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
	public void repertory_location(final DataListener<List<Location_goods>> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(repertory_locationUrl, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<List<Location_goods>>() {
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
	public void retertory_goods_location(final String sku_id,final String location,final Handler handler) {
		showProgressDialog();
		new Thread(){
			@Override
			public void run() {
				String jsonParam="{\"sku_id\":\""+sku_id+"\",\"location\":\""+location+"\"}";
				MyLog.ShowLog(jsonParam);
				JSONObject resultObj = null;
				JSONObject resultObj1 = null;
				HttpClient httpClient = new DefaultHttpClient();
				HttpPut httpPatch = new HttpPut(retertory_goods_locationUrl);
				httpPatch.setHeader("Content-type", "application/json");
				httpPatch.setHeader("Charset", HTTP.UTF_8);
				httpPatch.setHeader("Accept", "application/json");
				httpPatch.setHeader("Accept-Charset", HTTP.UTF_8);
				httpPatch.addHeader("token", ServiceApplication.getInstance().readSession());
				try {
					if (jsonParam != null){
						StringEntity entity = new StringEntity(jsonParam,HTTP.UTF_8);
						httpPatch.setEntity(entity);
					}
					HttpResponse response = httpClient.execute(httpPatch);
					resultObj = new JSONObject(EntityUtils.toString(response.getEntity()));
					MyLog.ShowLog(resultObj.toString());
					if ("true".equals(resultObj.getString("success"))) {
						//String data=resultObj.getString("data");
						//resultObj1 = new JSONObject(data);
						//PublicUtil.ShowToast(resultObj1.getString("auth"));
						//	serviceApplication.mPrefUtil.putSetting("auth",resultObj1.getString("auth"));
						handler.sendEmptyMessage(1);
					}else {
						String errors=resultObj.getString("errors");
						resultObj1 = new JSONObject(errors);

						//如果是token出错 就跳转到登录页面
						if (resultObj1.getInt("code") == 401) {
							handler.sendEmptyMessage(5);
						}else {
							Message message=new Message();
							message.obj="" + resultObj1.getString("message");
							message.what=2;
							handler.sendMessage(message);
						}
					}
				} catch ( JSONException | IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	public void retertory_goods_remove(final String form_location,final String to_location,final String amount,final String sku_id,final Handler handler) {
		showProgressDialog();
		new Thread(){
			@Override
			public void run() {
				String jsonParam="{\"form_location\":\""+form_location+"\",\"to_location\":\""+to_location+"\",\"amount\":\""+amount+"\",\"sku_id\":\""+sku_id+"\"}";
				MyLog.ShowLog(jsonParam);
				JSONObject resultObj = null;
				JSONObject resultObj1 = null;
				HttpClient httpClient = new DefaultHttpClient();
				HttpPatch httpPatch = new HttpPatch(retertory_goods_removeUrl);
				httpPatch.setHeader("Content-type", "application/json");
				httpPatch.setHeader("Charset", HTTP.UTF_8);
				httpPatch.setHeader("Accept", "application/json");
				httpPatch.setHeader("Accept-Charset", HTTP.UTF_8);
				httpPatch.addHeader("token", ServiceApplication.getInstance().readSession());
				try {
					if (jsonParam != null){
						StringEntity entity = new StringEntity(jsonParam,HTTP.UTF_8);
						httpPatch.setEntity(entity);
					}
					HttpResponse response = httpClient.execute(httpPatch);
					resultObj = new JSONObject(EntityUtils.toString(response.getEntity()));
					MyLog.ShowLog(resultObj.toString());
					if ("true".equals(resultObj.getString("success"))) {
						//String data=resultObj.getString("data");
						//resultObj1 = new JSONObject(data);
						//PublicUtil.ShowToast(resultObj1.getString("auth"));
						//	serviceApplication.mPrefUtil.putSetting("auth",resultObj1.getString("auth"));
						handler.sendEmptyMessage(3);
					}else {
						String errors=resultObj.getString("errors");
						resultObj1 = new JSONObject(errors);

						//如果是token出错 就跳转到登录页面
						if (resultObj1.getInt("code") == 401) {
							handler.sendEmptyMessage(5);
						}else {
							Message message=new Message();
							message.obj="" + resultObj1.getString("message");
							message.what=4;
							handler.sendMessage(message);
						}
					}
				} catch ( JSONException | IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
