package com.shopcar.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {
    private int userId;
    private String userName;
    List<ReceiveMsg> receiveMsgs = new ArrayList<>();// 用户用于接收商品的相关信息

    public Customer(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
    public ReceiveMsg getDefaultReceiveMsg(){
        ReceiveMsg receiveMsg = null;
        Iterator it = this.getReceiveMsgs().iterator();
        while(it.hasNext()){
            receiveMsg = (ReceiveMsg) it.next();
            if (receiveMsg.isUserDefault()){
                return receiveMsg;
            }
        }
        return receiveMsg;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ReceiveMsg> getReceiveMsgs() {
        return receiveMsgs;
    }

    public void setReceiveMsgs(List<ReceiveMsg> receiveMsgs) {
        this.receiveMsgs = receiveMsgs;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", receiveMsgs=" + receiveMsgs +
                '}';
    }
}
