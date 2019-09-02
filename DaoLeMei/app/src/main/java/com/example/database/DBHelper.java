package com.example.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * DatabaseHelper
 * @author Cactus
 *
 */
public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "rollcall.db";
	public static final int DATABASE_VERSION = 1;
	public static final String STUDENT_TABLE = "student";// 表名
	public static final String _ID = "_id";// 表中的列名
	public static final String NAME = "name";// 表中的列名
	public static final String STU_NUM = "stunum";// 表中的列名

	public DBHelper(Context context)
	{
		// CursorFactory设置为null,使用默认值
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public DBHelper(Context con, String name)
	{
		this(con, name, null, 1);
	}

	public DBHelper(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	// 数据库第一次被创建时onCreate会被调用
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String sql = "create table student("
				+ " _id integer primary key autoincrement, name varchar(10),stunum varchar(20),objectId varchar(20), "
				+ " coursename varchar(20), signFlag int, leaveFlag int, truantFlag int, countnum int" + ")";
		db.execSQL(sql);
	}

	// 如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("ALTER TABLE base_info ADD COLUMN other STRING");
		onCreate(db);
	}

	/**
	 * 根据输入内容模糊查询
	 * 
	 * @param name
	 * @return
	 */
	public Cursor query(String name)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("select * from student where name like '%" + name + "%' limit 5", null);
		return c;

	}

}
