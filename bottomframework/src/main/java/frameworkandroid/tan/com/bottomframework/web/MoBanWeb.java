package frameworkandroid.tan.com.bottomframework.web;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import frameworkandroid.tan.com.bottomframework.Constant;
import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.NewsDetailModel;
import frameworkandroid.tan.com.bottomframework.ServiceApplication;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Good;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Porch_info;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Proch_all;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.Purchase_lists;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Proch_lists_download;
import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Statistics_pur;
import frameworkandroid.tan.com.bottomframework.impl.IMoBanWeb;
import frameworkandroid.tan.com.bottomframework.impl.IProchWeb;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;
import frameworkandroid.tan.com.bottomframework.util.AssetsUtils;
import frameworkandroid.tan.com.bottomframework.util.HttpPatch;
import frameworkandroid.tan.com.bottomframework.util.MyLog;
import frameworkandroid.tan.com.bottomframework.util.PublicUtil;
import frameworkandroid.tan.com.bottomframework.util.utils_log.LogTxt_json;


public class MoBanWeb extends BaseWeb  implements IMoBanWeb {
	private static String OrderUrl = BaseUrl + "repertory/";
	private static String repertory_listsUrl = OrderUrl + "lists";


	private Context context;
	public MoBanWeb(Context context) {
		super(context);
		this.context=context;
	}


	@Override
	public void proch_lists(String proch_order_sn,final DataListener<Proch_lists_download> dataListener) {
		RequestParams params = new RequestParams();
		//params.addBodyParameter("order_id",order_id);
		post(Constant.PublicWebSite, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//利用模拟数据
				//	MyLog.ShowLog(json);
				json= AssetsUtils.getAssetsData(context, "FisheriesProduct/user/news_detail3.txt");
				// 打印和收集测试数据
				LogTxt_json.writeLog(Constant.PublicWebSite + "\r\n" + "数据" + "\r\n" + json, "proch_lists",Constant.program_name);
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
}
