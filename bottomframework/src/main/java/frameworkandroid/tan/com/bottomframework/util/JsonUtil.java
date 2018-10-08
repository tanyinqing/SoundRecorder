package frameworkandroid.tan.com.bottomframework.util;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Objects;

import frameworkandroid.tan.com.bottomframework.entity_warehouse_download.Proch_lists_download;

/**
 * json与对象的相互转化
 * @param <T>
 */
public class JsonUtil<T> {

	public Object convertJsonToObject(String value,Class<T> object){
		Object resultObject = new Object();
		Gson gson = new Gson();
		resultObject = gson.fromJson(value, object);
		return resultObject;
	}
	
	public static Object convertJsonToObject(String value){
		Object resultObject = new Object();
		Gson gson = new Gson();
		resultObject = gson.fromJson(value, Object.class);
		return resultObject;
	}
	public static Object convertJsonToObject1(String value,Type type){
		String data1="";
		try {
			JSONObject jsonObject=new JSONObject(value);
			 data1=jsonObject.getString("data");

		} catch (JSONException e) {
			e.printStackTrace();
			MyLog.ShowLog(e.toString());
		}

		Object resultObject = new Object();
		Gson gson = new Gson();
		resultObject = gson.fromJson(data1, type);
		return resultObject;
	}
//	JsonUtil.convertObjectToJson(jsonDataUpLoad)
	public static String convertObjectToJson(Object obj){
		String result = "";
		Gson gson = new Gson();
		result = gson.toJson(obj);
		return result;
	}
}
