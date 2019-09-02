package com.example.rollcall;

import java.util.ArrayList;

import java.util.List;

import com.example.database.DBHelper;
import com.example.database.StudentBase;
import com.example.util.AutoCompleteAdater;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
/**
 * 查询学生界面*
 * @author Cactus
 */
public class QueryActivity extends Activity implements OnClickListener {
	private AutoCompleteTextView auto;
	private Button quert_name;
	private TextView show_name;
	private TextView show_num;
	private TextView show_course;
	private TextView show_sign;
	private TextView show_leave;
	private TextView show_truant;
	private static DBHelper dbHelper;
	private static SQLiteDatabase database;

	List<StudentBase> s = new ArrayList<StudentBase>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query);

		AutoCompleteAdater cursorAdapter = new AutoCompleteAdater(
				QueryActivity.this,
				android.R.layout.simple_dropdown_item_1line, 
				null,
				DBHelper.NAME,
				android.R.id.text1);
		// 设置输入一个字符就弹出提示列表(默认输入两个字符时才弹出提示)
		auto = ((AutoCompleteTextView) this.findViewById(R.id.autoCompleteTextView1));
		auto.setThreshold(1);
		auto.setAdapter(cursorAdapter);

		quert_name = (Button) findViewById(R.id.quert_name);
		show_name = (TextView) findViewById(R.id.show_name);
		show_num = (TextView) findViewById(R.id.show_num);
		show_course = (TextView) findViewById(R.id.show_course);
		show_sign = (TextView) findViewById(R.id.show_sign);
		show_leave = (TextView) findViewById(R.id.show_leave);
		show_truant = (TextView) findViewById(R.id.show_truant);
		quert_name.setOnClickListener(this);

		Topbar topBar = (Topbar) findViewById(R.id.topbar_query);
		topBar.setOnLeftAndRightClickListener(new Topbar.OnLeftAndRightClickListener() {
			@Override
			public void OnLeftButtonClick() {
				Intent i = getIntent();
				i.setClass(getApplicationContext(),MainActivity.class);
				startActivity(i);
			}

			@Override
			public void OnRightButtonClick() {
			}
		});
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.quert_name:
			String name = auto.getText().toString();
			s = query_name(this, name);
			assign(s);
			break;

		default:
			break;
		}
	}

	/*
	 * 赋值
	 */
	//此处是否应该for循环赋值
	private void assign(List<StudentBase> s)
	{
		show_name.setText(s.get(0).getName());
		show_num.setText(s.get(0).getStuNum());
		show_course.setText(s.get(0).getCourseName());
		show_sign.setText(s.get(0).getSignFlag() + "");
		show_leave.setText(s.get(0).getLeaveFlag() + "");
		show_truant.setText(s.get(0).getTruantFlag() + "");
	}

	/*
	 * 根据姓名得到该学生所有信息
	 */
	private List<StudentBase> query_name(Context con, String name)
	{
		List<StudentBase> stu = new ArrayList<StudentBase>();
		dbHelper = new DBHelper(con, "rollcall.db");
		database = dbHelper.getReadableDatabase();
		// 去除重复的班级列表
		String ss = "select * from student where name = '" + name + "'";
		Cursor cursor1 = database.rawQuery(ss, null);
		while (cursor1.moveToNext())
		{
			stu.add(new StudentBase(cursor1.getInt(cursor1.getColumnIndex("_id")),
					cursor1.getString(cursor1.getColumnIndex("name")),
					cursor1.getString(cursor1.getColumnIndex("stunum")),
					cursor1.getString(cursor1.getColumnIndex("coursename")),
					cursor1.getInt(cursor1.getColumnIndex("signFlag")),
					cursor1.getInt(cursor1.getColumnIndex("leaveFlag")),
					cursor1.getInt(cursor1.getColumnIndex("truantFlag")),
					cursor1.getInt(cursor1.getColumnIndex("countnum"))));
		}
		database.close();
		cursor1.close();
		dbHelper.close();
		return stu;
	}

}
