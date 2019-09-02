package com.example.database;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 对象类
 * @author Cactus
 *
 */
public class StudentBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int _id;
	private String stuNum;
	private String courseName;// 课程名
	private String objectId;
	private String course;
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
	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public StudentBase(int _id, String name, String stuNum, String courseName,String objectId)
	{
		super();
		this.name = name;
		this._id = _id;
		this.stuNum = stuNum;
		this.courseName = courseName;
		this.objectId=objectId;
	}

	public StudentBase()
	{
		super();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getSignFlag()
	{
		return signFlag;
	}

	public void setSignFlag(int signFlag)
	{
		this.signFlag = signFlag;
	}

	public int getLeaveFlag()
	{
		return leaveFlag;
	}

	public void setLeaveFlag(int leaveFlag)
	{
		this.leaveFlag = leaveFlag;
	}

	public int getTruantFlag()
	{
		return truantFlag;
	}

	public void setTruantFlag(int truantFlag)
	{
		this.truantFlag = truantFlag;
	}

	public int getCountnum()
	{
		return countnum;
	}

	public void setCountnum(int countnum)
	{
		this.countnum = countnum;
	}

	public String getCourseName()
	{
		return courseName;
	}

	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}

	public String getStuNum()
	{
		return stuNum;
	}

	public void setStuNum(String stuNum)
	{
		this.stuNum = stuNum;
	}

	public StudentBase(int _id, String name, String stuNum, String courseName, int signFlag, int leaveFlag, int truantFlag,
                       int countnum)
	{
		super();
		this.name = name;
		this._id = _id;
		this.stuNum = stuNum;
		this.courseName = courseName;
		this.signFlag = signFlag;
		this.leaveFlag = leaveFlag;
		this.truantFlag = truantFlag;
		this.countnum = countnum;
	}
	public StudentBase(String objectId, String name, String stuNum, String courseName, int signFlag, int leaveFlag, int truantFlag,
					   int countnum)
	{
		super();
		this.objectId=objectId;
		this.name = name;
		this.stuNum = stuNum;
		this.courseName = courseName;
		this.signFlag = signFlag;
		this.leaveFlag = leaveFlag;
		this.truantFlag = truantFlag;
		this.countnum = countnum;
	}

	public StudentBase(int _id, String name, String stuNum, String courseName, int signFlag, int leaveFlag, int truantFlag)
	{
		super();
		this.name = name;
		this._id = _id;
		this.stuNum = stuNum;
		this.courseName = courseName;
		this.signFlag = signFlag;
		this.leaveFlag = leaveFlag;
		this.truantFlag = truantFlag;
	}

	public int get_id()
	{
		return _id;
	}

	public void set_id(int _id)
	{
		this._id = _id;
	}

}
