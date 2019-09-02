package com.example.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rollcall.MainActivity;
import com.example.rollcall.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;


/**
 * @ClassName: LoginActivity
 * @Description: TODO
 * @author smile
 * @date 2014-6-3 下午4:41:42
 */
public class LoginActivity extends Activity implements OnClickListener {

	EditText et_username, et_password;
	Button btn_login;
	TextView btn_register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Bmob.initialize(this,"3dc18b2700a2c36dfcb5b1bc5d9876a9");
		BmobUser bmobUser = BmobUser.getCurrentUser(this);
		if(bmobUser != null){
			Intent intent = new Intent(LoginActivity.this,MainActivity.class);
			startActivity(intent);
			// 允许用户使用应用
		}else{
			//缓存用户对象为空时， 可打开用户注册界面…
		}
		init();
	}

	private void init() {
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_register = (TextView) findViewById(R.id.btn_register);
		btn_login.setOnClickListener(this);
		btn_register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == btn_register) {
			Intent intent = new Intent(LoginActivity.this,
					RegisterActivity.class);
			startActivity(intent);
		} else {
			boolean isNetConnected = CommonUtils.isNetworkAvailable(this);
			if(!isNetConnected){
				ShowToast("当前网络不可用，请检查您的网络！");
				return;
			}
			login();
		}
	}
	private void login(){
		String name = et_username.getText().toString();
		String password = et_password.getText().toString();

		if (TextUtils.isEmpty(name)) {
			ShowToast("用户名不能为空");
			return;
		}

		if (TextUtils.isEmpty(password)) {
			ShowToast("密码不能为空");
			return;
		}

		final ProgressDialog progress = new ProgressDialog(LoginActivity.this);
		progress.setMessage("正在登陆...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		User user = new User();
		user.setPassword(password);
		user.setUsername(name);
		user.login(this, new SaveListener() {
			@Override
			public void onSuccess() {
				progress.dismiss();
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}

			@Override
			public void onFailure(int i, String s) {
				progress.dismiss();
				ShowToast("登录失败!(原因："+s+")");
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

}
