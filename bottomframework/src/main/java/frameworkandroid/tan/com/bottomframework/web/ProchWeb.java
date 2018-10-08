package frameworkandroid.tan.com.bottomframework.web;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import frameworkandroid.tan.com.bottomframework.ServiceApplication;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Good;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Porch_info;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Proch_all;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Purchase_lists;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Proch_lists_download;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Statistics_pur;
import frameworkandroid.tan.com.bottomframework.impl.IProchWeb;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;
import frameworkandroid.tan.com.bottomframework.util.HttpPatch;
import frameworkandroid.tan.com.bottomframework.util.MyLog;
import frameworkandroid.tan.com.bottomframework.util.PublicUtil;


public class ProchWeb extends BaseWeb implements IProchWeb {

	private static String OrderUrl = BaseUrl + "proch/";
	private static String OrderUrl2 = BaseUrl + "purchase/";
	private static String OrderUrl3 = BaseUrl + "statistics/";

	private static String proch_listsUrl = OrderUrl + "lists";
	private static String porch_infoUrl = OrderUrl + "info";
	private static String proch_passedUrl = OrderUrl + "passed";
	private static String proch_rejectUrl = OrderUrl + "reject";
	private static String proch_allUrl = OrderUrl + "all";
	private static String proch_startUrl = OrderUrl + "start";
	private static String purchase_listsUrl = OrderUrl2 + "lists";
	private static String purchase_infoUrl = OrderUrl2 + "info";
	private static String purchase_stopUrl = OrderUrl2 + "stop";
	private static String proch_searchUrl = OrderUrl + "search";
	private static String statistics_purUrl = OrderUrl3 + "pur";


	private Context context;
	public ProchWeb(Context context) {
		super(context);
		this.context=context;
	}


	@Override
	public void proch_lists(String proch_status, String start, String page_num, String proch_order_sn,final DataListener<Proch_lists_download> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(proch_listsUrl+"?proch_status="+proch_status+"&start="+start+"&page_num="+page_num+"&proch_order_sn="+proch_order_sn, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
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
	public void porch_info(String prochsn,final DataListener<Porch_info> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(porch_infoUrl+"?prochsn="+prochsn, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<Porch_info>() {
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
	public void proch_passed(final String prochsn, final Handler handler) {
		showProgressDialog();
		new Thread(){
			@Override
			public void run() {
				MyLog.ShowLog("参数是" + prochsn);
				String jsonParam="{\"prochsn\":\""+prochsn+"\"}";
				JSONObject resultObj = null;
				JSONObject resultObj1 = null;
				HttpClient httpClient = new DefaultHttpClient();
				HttpPatch httpPatch = new HttpPatch(proch_passedUrl);
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
	public void proch_reject(final String prochsn,final String note,final  Handler handler) {
		showProgressDialog();
		new Thread(){
			@Override
			public void run() {
				String jsonParam="{\"prochsn\":\""+prochsn+"\",\"note\":\""+note+"\"}";
				JSONObject resultObj = null;
				JSONObject resultObj1 = null;
				HttpClient httpClient = new DefaultHttpClient();
				HttpPatch httpPatch = new HttpPatch(proch_rejectUrl);
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

	@Override
	public void proch_all(final DataListener<List<Proch_all>> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(proch_allUrl, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<List<Proch_all>>() {
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
	public void proch_start(String sku,final DataListener<String> listener) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("sku",sku);
		post(proch_startUrl, params, HttpRequest.HttpMethod.POST, new IRequestListener() {
			@Override
			public void onSuccess(String json) {

				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(json);//把字符串转化成一个json对象
					if ("true".equals(jsonObject.getString("success"))) {
						Gson gson = new Gson();
						String data1=jsonObject.getString("data");
			String purchase_sn=(new JSONObject(data1)).getString("purchase_sn");
						listener.onSuccess(purchase_sn);
					} else {
						String data1=jsonObject.getString("errors");
						String data2=(new JSONObject(data1)).getString("message");
						PublicUtil.ShowToast("" + data2);
						listener.onFailed();
					}
				} catch (Exception e) {
					listener.onFailed();
					PublicUtil.ShowToast("" + e.toString());
					MyLog.ShowLog("json parse failed : " + e.toString());
					e.printStackTrace();
				}
			}

			@Override
			public void onFailed() {
				listener.onFailed();
			}
		});
	}

	@Override
	public void purchase_lists(final DataListener<List<Purchase_lists>> dataListener) {
		RequestParams params = new RequestParams();
//		params.addBodyParameter("sku",sku);
		post(purchase_listsUrl, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<List<Purchase_lists>>() {
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
	public void purchase_info(String purchase_sn,final DataListener<List<Good>> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(purchase_infoUrl+"?purchase_sn="+purchase_sn, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<List<Good>>() {
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
	public void purchase_stop(final String purchase_sn,final String sku, final Handler handler) {
		showProgressDialog();
		new Thread(){
			@Override
			public void run() {
				String jsonParam="{\"purchase_sn\":\""+purchase_sn+"\",\"sku\":"+sku+"}";
				MyLog.ShowLog("参数"+jsonParam);
				JSONObject resultObj = null;
				JSONObject resultObj1 = null;
				HttpClient httpClient = new DefaultHttpClient();
				HttpPatch httpPatch = new HttpPatch(purchase_stopUrl);
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
						//	String data=resultObj.getString("data");
						//resultObj1 = new JSONObject(data);
						//PublicUtil.ShowToast(resultObj1.getString("auth"));
						//	serviceApplication.mPrefUtil.putSetting("auth",resultObj1.getString("auth"));
						handler.sendEmptyMessage(1);
					}else {
						String errors=resultObj.getString("errors");
						resultObj1 = new JSONObject(errors);

						//如果是token出错 就跳转到登录页面
						if (resultObj1.getInt("code") == 401) {
							handler.sendEmptyMessage(2);
						}else {
							Message message=new Message();
							message.obj="" + resultObj1.getString("message");
							message.what=3;
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
	public void proch_search(String name,final DataListener<List<Good>> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(proch_searchUrl+"?name="+name, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<List<Good>>() {
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
	public void statistics_pur(String beginTime, String endTime, String sku, String goods_id, String goods_type,final DataListener<Statistics_pur> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(proch_searchUrl+"?beginTime="+beginTime+"&endTime="+endTime+"&sku="+sku+"&goods_id="+goods_id+"&goods_type="+goods_type, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<Statistics_pur>() {
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
