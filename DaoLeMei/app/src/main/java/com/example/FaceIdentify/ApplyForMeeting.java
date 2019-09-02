package com.example.FaceIdentify;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.DBHelper;
import com.example.database.Student;
import com.example.rollcall.MainActivity;
import com.example.rollcall.R;
import com.example.rollcall.Topbar;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Cactus on 2018/2/10.
 */

public class ApplyForMeeting extends Activity implements View.OnClickListener {
    private EditText et_meeting;
    private Button button_download;
    private List<Student> Stus1=new ArrayList<>();
    private int flag_B2D = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_meeting);
        Topbar topBar = (Topbar) findViewById(R.id.topbar_applyForMeeting);
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
        et_meeting = (EditText) findViewById(R.id.et_meeting);
        button_download = (Button) findViewById(R.id.button_download);
        button_download.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_download:
                if(flag_B2D == 0){
                    String s = et_meeting.getText().toString();
                    LoadContent(s);
                    B2D(this);
                }else{
                    ShowToast("您已成功导入数据库，无需重复下载！");
                }
        }
    }
    private void LoadContent(String course)
    {
        BmobQuery<Student> query =new BmobQuery<>();
        query.addWhereEqualTo("courseName", course);
        query.findObjects(this, new FindListener<Student>() {
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
                mToast = Toast.makeText(this, text,
                        Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }
}
