package com.example.fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.database.DBHelper;
import com.example.database.StudentBase;
import com.example.rollcall.CheckRollcallMethod;
import com.example.rollcall.OrderNameActivity;
import com.example.rollcall.R;
import com.example.service.MainService;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
/**
 * 
 * @author Cactus
 *
 */
public class ShowClassFragment extends Fragment {
	// 服务
	private List<StudentBase> classLists;
	private List<StudentBase> classList;
	private String coursename;
	private MainService mainService;
	private static DBHelper dbHelper;
	private static SQLiteDatabase database;
	private ListView listview;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.show_class, container, false);
	}

	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		classLists = getAllStu(this.getActivity());
		String s = "" + classLists.size();
		showP(classLists);
	}

	// 添加到班级列表里
	private void showP(List<StudentBase> classL)
	{
		listview = (ListView) getActivity().findViewById(R.id.listViews);
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (StudentBase s : classL)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("coursename", s.getCourseName());
			list.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.c_item3, new String[] { "coursename" },
				new int[] { R.id.coursenames });
		listview.setAdapter(adapter);

		// 点击班级的处理事件
		listview.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				// parent是被点击的那个ListView
				ListView l = (ListView) parent;
				HashMap<String, Object> item = (HashMap<String, Object>) l.getItemAtPosition(position);
				// 获取被选中行对应的ID
				Object scoreId = item.get("coursename");
				String s = (String) scoreId;
				mainService = new MainService();
				List<StudentBase> classLists = new ArrayList<StudentBase>();
				classList = mainService.getStuList(getActivity(), s);
				Bundle b = new Bundle();
				b.putSerializable("stuList", (Serializable) classList);
				Intent i = new Intent();
				i.putExtras(b);
				i.setClass(getActivity(), CheckRollcallMethod.class);
				startActivity(i);
			}
		});
	}
	// 得到学生
	public List<StudentBase> getStudent()
	{
		return classLists;
	}
	// 获取所有的学生表
	public static List<StudentBase> getAllStu(Context con)
	{
		List<StudentBase> classLists = new ArrayList<StudentBase>();
		dbHelper = new DBHelper(con, "rollcall.db");
		database = dbHelper.getReadableDatabase();
		// 去除重复的班级列表
		String ss = "select * from student where _id in (select max(_id) from student group by coursename)";
		Cursor cursor = database.rawQuery(ss, null);
		while (cursor.moveToNext())
		{
			classLists.add(new StudentBase(cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("stunum")),
					cursor.getString(cursor.getColumnIndex("coursename")),cursor.getString(cursor.getColumnIndex("objectId"))));
		}
		database.close();
		cursor.close();
		return classLists;
	}


}
