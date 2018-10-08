package frameworkandroid.tan.com.bottomframework.util;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import frameworkandroid.tan.com.bottomframework.Constant;

/**
 * Created by Administrator on 2018/7/31 0031.
 */
public class DabaCopy {
    //如何高效检查一个数组中是否包含某个值
    public static final String DATABASE_NAME = "WorkTracking.db";   //数据库名称
    public static final String DATABASE_NAME1 = Constant.prefName+".xml";   //缓存文件名称
    @SuppressLint("SdCardPath")
    private static final String DATABASE_PATH = "/data/data/com.tyq.fisheriesproduct/databases/";   //数据库的存储路径
    private static final String DATABASE_PATH1 = "/data/data/com.tyq.fisheriesproduct/shared_prefs/";   //数据库的存储路径

    private static ProgressDialog pd;
    private static String programName1;

    public static void copy(Context mContext,String programName) {
        programName1=programName;
        File sdCard = Environment.getExternalStorageDirectory();
        File toDir = new File(sdCard.getAbsolutePath() + "/"+"数据库"+ "/"+programName);

        if (!toDir.exists()) {
            toDir.mkdir();
        }

        showProgressDialog(mContext);
        copyDatabase(mContext, sdCard);
        copyDatabase1(mContext, sdCard);
        closeProgressDialog();
    }

    private static void copyDatabase(Context mContext, File sdCard) {
        File file = new File(sdCard.getAbsolutePath() + "/"+"数据库"+ "/"+programName1,DATABASE_NAME);



        InputStream is = null;
        FileOutputStream fos = null;
        try {
            // 得到.db file
            File saveFile = new File(DATABASE_PATH, DATABASE_NAME);
            if (saveFile.exists()) {
                // 如果数据库源存在  才删除当前的数据库
                if (file.exists()) {
                    file.delete();
                }
                // 获取数据库本地资源
                is = new FileInputStream(saveFile);
                // 获取数据库输入流
                fos = new FileOutputStream(file);
                byte[] buffer = new byte[8192];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                PublicUtil.ShowToast("数据库备份成功！");
            }else {
                PublicUtil.ShowToast("找不到数据库！");
            }


        } catch (Exception e) {
            Log.i("Exception", Log.getStackTraceString(e));
            PublicUtil.ShowToast(Log.getStackTraceString(e));
            closeProgressDialog();
        } finally {
            try {
                if (fos != null)
                    fos.close();
                if (is != null)
                    is.close();
            } catch (Exception e) {
                Log.i("Exception", Log.getStackTraceString(e));
                PublicUtil.ShowToast(Log.getStackTraceString(e));
            }
        }
    }
//    复制缓存文件
    private static void copyDatabase1(Context mContext, File sdCard) {
        File file = new File(sdCard.getAbsolutePath() + "/"+"数据库"+ "/"+programName1, Constant.prefName+".xml");

        if (file.exists()) {
            file.delete();
        }

        InputStream is = null;
        FileOutputStream fos = null;
        try {
            // 得到.db file
            File saveFile = new File(DATABASE_PATH1, DATABASE_NAME1);
            if (saveFile.exists()) {
                // 获取数据库本地资源
                is = new FileInputStream(saveFile);
                // 获取数据库输入流
                fos = new FileOutputStream(file);
                byte[] buffer = new byte[8192];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                PublicUtil.ShowToast("缓存文件备份成功！");
            }else {
                PublicUtil.ShowToast("找不到缓存文件！");
            }


        } catch (Exception e) {
            Log.i("Exception", Log.getStackTraceString(e));
            PublicUtil.ShowToast(Log.getStackTraceString(e));
            closeProgressDialog();
        } finally {
            try {
                if (fos != null)
                    fos.close();
                if (is != null)
                    is.close();
            } catch (Exception e) {
                Log.i("Exception", Log.getStackTraceString(e));
                PublicUtil.ShowToast(Log.getStackTraceString(e));
            }
        }
    }

    public static void closeProgressDialog() {
        if (pd != null) {
            try {
                pd.dismiss();
            } catch (Exception e) {

            }
        }
    }

    protected static void showProgressDialog(Context mContext) {
        if (pd != null) {
            pd.dismiss();
        }
        pd = ProgressDialog.show(mContext, "", "数据准备加载...", true, false);
        showProgressDialogMessage("数据准备加载...");
        pd.setCancelable(true);//设置进度条是否可以按退回键取消
        pd.show();

    }
    protected static void showProgressDialogMessage(String mag) {
        if (pd != null) {
            pd.setMessage(mag);
        }
    }

}
