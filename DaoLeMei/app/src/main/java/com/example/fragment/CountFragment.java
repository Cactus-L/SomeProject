package com.example.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.FaceIdentify.FaceRollcall;
import com.example.Publish.Note;
import com.example.Publish.NoteNewActivity;
import com.example.database.DBHelper;
import com.example.database.Student;
import com.example.database.StudentBase;
import com.example.rollcall.QueryActivity;
import com.example.rollcall.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import cn.bmob.v3.listener.UpdateListener;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
/**
 * 
 * @author Cactus
 *
 */
public class CountFragment extends Fragment implements OnClickListener {
	private Button three;
	private Button all;
	private Button query;
	private Button updateButton;
	private Button chatButton;
	private Button publishButton;
	private Button face_bt;
	private static List<StudentBase> classLists;
	private static List<StudentBase> stus;
	private static DBHelper dbHelper;
	private static SQLiteDatabase database;
	private ListView truantName;
	private ListView truantName1;
	private String s;
	private int flag=0;
	private int flag_listView1 = 0;
	private int flag_listView2 = 0;
	private String[] title = { "姓名", "学号", "课程", "签到次数", "请假次数", "旷课次数" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		System.out.println("CountFragment");
		return inflater.inflate(R.layout.main_count, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		stus = new ArrayList<StudentBase>();
		three = (Button) getActivity().findViewById(R.id.export_three);
		all = (Button) getActivity().findViewById(R.id.export_all);
		updateButton=(Button)getActivity().findViewById(R.id.updatebutton);
		query = (Button) getActivity().findViewById(R.id.quert_stu);
		chatButton=(Button)getActivity().findViewById(R.id.chat_bt);
		publishButton=(Button)getActivity().findViewById(R.id.publish_bt);
		truantName = (ListView) getActivity().findViewById(R.id.truantName);
		truantName1 = (ListView) getActivity().findViewById(R.id.truantName1);
		face_bt = (Button) getActivity().findViewById(R.id.face_bt);
		getClass(getActivity());
		three.setOnClickListener(this);
		all.setOnClickListener(this);
		query.setOnClickListener(this);
		updateButton.setOnClickListener(this);
		chatButton.setOnClickListener(this);
		publishButton.setOnClickListener(this);
		face_bt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.export_three:
			if(flag_listView1 == 0){
				showP2(classLists);
				flag_listView1 = 1;
			}else{
				final ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
				SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.c_item3,
						new String[] { "coursename" }, new int[] { R.id.coursenames });
				truantName.setAdapter(adapter);
				flag_listView1 = 0;
			}
			break;
		case R.id.export_all:
			if(flag_listView2 == 0){
				showP(classLists);
				flag_listView2 = 1;
			}else{
				final ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
				SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.c_item3,
						new String[] { "coursename" }, new int[] { R.id.coursenames });
				truantName1.setAdapter(adapter);
				flag_listView2 = 0;
			}
			break;
		case R.id.updatebutton:
			UpdateContent(getActivity());
			break;
		case R.id.quert_stu:
			Intent i = new Intent();
			i.setClass(getActivity(), QueryActivity.class);
			startActivity(i);
			break;
		case R.id.chat_bt:
			toast("精彩尽在“到了没V2.0.0” ");
			break;
		case R.id.publish_bt:
			Intent ii = new Intent();
			ii.setClass(getActivity(), NoteNewActivity.class);
			startActivity(ii);;
			break;
		case R.id.face_bt:
			Intent iii = new Intent();
			iii.setClass(getActivity(), FaceRollcall.class);
			startActivity(iii);;
			break;
		default:
			break;
		}
	}
	private void UpdateContent(Context con)
	{
		List<StudentBase> stusU = GetContent(con);
		final Student stu = new Student();
		if (stusU != null && !stusU.isEmpty())
		{
			for (int i = 0; i < stusU.size(); i++)
			{
				stu.setObjectId(stusU.get(i).getObjectId());
				stu.setStuNum(stusU.get(i).getStuNum());
				stu.setName(stusU.get(i).getName());
				stu.setCourseName(stusU.get(i).getCourseName());
				stu.setSignFlag(stusU.get(i).getSignFlag());
				stu.setTruantFlag(stusU.get(i).getTruantFlag());
				stu.setLeaveFlag(stusU.get(i).getLeaveFlag());
				stu.setCountnum(stusU.get(i).getCountnum());
				stu.update(con, stu.getObjectId(), new UpdateListener() {
					@Override
					public void onSuccess() {
						flag=1;
					}

					@Override
					public void onFailure(int i, String s) {

					}
				});
			}
		}
		if (flag==1)
		{
			toast("已将学生出勤信息同步到服务器！");
		}

	}
	public List<StudentBase> GetContent(Context con)
	{

		// 得到学生

		stus.clear();
		dbHelper = new DBHelper(con, "rollcall.db");
		database = dbHelper.getReadableDatabase();
		String sss = "select * from student";
		Cursor cursor1 = database.rawQuery(sss, null);
		while (cursor1.moveToNext())
		{
			stus.add(new StudentBase(cursor1.getString(cursor1.getColumnIndex("objectId")),
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

		StudentBase p =stus.get(1);
		System.out.println(p.getName()+":"+String.valueOf(p.getSignFlag())+"、"+
				String.valueOf(p.getLeaveFlag())+"、"+
				String.valueOf(p.getTruantFlag())+"、"+
				String.valueOf(p.getCountnum()));

		return stus;
	}



	/**
	 * 对象数据写入到Excel
	 */
	public void writeExcel(String s)
	{

		WritableWorkbook book = null;
		try
		{
//			String file = Environment.getExternalStorageDirectory().toString() + "/" + s + ".xls";
			String file = "/storage/emulated/0/RollCall/" + s + ".xls";
			System.out.println("+++++++++++++++++++++++++++"+file);
			// 打开文件
			book = Workbook.createWorkbook(new File(file));
			// 生成名为"学生"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("学生", 0);
			// 添加标题行
			for (int i = 0; i < title.length; i++)
			{
				sheet.addCell(new Label(i, 0, title[i]));

			}
			if (stus != null && !stus.isEmpty())
			{
				System.out.println(stus.size());
				for (int i = 1; i <= stus.size(); i++)
				{
					sheet.addCell(new Label(0, i, stus.get(i - 1).getName()));
					sheet.addCell(new Label(1, i, stus.get(i - 1).getStuNum()));
					sheet.addCell(new Label(2, i, stus.get(i - 1).getCourseName()));
					sheet.addCell(new Label(3, i, stus.get(i - 1).getSignFlag() + ""));
					sheet.addCell(new Label(4, i, stus.get(i - 1).getLeaveFlag() + ""));
					sheet.addCell(new Label(5, i, stus.get(i - 1).getTruantFlag() + ""));
				}
			}
			// 写入数据并关闭文件
			book.write();
		} catch (Exception e)
		{
			System.out.println(e);
		} finally
		{
			if (book != null)
			{
				try
				{
					book.close();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}

	}

	/*
	 * 将班级信息添加到班级列表里 并进行操作
	 */
	private void showP(List<StudentBase> classL)
	{
		final ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (StudentBase s : classL)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("coursename", s.getCourseName());
			list.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.c_item3, new String[] { "coursename" },
				new int[] { R.id.coursenames });
		truantName1.setAdapter(adapter);

		// 点击班级的处理事件
		truantName1.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				// parent是被点击的那个ListView
				ListView l = (ListView) parent;
				HashMap<String, Object> item = (HashMap<String, Object>) l.getItemAtPosition(position);
				// 获取被选中行对应的ID
				Object scoreId = item.get("coursename");
				s = (String) scoreId;
				// 弹出删除确定
				new AlertDialog.Builder(getActivity()).setTitle("导出班级").setMessage(s)
						.setPositiveButton("确定", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						stus = getStus(s, getActivity());
						writeExcel(s);
						new AlertDialog.Builder(getActivity()).setTitle("导出班级").setMessage("成功")
								.setPositiveButton("确定", new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog, int which)
							{
								list.clear();
								SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.c_item3,
										new String[] { "coursename" }, new int[] { R.id.coursenames });
								truantName1.setAdapter(adapter);
								flag_listView2 = 0;
							}

						}).setNegativeButton("取消", null).show();
					}

				}).setNegativeButton("取消", null).show();

			}
		});
	}

	/*
	 * 得到选中班级的学生
	 */
	public List<StudentBase> getStus(String classname, Context con)
	{

		// 得到学生

		stus.clear();
		dbHelper = new DBHelper(con, "rollcall.db");
		database = dbHelper.getReadableDatabase();
		String sss = "select * from student where coursename = '" + classname + "'";
		Cursor cursor1 = database.rawQuery(sss, null);
		while (cursor1.moveToNext())
		{
			stus.add(new StudentBase(cursor1.getInt(cursor1.getColumnIndex("_id")),
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

		StudentBase p =stus.get(1);
		System.out.println(p.getName()+":"+String.valueOf(p.getSignFlag())+"、"+
				String.valueOf(p.getLeaveFlag())+"、"+
				String.valueOf(p.getTruantFlag())+"、"+
				String.valueOf(p.getCountnum()));

		return stus;
	}

	/*
	 * 得到所有的班级
	 */
	public static List<StudentBase> getClass(Context con)
	{
		// 得到班级
		classLists = new ArrayList<StudentBase>();
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
		dbHelper.close();
		return classLists;
	}

	private void toast(CharSequence hint)
	{
		Toast.makeText(getActivity(), hint, Toast.LENGTH_SHORT).show();
	}

	/*
	 * 将班级信息添加到班级列表里 并进行操作
	 */
	private void showP2(List<StudentBase> classL)
	{
		final ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (StudentBase s : classL)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("coursename", s.getCourseName());
			list.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.c_item3, new String[] { "coursename" },
				new int[] { R.id.coursenames });
		truantName.setAdapter(adapter);

		// 点击班级的处理事件
		truantName.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				// parent是被点击的那个ListView
				ListView l = (ListView) parent;
				HashMap<String, Object> item = (HashMap<String, Object>) l.getItemAtPosition(position);
				// 获取被选中行对应的ID
				Object scoreId = item.get("coursename");
				s = (String) scoreId;
				// 弹出删除确定
				new AlertDialog.Builder(getActivity()).setTitle("导出旷课名单").setMessage(s + "旷课名单")
						.setPositiveButton("确定", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						stus = getStus_truank(s, getActivity());
						writeExcel(s + "旷课");
						// 弹出删除确定
						new AlertDialog.Builder(getActivity()).setTitle("导出").setMessage("成功")
								.setPositiveButton("确定", new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog, int which)
							{
								list.clear();
								SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.c_item3,
										new String[] { "coursename" }, new int[] { R.id.coursenames });
								truantName.setAdapter(adapter);
								flag_listView1 = 0;
							}

						}).setNegativeButton("取消", null).show();
					}

				}).setNegativeButton("取消", null).show();

			}
		});
	}

	/*
	 * 得到选中班级的学生 根据旷课信息查询出的学生
	 */
	public List<StudentBase> getStus_truank(String classname, Context con)
	{
		stus.clear();
		// 得到学生
		stus = new ArrayList<StudentBase>();
		dbHelper = new DBHelper(con, "rollcall.db");
		database = dbHelper.getReadableDatabase();
		String sss = "select * from student where coursename = '" + classname + "' and truantFlag > 0";
		Cursor cursor1 = database.rawQuery(sss, null);
		while (cursor1.moveToNext())
		{
			stus.add(new StudentBase(cursor1.getInt(cursor1.getColumnIndex("_id")),
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
		return stus;
	}

}