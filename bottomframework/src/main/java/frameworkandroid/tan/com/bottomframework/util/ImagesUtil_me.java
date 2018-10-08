package frameworkandroid.tan.com.bottomframework.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

/*import com.tandong.sa.zUImageLoader.core.DisplayImageOptions;
import com.tandong.sa.zUImageLoader.core.assist.ImageScaleType;
import com.tandong.sa.zUImageLoader.core.display.FadeInBitmapDisplayer;
import com.tandong.sa.zUImageLoader.core.display.RoundedBitmapDisplayer;
import com.xlw.application.XlwApplication;
import com.xlw.onroad.R;*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import frameworkandroid.tan.com.bottomframework.R;
import frameworkandroid.tan.com.bottomframework.ServiceApplication;
import frameworkandroid.tan.com.bottomframework.util.utils_log.LogTxt;


/**
 * Created by xinliwei on 2015/7/2.
 */
public class ImagesUtil_me {
    public static final String TAG = "ImagesUtil_me.class";

    /**
     * 有两种办法将照片加载到bitmap中：1.通过uri用stream的方式
     * @param mContext Context
     * @param uri  资源uri
     * @param width 加载后缩放到的目标宽度
     * @param height 加载后缩放到的目标高度
     * @return 加载并缩放后的位图
     */
    public static Bitmap loadBitmap(Context mContext, Uri uri, int width,int height,int mark) {
        Bitmap mBitmap = null;
        try {
            ContentResolver resolver = mContext.getContentResolver();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;  // FaceDetecor只能读取RGB 565格式的Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(resolver.openInputStream(uri), null, options);
            mBitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height);   // 缩放位图到指定大小
        } catch (FileNotFoundException ex) {
            //Log.e(mContext.getPackageName(), "图像加载异常: " + ex.getMessage());
            MyLog.ShowLog("图像加载异常: " + ex.getMessage());
            // 图片的大小是400 600
            if (mark==1){
                Bitmap mBitmap1=BitmapFactory.decodeResource(ServiceApplication.getInstance().getResources(), R.drawable.ic_launcher);
                return ThumbnailUtils.extractThumbnail(mBitmap1, width, height);   // 缩放位图到指定大小
            }else if (mark==0){

            }
        }
        return mBitmap;
    }

    /**
     * 有两种办法将照片加载到bitmap中：2.用照片的真实路径加载
     * @param path  被加载的图像的路径
     * @param width 加载后缩放到的目标宽度
     * @param height 加载后缩放到的目标高度
     * @return 加载并缩放后的位图
     */
    public static Bitmap loadBitmap(String path, int width, int height) {
        Bitmap mBitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;  // FaceDetecor只能读取RGB 565格式的Bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        mBitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height);  // 缩放位图
        return mBitmap;
    }

    // 获得照片的真实路径
    public static String obtainFilePath(Context context, Uri uri){
        String filePath = null;
        String[] projection = { MediaStore.Images.Media.DATA };

        CursorLoader loader = new CursorLoader(context, uri, projection, null,
                null, null);
        Cursor cursor = loader.loadInBackground();

        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(projection[0]));
            cursor.close();
        }
        return filePath;
    }

    // 将图片保存到SD卡中  为什么这个方法总是报错  因为没有加上写内存卡的权限
    public static Uri saveImage(Bitmap bitmap,Context context) throws FileNotFoundException {
        //先判断手机是否装有SD卡,并可以进行读写
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//            Toast.makeText(getApplicationContext(),
//                    "没有SD卡,无法保存图像.", Toast.LENGTH_SHORT).show();
            Log.e("ImagesUtil_me.java","没有SD卡,无法保存图像.");
            return null;
        }

        //文件的保存路径
        String[] file_path=new String[2];
        file_path[0]="谈银青";
        file_path[1]="个人相册";


       /* file_path[1]="朝阳区甘露园1区";
        file_path[2]="6号楼";
        file_path[3]="4单元";
        file_path[4]="301室";*/

        //文件命名的时间成分
        Calendar calendar=Calendar.getInstance();
        Date data=calendar.getTime();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(data);
        String Month=time.substring(0,7);
        String day=time.substring(8,10);

        /*file_path[1]= Month;
        file_path[2]= day;*/

        // 获得外部SD卡,创建本应用程序的保存目录,保存相片  放在跟目录下
       File sdCard = Environment.getExternalStorageDirectory();
        // 放在外部SD卡 的私有目录下
        // File sdCard =context.getExternalFilesDir("");
        //这个是默认的目录，将图片保存在内存卡的根目录下
         File photoDir = new File(sdCard.getAbsolutePath() + "/"+file_path[0]+ "/"+file_path[1]);

        //这个是默认的目录，将图片保存在内存卡的私有目录下  /storage/emulated/0/Download   Context.getExternalFilesDir()
        //  File photoDir = new File(context.getExternalFilesDir("") + "/"+file_path[0]+"/"+file_path[1]+"/"+file_path[2]+"/"+file_path[3]+"/"+file_path[4]+"/"+file_path[5]+"/"+file_path[6]);
        MyLog.ShowLog("保存图像地址"+photoDir.getAbsolutePath());
        LogTxt.writeLog(photoDir.getAbsolutePath(), "图片保存的地址");
        if(!photoDir.exists()){
            photoDir.mkdirs();
        }
       // File photo = new1 File(photoDir,(new1 Date()).getCreateTime() + ".jpeg");
//        File photo = new File(photoDir,ServiceApplication.getInstance().getPreferenceUtil().getStrSetting("state")+"时间"+time+".jpeg");
        File photo = new File(photoDir,"tan"+".jpeg");
       MyLog.ShowLog("创建photo文件");
        FileOutputStream fOut = new FileOutputStream(photo); //这句出的bug
        MyLog.ShowLog("输出流文件创建完成");
        // 把数据写入文件.下面的100参数表示不压缩
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fOut);

        // 解析图片的Uri，用它传递分享图片
        return Uri.parse("file://" + photo.getAbsolutePath());
//        Toast.makeText(this,uri.toString(),Toast.LENGTH_LONG).show();
    }

    // 显示缓存的图片(使用了SmartAndroid中的com.tandong.sa.zUImageLoader.core.ImageLoader)
  /*  public void displayImage(String imageUrl, ImageView imageView){
        DisplayImageOptions options;
        options = new1 DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)       // 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)     // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)          // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)          // 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)   // 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)   // 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)                // 设置图片的解码类型
//                .decodingOptions(BitmapFactory.Options decodingOptions)               //设置图片的解码配置
                //.delayBeforeLoading(int delayInMillis)        //int delayInMillis为你设置的下载前的延迟时间
                //.preProcessor(BitmapProcessor preProcessor)   //设置图片加入缓存前，对bitmap进行设置
                .resetViewBeforeLoading(true)                   //设置图片在下载前是否重置，复位
                .displayer(new1 RoundedBitmapDisplayer(20))      //是否设置为圆角，弧度为多少
                .displayer(new1 FadeInBitmapDisplayer(100))      //是否图片加载好后渐入的动画时间
                .build();                   // 构建完成

        XlwApplication.getInstance().getImageLoader().displayImage(imageUrl, imageView, options);
    }*/
}

/*
加载图片方法：
1.直接加载：
imageLoader.displayImage(imageUrl, imageView); // imageUrl代表图片的URL地址，imageView代表承载图片的IMAGEVIEW控件 　

2.加载自定义配置的一个图片：
imageLoader.displayImage(imageUrl, imageView，options); // imageUrl代表图片的URL地址，imageView代表承载图片的IMAGEVIEW控件 ， options代表DisplayImageOptions配置文件

3.图片加载时候带加载情况的监听
imageLoader.displayImage(imageUrl, imageView, options, new1 ImageLoadingListener() {
    @Override
    public void onLoadingStarted() {
       //开始加载的时候执行
    }
    @Override
    public void onLoadingFailed(FailReason failReason) {
       //加载失败的时候执行
    }
    @Override
    public void onLoadingComplete(Bitmap loadedImage) {
       //加载成功的时候执行
    }
    @Override
    public void onLoadingCancelled() {
       //加载取消的时候执行

    }});

4.图片加载时候，带监听又带加载进度条的情况

imageLoader.displayImage(imageUrl, imageView, options, new1 ImageLoadingListener() {
    @Override
    public void onLoadingStarted() {
       //开始加载的时候执行
    }
    @Override
    public void onLoadingFailed(FailReason failReason) {
       //加载失败的时候执行
    }
    @Override
    public void onLoadingComplete(Bitmap loadedImage) {
       //加载成功的时候执行
    }
    @Override
    public void onLoadingCancelled() {
       //加载取消的时候执行
    },new1 ImageLoadingProgressListener() {
      @Override
      public void onProgressUpdate(String imageUri, View view, int current,int total) {
      //在这里更新 ProgressBar的进度信息
      }
    });
 */
/*
加载本地图片：
String imageUri = "http://site.com/image.png";      // from Web
String imageUri = "file:///mnt/sdcard/image.png";   // from SD card  这个方法可能不对
String imageUri = "content://media/external/audio/albumart/13";     // from content provider
String imageUri = "assets://image.png";             // from assets
String imageUri = "drawable://" + R.drawable.image; // from drawables (only Images, non-9patch)
 */
