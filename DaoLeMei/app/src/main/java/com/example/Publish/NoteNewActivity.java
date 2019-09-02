package com.example.Publish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Publish.Note;
import com.example.rollcall.MainActivity;
import com.example.rollcall.R;
import com.example.rollcall.Topbar;

import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Cactus on 2017/12/10.
 */

public class NoteNewActivity extends Activity implements View.OnClickListener{
    private EditText editText_content;
    private TextView bt_publish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_new);
        editText_content= (EditText) findViewById(R.id.editText_content);
        bt_publish= (TextView) findViewById(R.id.bt_publish);
        bt_publish.setOnClickListener(this);

        Topbar topBar = (Topbar) findViewById(R.id.topbar_publish);
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
        switch (view.getId())
        {
            case R.id.bt_publish:
                String content=editText_content.getText().toString();
                if(!TextUtils.isEmpty(content))
                {
                    Note note=new Note();
                    note.setContent(content);
                    note.insertBatch(this, null, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            ShowToast("发布成功！");
                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                }
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
