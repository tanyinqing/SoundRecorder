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

import java.io.IOException;
import java.lang.reflect.Type;

import frameworkandroid.tan.com.bottomframework.ServiceApplication;
import frameworkandroid.tan.com.bottomframework.entity_warehouse.User;
import frameworkandroid.tan.com.bottomframework.impl.IUserWeb;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;
import frameworkandroid.tan.com.bottomframework.util.HttpPatch;
import frameworkandroid.tan.com.bottomframework.util.MyLog;
import frameworkandroid.tan.com.bottomframework.util.PublicUtil;


/**
 * 用户相关请求
 * 
 * @author 志强
 * 
 */
public class UserWeb extends BaseWeb implements IUserWeb {

	private static String UserUrl = BaseUrl + "/FisheriesProduct/user/";

	private static String user_loginUrl = UserUrl + "login";
	private static String user_updatepwdUrl = UserUrl + "updatepwd";
	private static String user_logoutUrl = UserUrl + "logout";

	private Context context;

	public UserWeb(Context context) {
		super(context);
		this.context=context;
	}


	@Override
	public void user_login(String name, String password,final DataListener<User> dataListener) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("name",name);
		params.addBodyParameter("password",password);
		post(user_loginUrl, params, HttpRequest.HttpMethod.POST, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
				//	MyLog.ShowLog(json);
				Type type = new TypeToken<User>() {
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
	public void user_updatepwd(final String oldPassword,final String newPassword,final Handler handler) {
		showProgressDialog();
		new Thread(){
			@Override
			public void run() {
				String jsonParam="{\"oldPassword\":\""+oldPassword+"\",\"newPassword\":\""+newPassword+"\"}";
				JSONObject resultObj = null;
				JSONObject resultObj1 = null;
				HttpClient httpClient = new DefaultHttpClient();
				HttpPatch httpPatch = new HttpPatch(user_updatepwdUrl);
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
							handler.sendEmptyMessage(3);
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
	public void user_logout(final DataListener listener) {
		RequestParams params = new RequestParams();
		/*params.addBodyParameter("name",name);
		params.addBodyParameter("password",password);*/
		post(user_logoutUrl, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
			@Override
			public void onSuccess(String json) {
					MyLog.ShowLog(json);
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(json);
					if ("true".equals(jsonObject.getString("success"))) {
						listener.onSuccess();
					} else {
						PublicUtil.ShowToast("" + jsonObject.getString("msg"));
						listener.onFailed();
					}
				} catch (Exception e) {
					listener.onFailed();
					e.printStackTrace();
				}
			}

			@Override
			public void onFailed() {
				listener.onFailed();
			}
		});
	}
}
