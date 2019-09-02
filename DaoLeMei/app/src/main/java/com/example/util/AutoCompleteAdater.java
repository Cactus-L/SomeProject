package com.example.util;

import com.example.database.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;
/**
 * 将游标指向的数据直接绑定到ListView#
 * 思路：通过传统的方法执行查询操作，返回一个Cursor，
 * 将这个游标放入到SimpleCursorAapter的构造函数中即可，最后setAdapter
 * @author Cactus
 *
 */

public class AutoCompleteAdater extends SimpleCursorAdapter {

	private DBHelper dbHelper = null;
	private Context context;
	// 查询字段
	private String queryField;

	public AutoCompleteAdater(Context context, int layout, Cursor c, String from, int to)
	{
		super(context, layout, c, new String[] { from }, new int[] { to });
		this.context = context;
		this.queryField = from;
	}

	/**
	 * 动态查询数据库
	 */
	@Override
	public Cursor runQueryOnBackgroundThread(CharSequence constraint)
	{
		if (constraint != null)
		{
			return getDbHelper().query((String) constraint);
		} else
		{
			return null;
		}
	}

	/**
	 * 这里设置在弹出的提示列表中点击某一项后的返回值,返回值将被显示在文本框中
	 * 
	 */
	@Override
	public CharSequence convertToString(Cursor cursor)
	{
		return cursor.getString(cursor.getColumnIndex(queryField));
	}

	public DBHelper getDbHelper()
	{
		if (dbHelper == null)
		{
			dbHelper = new DBHelper(this.context);
		}
		return dbHelper;
	}

}
