package frameworkandroid.tan.com.bottomframework.util.utils_log;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import frameworkandroid.tan.com.bottomframework.Constant;

/**
 * 这个的作用是把日志都记录的一个文件中
 *     // 前面是内容，后面是文件名 会把日志累加起来
      LogTxtLeiJi.writeLog("3","记录推送下来的订单号");
 */
public class LogTxt_url {


    public static void writeLog(String context,String fileName){
        if (Constant.CeShiBan_true){  //这个值该为false后就不会在打印日志了

            // String filePath = "/sdcard/Test/";
            if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//            Toast.makeText(getApplicationContext(),
//                    "没有SD卡,无法保存图像.", Toast.LENGTH_SHORT).show();
                Log.e("ImagesUtil_me.java", "没有SD卡,无法保存图像.");
                return ;
            }

            //文件命名的时间成分
            Calendar calendar=Calendar.getInstance();
            Date data=calendar.getTime();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=format.format(data);

            // 获得外部SD卡,创建本应用程序的保存目录,保存相片
            File sdCard = Environment.getExternalStorageDirectory();
            // File photoDir = new1 File(sdCard.getAbsolutePath() + "/mycamera");
            File photoDir = new File(sdCard.getAbsolutePath() + "/"+"测试数据"+ "/"+"水产");
            // MyLog.ShowLog("保存图像地址"+photoDir.getAbsolutePath());
            if(!photoDir.exists()){
                photoDir.mkdirs();
            }

        /*File photo = new1 File(photoDir,fileName+time+".txt");
        MyLog.ShowLog("创建photo文件");
*/
            // 每次写入时，都换行写
            String strContent = "\r\n"+time+"\r\n"+context;
            //String strContent = context;
            try {
                // File file = new File(photoDir,fileName+time+".txt");
                File file = new File(photoDir,fileName+".txt");
                if (!file.exists()) {
                    //Log.d("TestFile", "Create the file:" + strFilePath);
                    file.createNewFile();
                }
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                // 获取文件的长度即字节数  // 将写文件指针移到文件尾
                raf.seek(file.length());
                // 获取文件的长度即字节数  // 将写文件指针移到文件开头
                // raf.seek(0);
                raf.write(strContent.getBytes());
                raf.close();

           /* OutputStream outstream = new FileOutputStream(file);
            OutputStreamWriter out = new OutputStreamWriter(outstream);
            out.write(strContent);
            out.close();*/
            } catch (Exception e) {
                Log.e("TestFile", "Error on write File:" + e);
            }
        }
    }
}