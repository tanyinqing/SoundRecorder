package frameworkandroid.tan.com.bottomframework.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

import frameworkandroid.tan.com.bottomframework.database.DBHelper;
import frameworkandroid.tan.com.bottomframework.entity.moban_databass_entity;


public class moban_DB {
	//点赞数据表
	@SuppressWarnings("unused")
	private Context context;
	private DBHelper helper;

	public moban_DB(Context context) {
		// 数据库的初始化
		helper = new DBHelper(context, "securityCheckOne.db", null, DBHelper.DATABASE_VERSION);
		this.context = context;

	}

	//插入一个对象
	public void insertAlarm(moban_databass_entity mRiZhi_entity) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("U_Id", mRiZhi_entity.getU_Id());
		values.put("Date", mRiZhi_entity.getDate());
		values.put("Content", mRiZhi_entity.getContent());
		values.put("IT_Number", mRiZhi_entity.getIT_Number());
		db.insert("RiZhi", null, values);
		db.close();
	}


	//得到所有小区的集合  在领取任务中出现 现在要出现小区下用户为0的小区
	public ArrayList<moban_databass_entity> getAlarmList() {
		SQLiteDatabase db = helper.getWritableDatabase();
		ArrayList<moban_databass_entity> alarmArray = new ArrayList<moban_databass_entity>();
		Cursor cursor = db.query("RiZhi", new String[] { "*"}, null, null, null, null, null);
		//将cursor对应的值放入链表集合中
		cursorMethod(alarmArray, cursor);
		cursor.close();
		db.close();
		return alarmArray;
	}



	//将cursor对应的值放入链表集合中
	private void cursorMethod(ArrayList<moban_databass_entity> alarmArray, Cursor cursor) {
		while (cursor.moveToNext()) {
			moban_databass_entity alarm = new moban_databass_entity();
			alarm.setId(cursor.getString(cursor.getColumnIndex("id")));
			alarm.setU_Id(cursor.getString(cursor.getColumnIndex("U_Id")));
			alarm.setDate(cursor.getString(cursor.getColumnIndex("Date")));
			alarm.setContent(cursor.getString(cursor.getColumnIndex("Content")));
			alarm.setIT_Number(cursor.getString(cursor.getColumnIndex("IT_Number")));
			alarmArray.add(alarm);
		}
	}


	//根据goodId进行删除操作
	public void delete(String id) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("RiZhi", "id=?",new String[] {id});
		db.close();
	}

	/**
	 * 删除这个数据表的数据
	 */
	public void delete() {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("RiZhi", null, null);
		db.close();
	}
	
}