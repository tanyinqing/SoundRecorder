package frameworkandroid.tan.com.bottomframework.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;

import java.io.File;

import frameworkandroid.tan.com.bottomframework.R;
import frameworkandroid.tan.com.bottomframework.model.BaseModel;


public class SplashScreenActivity extends BaseActivity1  {
    private MobanModel mMobanModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        init();
    }

    private void init() {

        if (mMobanModel == null) {
            mMobanModel = new MobanModel(this);
        }

        new Handler().postDelayed(new Runnable() {
            public void run() {
              //  User_me district = ServiceApplication.getInstance().readUser();

                if (false) {
              /*  if (null == district) {
                    startActivityByClass(LoginAcitvity.class);*/
                } else {

                    //删除缓存的Log信息 每次启动时
                    String  DATABASE_NAME="LogTan";   //数据库的存储路径
                    File sdCard = Environment.getExternalStorageDirectory();
                    String DATABASE_PATH= sdCard.getAbsolutePath()+"/"+DATABASE_NAME+"/";
                    File dbContentFile = new File(DATABASE_PATH);
                    Boolean tan=true;
                    if (dbContentFile.exists()) {
                        //dbContentFile.delete();
                        if (tan){
                            mMobanModel.delete(dbContentFile);
                        }
                    }
//                    startActivityByClass(MainActivity.class);
                    Intent intent=new Intent();
                    intent.setAction("com.tyq.warehouse.activity.MainActivity");
                    startActivity(intent);
                }
                finish();
            }
        }, 1000);// 延时2秒
    }




    //这个是主导器
    private class MobanModel<T> extends BaseModel {

        public MobanModel(Context mContext) {
            super(mContext);

        }

        //删除一个文件夹
        public  void delete(File file) {
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
                file.delete();
            }
        }

    }
}
