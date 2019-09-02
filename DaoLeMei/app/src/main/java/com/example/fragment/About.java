package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.rollcall.MainActivity;
import com.example.rollcall.R;
import com.example.rollcall.Topbar;

/**
 * Created by Cactus on 2018/1/28.
 */

public class About extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        Topbar topBar = (Topbar) findViewById(R.id.topbar_about);
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
}
