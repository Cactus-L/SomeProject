package com.example.database;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.bmob.v3.listener.UpdateListener;

/**
 * 操作数据库
 * @author Cactus
 *
 */
public class StudentDaoImpl implements StudentsDao {

	private static DBHelper dbHelper;
	private static SQLiteDatabase database;

	public StudentDaoImpl(Context con)
	{
		dbHelper = new DBHelper(con);
	}

//	private void connsql()
//	{
//
//	}

	@Override
	public void add(StudentBase s)
	{
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DBHelper._ID, s.get_id());
		values.put(DBHelper.NAME, s.getName());
		db.insert(dbHelper.DATABASE_NAME, null, values);
	}

	// 获取班级的学生表
	public static List<StudentBase> getAllStu(Context con, String coursename)
	{
		List<StudentBase> classList = new ArrayList<StudentBase>();
		dbHelper = new DBHelper(con, "rollcall.db");
		database = dbHelper.getReadableDatabase();
		String sql = "select * from student where coursename = '" + coursename + "'";
		Cursor cursor = database.rawQuery(sql, null);
		while (cursor.moveToNext())
		{
			classList.add(new StudentBase(cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("stunum")),
					cursor.getString(cursor.getColumnIndex("coursename")),
					cursor.getInt(cursor.getColumnIndex("signFlag")), cursor.getInt(cursor.getColumnIndex("leaveFlag")),
					cursor.getInt(cursor.getColumnIndex("truantFlag"))));
		}
		System.out.println("所有" + classList.toString());
		database.close();
		cursor.close();
		return classList;
	}

	// 保存点名信息到数据库中，及上传到Bmob
	public static boolean saveState(Context con, StudentBase s)
	{

		dbHelper = new DBHelper(con, "rollcall.db");
		database = dbHelper.getReadableDatabase();
		ContentValues cv = new ContentValues();
		String id = s.get_id() + "";
		cv.put("name", s.getName());
		cv.put("stunum", s.getStuNum());
		cv.put("coursename", s.getCourseName());
		cv.put("signflag", s.getSignFlag());
		cv.put("leaveflag", s.getLeaveFlag());
		cv.put("truantflag", s.getTruantFlag());
		database.update("student", cv, "_id=?", new String[] { id });
		return true;
	}

	@Override
	public void updata(StudentBase s)
	{

	}

	@Override
	public void delete(int id)
	{

	}

	@Override
	public StudentBase findById(int id)
	{
		return null;
	}

}
