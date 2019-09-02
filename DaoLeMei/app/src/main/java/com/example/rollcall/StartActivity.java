package com.example.rollcall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.HorizontalScrollView;

import com.example.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 开始界面
 * @author Cactus
 *
 */
public class StartActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.start);
		Handler x = new Handler();
		x.postDelayed(new splashhandler(), 2000);

	}

	class splashhandler implements Runnable {

		public void run()
		{
			startActivity(new Intent(getApplication(), LoginActivity.class));
			StartActivity.this.finish();
		}

	}
}
