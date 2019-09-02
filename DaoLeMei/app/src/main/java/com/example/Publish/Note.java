package com.example.Publish;

import cn.bmob.v3.BmobObject;

/**
 * Created by Cactus on 2017/10/5.
 */

public class Note extends BmobObject {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
