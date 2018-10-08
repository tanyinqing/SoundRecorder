package frameworkandroid.tan.com.bottomframework.database;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import frameworkandroid.tan.com.bottomframework.FisheriesProduct.database.RiZhiDB;

/**
 * Created by Administrator on 2018/5/16 0016.
 */
public class TestData {

    //从数据库获取数据
  /*  public static ArrayList<TaskListEntityNew> getListData(Context mContext) {
        TaskListEntityDBNew taskListEntityDBNew = new TaskListEntityDBNew(mContext);
        ArrayList<TaskListEntityNew> alarmArray=taskListEntityDBNew.getAlarmList(1);
        return alarmArray;
    }*/

    // 向数据库中填充数据  模板是data中的全部数据  只在下载数据时进行判断
   /* public static void FillDataDown(Context mContext){
        TaskListEntityDBNew taskListEntityDBNew = new TaskListEntityDBNew(mContext);
        String json = AssetsUtils.getAssetsData(mContext, "MyDownTaskList41.txt");
        Type type = new TypeToken<JsonDataNew>() {
        }.getType();
        JSONObject jsonObject;
        try {
            //把字符串转化成一个json对象
            jsonObject = new JSONObject(json);
            Gson gson = new Gson();
            // 前一个参数是字符串 后一个参数是类型
            JsonDataNew obj = gson.fromJson(String.valueOf(jsonObject), type);
            //	  想数据库data字段对应的数据
            taskListEntityDBNew.insertList((ArrayList<TaskListEntityNew>) obj.getData());
            MyLog.ShowLog("数据插入成功");
        } catch (JSONException e) {
            e.printStackTrace();
            LogTxt.writeLog("json parse failed : " + e.toString(), "网络下载数据解析异常");
            PublicUtil.ShowToast("数据解析异常");
        }
    }

     // 向数据库中填充数据  解析的模板是任务列表的数组
    public static void FillData(Context mContext){
        TaskListEntityDBNew taskListEntityDBNew = new TaskListEntityDBNew(mContext);
        String json = AssetsUtils.getAssetsData(mContext, "MyDownTaskList41.txt");
        Type type = new TypeToken<ArrayList<TaskListEntityNew>>() {
        }.getType();
        JSONObject jsonObject;
        try {
            //把字符串转化成一个json对象
            jsonObject = new JSONObject(json);
            Gson gson = new Gson();
            // 前一个参数是字符串 后一个参数是类型
            ArrayList<TaskListEntityNew> obj = gson.fromJson(jsonObject.getString("data"), type);
            //	  数据库插入数据
            taskListEntityDBNew.insertList(obj);
            MyLog.ShowLog("数据插入成功");
        } catch (JSONException e) {
            e.printStackTrace();
            LogTxt.writeLog("json parse failed : " + e.toString(), "网络下载数据解析异常");
        }
    }*/

}
