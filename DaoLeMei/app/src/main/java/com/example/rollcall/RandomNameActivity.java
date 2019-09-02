package com.example.rollcall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.database.StudentBase;
import com.example.service.MainService;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class RandomNameActivity extends Activity implements OnClickListener {
	// 服务
	private MainService service;

	// 随机的人数
	private TextView stunum;
	// 得到的姓名
	private TextView sname;
	// 学号
	private TextView snum;
	// 上一个
	private Button floatB;
	private Button nextB;
	// 返回主界面
	private Integer num;
	private List<StudentBase> list;
	// 产生随机数
	private List<Integer> nums;
	// 保存临时状态变量
	private int s = 0;
	private int i2 = 1;
	private int flag_i2 = 1;
	// 点名
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

	private TextView ran_now;
	// 点名完成之后返回界面传递的班级名
	private String coursename;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.random_name);
		flagClear();
		initview();
		getDate();
		stunum.setText(num + "");
		ran_now.setText(1 + "");
		makeRandom(num);
		showname(nums.get(0));

		Topbar topBar = (Topbar) findViewById(R.id.topbar_random);
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

	private void makeRandom(int num2)
	{
		Random r = new Random();
		nums = new ArrayList<Integer>();
		while (nums.size() < num2)
		{
			Integer d = r.nextInt(list.size());
			if (!nums.contains(d))
			{ // 判断不重复
				nums.add(d);
			}
		}
//		for (Integer d : nums)
//		{
//			System.out.println("依次产生的随机数为： " + d);
//		}
	}

	private void getDate()
	{
		Bundle bundle = getIntent().getExtras();
		num = Integer.parseInt(bundle.getString("num"));
		coursename = bundle.getString("coursename");
		list =getStudent();
	}

	private void initview()
	{
		service = new MainService();
		stunum = (TextView) findViewById(R.id.ran_all);
		ran_now = (TextView) findViewById(R.id.ran_now);
		sname = (TextView) findViewById(R.id.sname);
		snum = (TextView) findViewById(R.id.snumber);
		floatB = (Button) findViewById(R.id.floatname);
		nextB = (Button) findViewById(R.id.nextname);

		rg = (RadioGroup) findViewById(R.id.randomradio);
		checkin = (RadioButton) findViewById(R.id.checkIn);
		leave = (RadioButton) findViewById(R.id.leaveIn);
		crunk = (RadioButton) findViewById(R.id.crunkIn);

		floatB.setOnClickListener(this);
		nextB.setOnClickListener(this);

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

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v)
	{

		System.out.println("当前为： " + i2);
		switch (v.getId())
		{
		case R.id.floatname:
			if (i2 > 1)
			{
				floatB.setClickable(true);
				s = nums.get(i2--);
				showname(s);
				flagClear();
			} else
			{
				floatB.setClickable(false);
			}
			break;
		case R.id.nextname:
			if (rg.getCheckedRadioButtonId() != -1)
			{
				if (i2 < nums.size())
				{
					nextB.setClickable(true);
					//保存上一位学生信息
					orderResult(nums.get(i2-1));
					i2++;
					flag_i2 = i2;
					ran_now.setText(i2+"");
					s = nums.get(i2-1);
					showname(s);
					flagClear();
				}else if (i2 == nums.size()) {
					nextB.setClickable(false);
					new AlertDialog.Builder(this).setTitle("成功").setMessage("点名完成")
							.setPositiveButton("确定", new DialogInterface.OnClickListener()
							{
								public void onClick(DialogInterface dialog, int which)
								{
									jumps();
								}
							}).setNegativeButton("取消", null).show();
					flag_i2++;
					nextB.setText("完成");
				}
			}
			break;
		default:
			break;
		}

	}

	// 跳转到随机点名界面
	private void jumps()
	{
		Bundle b2 = new Bundle();
		b2.putString("coursename", coursename);
		Intent i3 = new Intent();
		i3.putExtras(b2);
		i3.setClass(this, CheckRollcallMethod.class);
		startActivity(i3);
	}

	// 显示姓名到界面上
	private void showname(int s)
	{
		rg.clearCheck();
		sname.setText(list.get(s).getName());
		snum.setText(list.get(s).getStuNum());
	}

	// 停止返回到主界面
	public void stopActivity(Context con)
	{
		Intent i2 = new Intent();
		// 清空之前的activity
		i2.addFlags(i2.FLAG_ACTIVITY_CLEAR_TOP);
		i2.setClass(con, MainActivity.class);
		startActivity(i2);
	}

	// 得到点名的结果
	private void orderResult(int i1)
	{
		StudentBase s = list.get(i1);
		// 得到学生之前请假，旷课，签到的次数
		int sign = s.getSignFlag() + checkflag;
		int leave = s.getLeaveFlag() + leaveflag;
		int truant = s.getTruantFlag() + Truantflag;
		s.setSignFlag(sign);
		s.setLeaveFlag(leave);
		s.setTruantFlag(truant);
		boolean b = service.saveState(RandomNameActivity.this, s);
	}
	private ArrayList<StudentBase> getStudent()
	{
		Intent i = getIntent();
		Bundle bundle = i.getExtras();
		ArrayList<StudentBase>list = (ArrayList<StudentBase>) bundle.getSerializable("stuList");
		return list;
	}

	private void flagClear(){
		leaveflag = 0;
		checkflag = 0;
		Truantflag = 0;
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
}
