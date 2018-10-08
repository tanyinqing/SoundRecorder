package frameworkandroid.tan.com.bottomframework.FisheriesProduct.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import frameworkandroid.tan.com.bottomframework.database.DBHelper;
import frameworkandroid.tan.com.bottomframework.entity.Work;

/**
 * Created by Administrator on 2018/8/31 0031.
 */
public class RiZhiDB {
    private Context context;
    private DBHelper helper;

    public RiZhiDB(Context context) {
        // 数据库的初始化
        helper = new DBHelper(context, "WorkTracking.db", null, DBHelper.DATABASE_VERSION);
        this.context = context;

    }

    //插入一个对象
    public void insertAlarm(Work work) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Content","测试1");
        db.insert("RiZhi", null, values);
        db.close();
    }
}
