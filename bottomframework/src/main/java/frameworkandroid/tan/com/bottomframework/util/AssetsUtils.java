package frameworkandroid.tan.com.bottomframework.util;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/5/11 0011.
 */
public class AssetsUtils {
    //从assets文件夹中获得模拟的返回数据
//	{"JsonDataNew":[],"limit":"0","msg":"查询成功!","success":"true"}
//    String json = AssetsUtils.getAssetsData(mContext, "taskNewEntity.txt");
    public static String getAssetsData(Context mContext,String name) {
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
//            LogTxt.writeLog(e.toString(), "getAssetsData  发生异常");
            return result;
        }
    }


}
