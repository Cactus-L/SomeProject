package com.example.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rollcall.CheckRollcallMethod;
import com.example.rollcall.MainActivity;
import com.example.rollcall.R;
import com.example.rollcall.Topbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;


public class RegisterActivity extends Activity implements OnClickListener{

	Button btn_register;
	EditText et_username, et_password, et_email,et_workNum,et_relName,et_school;
	private TextView chooseCourse;
	private ListView lv_chooseCourse;
	String teach_course;
	private List<String> courseList=new ArrayList<>();
	private int flag_chooseCourse=0;
	private int flag1 = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		Bmob.initialize(this,"3dc18b2700a2c36dfcb5b1bc5d9876a9");
		Topbar topBar = (Topbar) findViewById(R.id.topbar_register);
		topBar.setOnLeftAndRightClickListener(new Topbar.OnLeftAndRightClickListener() {
			@Override
			public void OnLeftButtonClick() {
				Intent i = getIntent();
				i.setClass(getApplicationContext(),LoginActivity.class);
				startActivity(i);
			}

			@Override
			public void OnRightButtonClick() {
			}
		});
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		et_email = (EditText) findViewById(R.id.et_email);
		et_workNum = (EditText) findViewById(R.id.et_workNum);
		et_relName = (EditText) findViewById(R.id.et_relName);
		et_school = (EditText) findViewById(R.id.et_school);
		chooseCourse = (TextView) findViewById(R.id.chooseCourse);
		lv_chooseCourse = (ListView) findViewById(R.id.lv_chooseCourse);
		btn_register = (Button) findViewById(R.id.btn_register);
		chooseCourse.setOnClickListener(this);
		btn_register.setOnClickListener(this);

	}

	private void register() {

		String name = et_username.getText().toString();
		String password = et_password.getText().toString();
		String pwd_again = et_email.getText().toString();
		String work_num = et_workNum.getText().toString();
		String rel_name = et_relName.getText().toString();
		String school = et_school.getText().toString();

		if (TextUtils.isEmpty(name)) {
			ShowToast("用户名不允许为空！");
			return;
		}

		if (TextUtils.isEmpty(password)) {
			ShowToast("密码不允许为空！");
			return;
		}
		if (!pwd_again.equals(password)) {
			ShowToast("密码前后不一致！");
			return;
		}
		if (TextUtils.isEmpty(school)) {
			ShowToast("学校名不允许为空！");
			return;
		}
		if (TextUtils.isEmpty(work_num)) {
			ShowToast("职工号不允许为空！");
			return;
		}
		if (TextUtils.isEmpty(rel_name)) {
			ShowToast("姓名不允许为空！");
			return;
		}
		if (TextUtils.isEmpty(teach_course)) {
			ShowToast("请选择您的授课课程！");
			return;
		}
		boolean isNetConnected = CommonUtils.isNetworkAvailable(this);
		if (!isNetConnected) {
			ShowToast("当前网络不可用，请检查您的网络！");
			return;
		}

		final ProgressDialog progress = new ProgressDialog(
				RegisterActivity.this);
		progress.setMessage("正在注册...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();

		final User bu = new User();
		bu.setUsername(name);
		bu.setPassword(password);
		bu.setWorkNum(work_num);
		bu.setRelName(rel_name);
		bu.setSchool(school);
		bu.setT_course(teach_course);
		System.out.println("user: name:"+bu.getUsername()+" password:"+bu.getPassword()+" work_num:"+bu.getWorkNum()+" relname:"+bu.getRelName()+" teach_course"+bu.getT_course());
		bu.signUp(RegisterActivity.this, new SaveListener() {
			@Override
			public void onSuccess() {
				progress.dismiss();
				ShowToast("注册成功");
				// 启动主页
				Intent intent = new Intent(RegisterActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}

			@Override
			public void onFailure(int i, String s) {
				ShowToast("注册失败!(原因："+s+")" );
				progress.dismiss();
			}
		});
	}
	Toast mToast;
	public void ShowToast(String text) {
		if (!TextUtils.isEmpty(text)) {
			if (mToast == null) {
				mToast = Toast.makeText(this, text,
						Toast.LENGTH_SHORT);
			} else {
				mToast.setText(text);
			}
			mToast.show();
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.btn_register:
					register();
				break;
			case R.id.chooseCourse:
				if(flag_chooseCourse == 0){
					getCourse();
					showP(courseList);
					flag_chooseCourse = 1;
				}else{
					final ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
					SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.c_item3,
							new String[] { "coursename" }, new int[] { R.id.coursenames });
					lv_chooseCourse.setAdapter(adapter);
					flag_chooseCourse = 0;
				}
				break;
			default:
				break;
		}
	}
	private void getCourse() {
		if (flag1 == 0) {
			courseList.add("操作系统");
			courseList.add("大学物理");
			courseList.add("高等数学2");
			courseList.add("UML建模");
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
		SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.c_item3, new String[] { "coursename" },
				new int[] { R.id.coursenames });
		lv_chooseCourse.setAdapter(adapter);
		// 点击班级的处理事件
		lv_chooseCourse.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				// parent是被点击的那个ListView
				ListView l = (ListView) parent;
				HashMap<String, Object> item = (HashMap<String, Object>) l.getItemAtPosition(position);
				// 获取被选中行对应的ID
				Object scoreId = item.get("coursename");
				teach_course = (String) scoreId;
				chooseCourse.setText(teach_course);
				list.clear();
				SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), list, R.layout.c_item3,
						new String[] { "coursename" }, new int[] { R.id.coursenames });
				lv_chooseCourse.setAdapter(adapter);
				flag_chooseCourse = 0;
			}
		});
	}
}
