package com.example.rollcall;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.database.Student;
import com.example.database.StudentBase;
import com.example.fragment.CallFragment;
import com.example.service.MainService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import cn.bmob.v3.listener.UpdateListener;

/**
 * 顺序点名的界面
 * @author Cactus
 *
 */
public class OrderNameActivity extends Activity implements OnClickListener {
	// 学生姓名
	private TextView stus_name;
	// 学生学号
	private TextView stus_num;
	// 学生总数
	private TextView stus_all;
	// 当前学生个数
	private TextView stus_now;
	private RadioGroup rg;
	// 签到
	private RadioButton checkin;
	private int checkflag = 0;
	// 请假
	private RadioButton leave;
	private int leaveflag = 0;
	// 旷课
	private RadioButton crunk;
	private int Truantflag = 0;
	// 上一名学生
	private Button floatname;
	// 下一名学生
	private Button nextname;
	// 学生列表
	private ArrayList<StudentBase> stulist;
	// 存放学生数据
	private ArrayList<HashMap<String, Object>> textList;
	// 学生增加
	private int i = 0;
	// 服务
	private int n=0;
	private MainService service;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_name);
		initview();
		textdeal();
		showname();

		Topbar topBar = (Topbar) findViewById(R.id.topbar_order);
		topBar.setOnLeftAndRightClickListener(new Topbar.OnLeftAndRightClickListener() {
			@Override
			public void OnLeftButtonClick() {
				Intent i = getIntent();
				i.setClass(getApplicationContext(),CheckRollcallMethod.class);
				startActivity(i);
			}

			@Override
			public void OnRightButtonClick() {
			}
		});
	}

	private void textdeal()
	{
		// 得到学生列表
		stulist = getinfo();
		textList = new ArrayList<HashMap<String, Object>>();
		for (StudentBase s : stulist)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("_id", s.get_id());
			map.put("name", s.getName());
			map.put("stunum", s.getStuNum());
			map.put("coursename", s.getCourseName());
			map.put("signflag", s.getSignFlag());
			map.put("leaveFlag", s.getLeaveFlag());
			map.put("truantFlag", s.getTruantFlag());
			textList.add(map);
		}
	}

	// 得到学生的信息
	private ArrayList<StudentBase> getinfo()
	{
		Intent i = getIntent();
		Bundle bundle = i.getExtras();
		ArrayList<StudentBase>list = (ArrayList<StudentBase>) bundle.getSerializable("stuList");
		StudentBase p =list.get(1);
		return list;
	}

	private void initview()
	{
		service = new MainService();
		// 显示信息
		stus_name = (TextView) findViewById(R.id.stus_name);
		stus_num = (TextView) findViewById(R.id.stus_num);
		stus_all = (TextView) findViewById(R.id.stus_all);
		stus_now = (TextView) findViewById(R.id.stus_now);

		rg = (RadioGroup) findViewById(R.id.orderradio);
		checkin = (RadioButton) findViewById(R.id.checkin);
		leave = (RadioButton) findViewById(R.id.leave);
		crunk = (RadioButton) findViewById(R.id.crunk);
		floatname = (Button) findViewById(R.id.floatname);
		nextname = (Button) findViewById(R.id.nextname);
		// 添加点击事件

		floatname.setOnClickListener(this);
		nextname.setOnClickListener(this);

		rg.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				if (checkedId == checkin.getId())
				{
					checkflag = 1;
				} else if (checkedId == leave.getId())
				{
					leaveflag = 1;
				} else if (checkedId == crunk.getId())
				{
					Truantflag = 1;
				}
			}
		});
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.floatname:
			if (i > 0)
			{
				floatname.setClickable(true);
				i--;
				n=i;
				showname();
			} else
			{
				floatname.setClickable(false);
			}
			break;
		case R.id.nextname:
			if (i < textList.size() - 1)
			{
				i++;
				n=i;
				showname();
				orderResult(i - 1);
				flagClear();
			} else if (i == textList.size() - 1)
			{
				n++;
				orderResult(i);
				flagClear();
			}
			break;
		default:
			break;
		}
		if(n==textList.size()-1)
		{
			nextname.setText("完成");
		}
		if(n==textList.size())
		{
			Intent intent=new Intent();
			intent.setClass(this,CallFragment.class);
			startActivity(intent);
		}
	}

	// 显示姓名到界面上
	private void showname()
	{
		rg.clearCheck();
		String s = (String) textList.get(i).get("name");
		String num = (String) textList.get(i).get("stunum");
		stus_name.setText(s);
		stus_num.setText(num);
		stus_all.setText(stulist.size() + "");
		stus_now.setText(i + 1 + "");
	}

	// 得到点名的结果
	private void orderResult(int i)
	{
		StudentBase s = stulist.get(i);
		// 得到学生之前请假，旷课，签到的次数
		int sign = s.getSignFlag() + checkflag;
		int leave = s.getLeaveFlag() + leaveflag;
		int truant = s.getTruantFlag() + Truantflag;
		s.setSignFlag(sign);
		s.setLeaveFlag(leave);
		s.setTruantFlag(truant);
		boolean b = service.saveState(this, s);
	}
	private void flagClear(){
		leaveflag = 0;
		checkflag = 0;
		Truantflag = 0;
	}
}
