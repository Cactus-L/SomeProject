package com.example.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.example.FaceIdentify.ApplyForMeeting;
import com.example.database.DBHelper;
import com.example.database.Student;
import com.example.database.StudentBase;
import com.example.login.LoginActivity;
import com.example.login.User;
import com.example.rollcall.R;
import com.example.rollcall.Topbar;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

import static android.R.id.list;

/**
 * 
 * @author Cactus
 *
 */
public class MoreFragment extends Fragment implements OnClickListener {
	private List<Student> Stus1=new ArrayList<>();
	// 删除按钮
	private Button loadButton;
	private Button callmeButton;
	private Button versionButton;
	private Button aboutButton;
	private Button bt_applyForMeeting;
	private ListView lv_courseList;
	private List<String> courseList=new ArrayList<>();
	private int flag_listView= 0;
	private int flag1= 0;
	private int flag_B2D = 0;
	private String s;
	private String str_course;
	private static String TAG = "MoreFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.main_more, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		getCourse(getActivity());
		Topbar topBar = (Topbar) getActivity().findViewById(R.id.topbar_more);
		topBar.setOnLeftAndRightClickListener(new Topbar.OnLeftAndRightClickListener() {
			@Override
			public void OnLeftButtonClick() {

			}

			@Override
			public void OnRightButtonClick() {
				BmobUser.logOut(getActivity());
				BmobUser currentUser = BmobUser.getCurrentUser(getActivity());
				Intent o = new Intent();
				o.setClass(getActivity(),LoginActivity.class);
				startActivity(o);
			}
		});
		loadButton=(Button)getActivity().findViewById(R.id.loadbutton);
		callmeButton=(Button)getActivity().findViewById(R.id.callme_bt);
		versionButton=(Button)getActivity().findViewById(R.id.version_bt);
		aboutButton=(Button)getActivity().findViewById(R.id.about_bt);
		bt_applyForMeeting = (Button) getActivity().findViewById(R.id.bt_applyForMeeting);
		lv_courseList = (ListView)getActivity().findViewById(R.id.lv_courseList);
		loadButton.setOnClickListener(this);
		callmeButton.setOnClickListener(this);
		versionButton.setOnClickListener(this);
		aboutButton.setOnClickListener(this);
		bt_applyForMeeting.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.loadbutton:
				if(flag_listView == 0){
					showP(courseList);
					flag_listView = 1;
				}else{
					final ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
					SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.c_item3,
							new String[] { "coursename" }, new int[] { R.id.coursenames });
					lv_courseList.setAdapter(adapter);
					flag_listView = 0;
				}

				break;
		case R.id.bt_applyForMeeting:
				Intent ii = new Intent();
				ii.setClass(getActivity(),ApplyForMeeting.class);
				startActivity(ii);
				break;
		case R.id.callme_bt:
				ShowToast("欢迎给我们建议和反馈，我们的联系邮箱是369458889@qq.com");
				break;
		case R.id.version_bt:
				ShowToast("当前版本为 “到了没V1.0.0”");
				break;
		case R.id.about_bt:
				Intent i = new Intent();
				i.setClass(getActivity(),About.class);
				startActivity(i);
				break;
		default:
			break;
		}
	}
	private void LoadContent(String course)
	{
		BmobQuery<Student> query =new BmobQuery<>();
		query.addWhereEqualTo("course", course);
		query.findObjects(getActivity(), new FindListener<Student>() {
			@Override
			public void onSuccess(List<Student> list) {
				Stus1=list;
			}

			@Override
			public void onError(int i, String s) {
				ShowToast("下载失败");
			}
		});
	}
	private void getCourse( Context context){
		if(flag1==0) {
			BmobQuery<User> query = new BmobQuery<>();
			query.addWhereEqualTo("username",(String)BmobUser.getCurrentUser(context).getUsername());
			query.findObjects(getActivity(), new FindListener<User>() {
				@Override
				public void onSuccess(List<User> list) {
					courseList.clear();
					for (User u : list) {
						if(u.getT_course() != null){
							courseList.add(u.getT_course());
							System.out.println("coursename:"+u.getT_course());
						}
					}
				}

				@Override
				public void onError(int i, String s) {
						ShowToast("课程下载失败！");
				}
			});
			flag1 = 1;
		}
	}
	private void showP(List<String> classL)
	{
		final ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (String l : classL)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("coursename", l);
			list.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.c_item3, new String[] { "coursename" },
				new int[] { R.id.coursenames });
		lv_courseList.setAdapter(adapter);
		// 点击班级的处理事件
		lv_courseList.setOnItemClickListener(new AdapterView.OnItemClickListener()
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
				if(flag_B2D == 0){
					LoadContent(s);
					B2D(getActivity());
				}else{
					ShowToast("您已成功下载，无需重复下载！");
				}
				list.clear();
				SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.c_item3,
						new String[] { "coursename" }, new int[] { R.id.coursenames });
				lv_courseList.setAdapter(adapter);
				flag_listView = 0;
			}
		});
	}
	private void B2D(Context con)
	{
		SQLiteDatabase db = new DBHelper(con, "rollcall.db").getWritableDatabase();
		for (Student stu : Stus1)
		{
			ContentValues cv = new ContentValues();
			cv.put("objectId",stu.getObjectId());
			cv.put("name", stu.getName());
			cv.put("stunum", stu.getStuNum());
			cv.put("coursename", stu.getCourseName());
			cv.put("signFlag", stu.getSignFlag());
			cv.put("leaveFlag", stu.getLeaveFlag());
			cv.put("truantFlag", stu.getTruantFlag());
			cv.put("countnum", stu.getCountnum());
			db.insert("student", null, cv);
			ShowToast("下载成功，已导入数据库");
			flag_B2D = 1;
		}
	}
	Toast mToast;
	public void ShowToast(String text) {
		if (!TextUtils.isEmpty(text)) {
			if (mToast == null) {
				mToast = Toast.makeText(getActivity(), text,
						Toast.LENGTH_SHORT);
			} else {
				mToast.setText(text);
			}
			mToast.show();
		}
	}
}