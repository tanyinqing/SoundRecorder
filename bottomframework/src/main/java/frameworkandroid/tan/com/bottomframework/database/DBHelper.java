package frameworkandroid.tan.com.bottomframework.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import frameworkandroid.tan.com.bottomframework.FisheriesProduct.database.databaseSQL_fisheries;


public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "WorkTracking.db";   //数据库名称
	@SuppressLint("SdCardPath")
	private static final String DATABASE_PATH = "/data/data/tyq.com.worktracking/databases/";   //数据库的存储路径
	//这个是建立在上一个app版本是低版本的基础上的 在同版本级别之间不会执行
	public static final int DATABASE_VERSION = 1;//给外部引用的常量


	//构造器  各个构造器来加入数据库版本的信息
	public DBHelper(Context context, String name, CursorFactory factory,
					int version) {
		super(context, name, factory, version);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(databaseSQL_fisheries.CREATE_RiZhi);
		/*db.execSQL(CREATE_IMAGE);*/
		/*db.execSQL("alter table SecurityItemEntity add column ST_Id VARCHAR(100)");
		db.execSQL("alter table SecurityItemEntity add column SN_Number VARCHAR(100)");*/

		/*db.execSQL(databaseSQL.CREATE_WORK);
		db.execSQL(databaseSQL.CREATE_IMAGE);
		db.execSQL(databaseSQL.CREATE_TaskListEntity);
		db.execSQL(databaseSQL.CREATE_Task_entity);
		db.execSQL(databaseSQL.CREATE_SecurityStatus);
		db.execSQL(databaseSQL.CREATE_SecurityItemEntity);
		db.execSQL(databaseSQL.CREATE_RiZhi);
		db.execSQL(databaseSQL.CREATE_QiTaBeiZhu);
		db.execSQL(databaseSQL.CREATE_BaseUrl);
		db.execSQL(databaseSQL.CREATE_BASICDATA);*/
	}

	@Override   //重写升级的方法
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > oldVersion) {

			for (int i = (oldVersion + 1); i <= newVersion; i++)
				upgrade(db, i);//升级程序时，修改了数据库，需要在以下用sql语句去修改
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		if (this != null)
			this.close();
		super.finalize();
	}

	/*
	 * 升级程序时，修改了数据库，需要在以下用sql语句去修改
	 */
	private void upgrade(SQLiteDatabase db, int version) {
		switch (version) {
		case 1:
			break;
		case 2:
			db.beginTransaction();//开启事务
			/*db.execSQL("alter table SecurityItemEntity add column ST_Id VARCHAR(100)");
			db.execSQL("alter table SecurityItemEntity add column SN_Number VARCHAR(100)");
			db.execSQL(CREATE_BASICDATA);*/
			//添加字段  必须开启事务
			//db.execSQL("alter table BaseUrl add column tanyinqing INTEGER default 0");
			//添加字段
//			db.execSQL("alter table yao add column yaoTest INTEGER default 0");
			//添加表
//			db.execSQL("create table MyBaodianFavorite ([Id] INTEGER DEFAULT '0' PRIMARY KEY AUTOINCREMENT,[NewsId] INTEGER NOT NULL,[AppType] VARCHAR(50) NOT NULL,[ImageUrl] VARCHAR(50),[Introduction] TEXT NOT NULL,[Title] VARCHAR(50) NOT NULL,[Author] VARCHAR(50) NOT NULL,[PubDate] VARCHAR(50) NOT NULL)");
			//db.execSQL("create table image ([content] VARCHAR(100),[image] VARCHAR(100),[newId] VARCHAR(100))");
			//db.execSQL(CREATE_BASICDATA);
			db.setVersion(version);//设置版本号
			db.setTransactionSuccessful();//事务成功  否则可以回滚事务的
			db.endTransaction();  //事务结束
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			break;
		}
	}

	// 复制数据库到sd卡中
	public static void copyDB(Context context) {
		// DBHelper.context = context;
		// 没有file先创建文件夹
		File dbFile = new File(DATABASE_PATH);
		if (!dbFile.exists()) {
			dbFile.mkdirs();
		} else {
			File dbContentFile = new File(DATABASE_PATH, DATABASE_NAME);
			if (dbContentFile.exists()) {
				return;
			}
		}

		InputStream is = null;
		FileOutputStream fos = null;
		try {
			AssetManager am = context.getAssets();  //管理器
			// 得到.db file
			File saveFile = new File(DATABASE_PATH, DATABASE_NAME);
			// 获取数据库本地资源
			is = am.open("securityCheckOne.db");
			// 获取数据库输入流
			fos = new FileOutputStream(saveFile);
			byte[] buffer = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) > 0) {
				fos.write(buffer, 0, count);
			}

		} catch (Exception e) {
			Log.i("Exception", Log.getStackTraceString(e));
		} finally {
			try {
				if (fos != null)
					fos.close();
				if (is != null)
					is.close();
			} catch (Exception e) {
				Log.i("Exception", Log.getStackTraceString(e));
			}
		}
	}
}
