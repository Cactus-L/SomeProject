package com.example.database;

import cn.bmob.v3.BmobObject;

/**
 * Created by Cactus on 2017/10/26.
 */

public class Student extends BmobObject {
    private String name;//姓名
    private String stuNum;//学号
    private String courseName;// 班级
    private String course;//课程名

    private int signFlag; // 签到
    private int leaveFlag;// 请假
    private int truantFlag;// 旷课
    private int countnum;// 总计点名次数

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(int signFlag) {
        this.signFlag = signFlag;
    }

    public int getLeaveFlag() {
        return leaveFlag;
    }

    public void setLeaveFlag(int leaveFlag) {
        this.leaveFlag = leaveFlag;
    }

    public int getTruantFlag() {
        return truantFlag;
    }

    public void setTruantFlag(int truantFlag) {
        this.truantFlag = truantFlag;
    }

    public int getCountnum() {
        return countnum;
    }

    public void setCountnum(int countnum) {
        this.countnum = countnum;
    }
    public Student(String name, String stuNum, String courseName, int signFlag, int leaveFlag, int truantFlag,
                       int countnum)
    {
        this.name = name;
        this.stuNum = stuNum;
        this.courseName = courseName;
        this.signFlag = signFlag;
        this.leaveFlag = leaveFlag;
        this.truantFlag = truantFlag;
        this.countnum = countnum;
    }
    public Student()
    {

    }
}
