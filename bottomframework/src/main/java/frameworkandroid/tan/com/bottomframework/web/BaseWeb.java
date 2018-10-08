package frameworkandroid.tan.com.bottomframework.web;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import frameworkandroid.tan.com.bottomframework.Constant;
import frameworkandroid.tan.com.bottomframework.Constant_secret;
import frameworkandroid.tan.com.bottomframework.ServiceApplication;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;
import frameworkandroid.tan.com.bottomframework.util.utils_log.LogTxt;
import frameworkandroid.tan.com.bottomframework.util.MyLog;
import frameworkandroid.tan.com.bottomframework.util.PreferenceUtil;
import frameworkandroid.tan.com.bottomframework.util.PublicUtil;


/**
 * Web请求基类
 * 
 * @author zeroangus
 * 
 */
public class BaseWeb<T> {
	//protected static String BaseUrl = "http://211.149.169.221:8080" + "/umijoy";
	//无线服务器
	//protected static String BaseUrl = "http://192.168.10.122:8080" + "/umijoy";
	//有线服务器
	//protected static String BaseUrl = "http://210.56.209.74:9080" + "/umijoy";
	//真正的服务器
	protected static String BaseUrl = Constant_secret.BaseUrl;
	private static String TAG = "BaseWeb";
	private HttpUtils mHttpUtil = null;//网络请求的实例
	private Context mContext;

	public  PreferenceUtil mPrefUtil; //用于配制信息的业务类 继成了各种方法
	protected ServiceApplication serviceApplication;

	private static String JSESSIONID = ServiceApplication.getInstance().readSession();//作用是换页面的情况下，不要重新登录

	public BaseWeb(Context context) {
		this.mContext = context;
		mPrefUtil= ServiceApplication.getInstance().getPreferenceUtil();
		serviceApplication=ServiceApplication.getInstance();
	}

	/**
	 * 获取Http实例的方法
	 * 
	 * @return
	 */
	private HttpUtils getHttpUtil() {
		if (mHttpUtil == null) {
			mHttpUtil = new HttpUtils();
			/*mHttpUtil.configCurrentHttpCacheExpiry(1000 * 10); //设置当前请求的缓存时间 10秒内数据不改变
			mHttpUtil.configTimeout(1000 * 1); //设置连接超时时间间隔*/
			/*mHttpUtil.configCurrentHttpCacheExpiry(600000 * 10); //设置当前请求的缓存时间
			这些设置的大了进度条会转的时间长	  上传数据是进度条上传的时间长
			时间单位是毫米  1000 * 3600 这是1个小时  1000 * 60这是1分钟*/
			mHttpUtil.configCurrentHttpCacheExpiry(60 * 1000); //设置当前请求的缓存时间
			mHttpUtil.configTimeout(1000 * 10);// 连接超时  //指的是连接一个url的连接等待时间
			mHttpUtil.configSoTimeout(1000 * 10);// 获取数据超时  //指的是连接上一个url，获取response的返回等待时间
		}
		return mHttpUtil;
	}

	/**
	 * 解析json串   T是外部类定义的泛型
	 * 
	 * @param json
	 * @param type
	 * @param listener
	 */
	protected void parse(String json, Type type, DataListener<T> listener) {

		//System.out.println("从服务端返回的原始数据字符串  ----------> " + json);    //正式版关闭
//		MyLog.ShowLog(json);
//		LogTxt.writeLog(json, "网络返回的数据");

		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(json);//把字符串转化成一个json对象
//			if ("true".equals(jsonObject.getString("success"))) {
			if ((jsonObject.getInt("code"))==1) {
				Gson gson = new Gson();
				String data1=jsonObject.getString("data");
//				MyLog.ShowLog("data1的值是"+data1);
//				String data2=(new JSONObject(data1)).getString("data");
				if ("[]".equals(data1)||"[[]]".equals(data1)) {
//					PublicUtil.ShowToast("没有数据返回！");
					listener.onSuccess(null);
					return;
				}
				T obj = gson.fromJson(data1, type);
				listener.onSuccess(obj);
			} else {
				/*String data1=jsonObject.getString("errors");
				String data2=(new JSONObject(data1)).getString("message");*/
				PublicUtil.ShowToast("" + jsonObject.getString("msg"));
				listener.onFailed();
			}
		} catch (Exception e) {
			listener.onFailed();
			PublicUtil.ShowToast("" + e.toString());
			Log.e(TAG, "BaseWeb  parse失败: " + e.toString());
			MyLog.ShowLog("BaseWeb  parse失败 : " + e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * 解析json串
	 * 
	 * @param json
	 * @param listener
	 */
	protected void parse(String json, DataListener<String> listener) {

		System.out.println("parse ----------> " + json);    //正式版关闭
		LogTxt.writeLog(json,"邻优网网络返回的数据2");

		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(json);
//			if ("true".equals(jsonObject.getString("success"))) {
				if ((jsonObject.getInt("code"))==1) {
				listener.onSuccess("" + jsonObject.getString("msg"));
			} else {
				PublicUtil.ShowToast("" + jsonObject.getString("msg"));
				listener.onFailed();
			}
		} catch (Exception e) {
			listener.onFailed();
			Log.e(TAG, "json parse failed : " + e.toString());
			//PromptManager.showDialogTest1(mContext, "json parse failed : " + e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * 请求  IRequestListener是被类定义的一个内部回调接口
	 * 
	 * @param url
	 * @param params
	 */
	protected void post(String url, RequestParams params,HttpMethod method,
			final IRequestListener listener) {
		MyLog.ShowLog("访问的网址"+url);
		// HttpUtils mHttpUtil = getHttpUtil();
		// mHttpUtil.configCurrentHttpCacheExpiry(1000 * 10);
		// mHttpUtil.configTimeout(5000);
		if (null != JSESSIONID) {    //如果cookie不为空，带上cookie 服务器端叫session
			System.out.println("JSESSIONID ----> " + JSESSIONID);
			//运用构造方法  创建一个实例  cookie和settion是一个键值对
			BasicClientCookie c = new BasicClientCookie(Constant.session_pref,
					JSESSIONID);
			c.setVersion(0);
			c.setPath("/");
			//c.toString() 相当于省份识别码
			c.setDomain(Constant.COOKIE_DOMAIN);
			               	System.out.println("JSESSIONID ----> " + c.toString());
			//获得访问网络的http对象
			DefaultHttpClient dh = (DefaultHttpClient) getHttpUtil()
					.getHttpClient();
			CookieStore cookieStore = dh.getCookieStore();
			cookieStore.addCookie(c);
			getHttpUtil().configCookieStore(cookieStore);
		}
		//RequestCallBack<String>()是工具本身自带的内置回调方法
		showProgressDialog();
		params.addHeader("token", mPrefUtil.getStrSetting(Constant.token));
		getHttpUtil().send(method, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						Log.e("post", arg0.toString() + "  " + arg1);
						MyLog.ShowLog(arg0.toString() + "  " + arg1);
						PublicUtil.ShowToast("网络请求出错，请稍后再试!");
                        closeProgressDialog();
                        listener.onFailed();
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						closeProgressDialog();
						DefaultHttpClient dh = (DefaultHttpClient) getHttpUtil()
								.getHttpClient();
						CookieStore cs = dh.getCookieStore();
						//返回的数据是一个Cookie的集合
						List<Cookie> cookies = cs.getCookies();
						for (int i = 0; i < cookies.size(); i++) {
							//从返回的Cookie集合里找到一个名字为"JSESSIONID"的保存到本地
							if ("JSESSIONID".equals(cookies.get(i).getName())) {
								JSESSIONID = cookies.get(i).getValue();
								ServiceApplication.getInstance()
										.saveSession(JSESSIONID);
								break;
							}
						}
						Log.d("jack", "比较sessionid  " + JSESSIONID);
						//PromptManager.showDialogTest1(mContext,responseInfo.result.toString());
						listener.onSuccess(responseInfo.result);
					}
				});
	}

	private ProgressDialog pd;

	public void closeProgressDialog() {
		if (pd != null) {
			try {
				pd.dismiss();
			} catch (Exception e) {

			}
		}
	}

	protected void showProgressDialog() {
		if (pd != null) {
			pd.dismiss();
		}
		pd = ProgressDialog.show(mContext, "", "数据加载中...", true, false);
		pd.show();

	}

	protected void showProgressDialog(String title, String content) {
		if (pd != null) {
			pd.dismiss();
		}
		pd = ProgressDialog.show(mContext, title, content, true, false);
		pd.show();
	}

	//定义网络访问的回调方法
	protected interface IRequestListener {
		public void onSuccess(String json);
        public void onFailed();
	}

	//从assets文件夹中获得模拟的返回数据
//	{"data":[],"limit":"0","msg":"查询成功!","success":"true"}
	protected String getAssetsData(String name) {
		String result = "";
		try {
			//获取输入流
			InputStream mAssets = mContext.getAssets().open(name);

			//获取文件的字节数
			int lenght = mAssets.available();
			//创建byte数组
			byte[] buffer = new byte[lenght];
			//将文件中的数据写入到字节数组中
			mAssets.read(buffer);
			mAssets.close();
			result = new String(buffer);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("fuck", "error");
			LogTxt.writeLog(e.toString(), "getAssetsData  发生异常");
			return result;
		}
	}


}
