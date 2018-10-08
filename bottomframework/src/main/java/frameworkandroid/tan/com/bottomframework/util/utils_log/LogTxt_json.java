package frameworkandroid.tan.com.bottomframework.util.utils_log;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import frameworkandroid.tan.com.bottomframework.Constant;
import frameworkandroid.tan.com.bottomframework.util.MyLog;
import frameworkandroid.tan.com.bottomframework.util_Jar.PhoneUtils;

/**
 * 这种是正常的把日志保存在内存卡的文件夹里
 *      // 前面是内容  后面是文件名  日志写到内存卡上
 *        LogTxt.writeLog("小谈你好","小谈测试");
 *  这个日志只在测试模式下，才会打开
 */
public class LogTxt_json {
    public static long xuhao=1;


    public static void writeLog(String context,String fileName,String programName){
//        MyLog.ShowLog("手机串号"+PhoneUtils.getIMEI());
        //红米note打印日志
        //小米3 864690020418318 红米note  99000661309615
        if (Constant.CeShiBan_true||"864690020418318".equals(PhoneUtils.getIMEI())||"99000661309615".equals(PhoneUtils.getIMEI())){  //这个值该为false后就不会在打印日志了
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
            // SimpleDateFormat format=new SimpleDateFormat("_HH:mm:ss");
            String time=format.format(data);

            // 获得外部SD卡,创建本应用程序的保存目录,保存相片
            File sdCard = Environment.getExternalStorageDirectory();
            // File photoDir = new1 File(sdCard.getAbsolutePath() + "/mycamera");
            File photoDir = new File(sdCard.getAbsolutePath() + "/"+programName+"测试数据");
            // MyLog.ShowLog("保存图像地址"+photoDir.getAbsolutePath());
            // 如果目录不存在 就生成目录
            if(!photoDir.exists()){
                photoDir.mkdirs();
            }

        /*File photo = new1 File(photoDir,fileName+time+".txt");
        MyLog.ShowLog("创建photo文件");
*/
            // 每次写入时，都换行写

            String strContent = time+ "\r\n"+"链接"+ "\r\n"+context;
            //String strContent = context;
            try {
                File file = new File(photoDir,fileName+".txt");
                if (!file.exists()) {
                    //Log.d("TestFile", "Create the file:" + strFilePath);
                    file.createNewFile();
                }else {
                    file.delete();
                }
           /* RandomAccessFile raf = new1 RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();*/
                OutputStream outstream = new FileOutputStream(file);
                OutputStreamWriter out = new OutputStreamWriter(outstream);
                out.write(strContent);
                if (xuhao<=2){
                    xuhao++;
                }else {
                    xuhao=1;
                    //缓存的数据 ，满足5条 就删除
                    String  DATABASE_NAME="LogTan";   //数据的存储路径
                    File sdCard1 = Environment.getExternalStorageDirectory();
                    String DATABASE_PATH= sdCard1.getAbsolutePath()+"/"+DATABASE_NAME+"/";
                    File dbContentFile = new File(DATABASE_PATH);
                    Boolean tan=false;
                    if (dbContentFile.exists()) {
                        //dbContentFile.delete();
                        if (tan){
                            delete(dbContentFile);
                        }
                    }
                }

                out.close();
            } catch (Exception e) {
                Log.e("TestFile", "Error on write File:" + e);
            }
        }
    }


    //删除一个文件夹
    public static void delete(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]);
            }
            // file.delete(); 删除这个文件夹
        }
    }
}