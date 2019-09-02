package com.example.rollcall;

import com.example.database.DBHelper;
import com.example.fragment.FragmentAdapter;
import com.example.rollcall.R;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

import cn.bmob.v3.Bmob;

/**
 * 主界面*
 * @author Cactus
 *
 */
public class MainActivity extends FragmentActivity implements OnClickListener {

	private DBHelper db;
	public static final int TAB_CALL = 0;
	public static final int TAB_COUNT = 1;
	public static final int TAB_MORE = 2;

	private ViewPager viewPager;
	private RadioButton main_tab_call;
	private RadioButton main_tab_count;
	private RadioButton main_tab_more;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Bmob.initialize(this,"3dc18b2700a2c36dfcb5b1bc5d9876a9");
		db = new DBHelper(this, "rollcall");
		db.getWritableDatabase();
		initView();
		addListener();
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	private void initView()
	{
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		main_tab_call = (RadioButton) findViewById(R.id.main_tab_call);
		main_tab_count = (RadioButton) findViewById(R.id.main_tab_count);
		main_tab_more = (RadioButton) findViewById(R.id.main_tab_more);

		main_tab_call.setOnClickListener(this);
		main_tab_count.setOnClickListener(this);
		main_tab_more.setOnClickListener(this);

		FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	private void addListener()
	{
		viewPager.setOnPageChangeListener(new OnPageChangeListener()
		{
			@Override
			public void onPageSelected(int id)
			{
				switch (id)
				{
				case TAB_CALL:
					main_tab_call.setChecked(true);
					break;
				case TAB_COUNT:
					main_tab_count.setChecked(true);
					break;
				case TAB_MORE:
					main_tab_more.setChecked(true);
					break;
				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{

			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{
				// TODO Auto-generated method stub

			}
		});
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.main_tab_call:
			viewPager.setCurrentItem(TAB_CALL);
			break;
		case R.id.main_tab_count:
			viewPager.setCurrentItem(TAB_COUNT);
			break;
		case R.id.main_tab_more:
			viewPager.setCurrentItem(TAB_MORE);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		// TODO Auto-generated method stub
		// super.onSaveInstanceState(outState);
	}
}
