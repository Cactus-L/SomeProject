package com.example.rollcall;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.StudentBase;
import com.example.fragment.ShowClassFragment;
import com.example.service.MainService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cactus on 2018/1/24.
 */

public class CheckRollcallMethod extends Activity implements View.OnClickListener{
    private Button bt_order,bt_random,bt_faceIdentification;
    private String coursename;
    private List<StudentBase> classList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_rollcall_method);
        bt_order=(Button)findViewById(R.id.bt_order);
        bt_random=(Button)findViewById(R.id.bt_random);
        bt_faceIdentification = (Button) findViewById(R.id.bt_faceIdentification);
        bt_order.setOnClickListener(this);
        bt_random.setOnClickListener(this);
        bt_faceIdentification.setOnClickListener(this);

        Topbar topBar = (Topbar) findViewById(R.id.topbar);
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


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_order:
                Intent i = getIntent();
                i.setClass(this,OrderNameActivity.class);
                startActivity(i);
                break;
            case R.id.bt_random:
                final EditText inputServer = new EditText(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请输入点名人数").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
                        .setNegativeButton("Cancel", null);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {

                    public void onClick(DialogInterface dialog, int which)
                    {
                        String s = inputServer.getText().toString();
                        jumps(s);
                    }
                });
                builder.show();
                break;
            case R.id.bt_faceIdentification:
                ShowToast("由于技术原因暂未实现");
                break;
            default:
                break;
        }

    }
    private void jumps(String s)
    {

        Bundle b2 = new Bundle();
        b2.putString("num", s);
        b2.putString("coursename", coursename);
        Intent i3 = getIntent();
        i3.putExtras(b2);
        i3.setClass(this, RandomNameActivity.class);
        startActivity(i3);
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
