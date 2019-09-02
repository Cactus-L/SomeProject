package com.example.login;

import cn.bmob.v3.BmobUser;

/**
 * @ClassName: User
 * @Description: TODO
 * @author smile
 * @date 2014-9-25 下午5:16:07
 */
public class User extends BmobUser {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String workNum;
	private String relName;
	private String t_course;
	private String school;

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getT_course() {
		return t_course;
	}

	public void setT_course(String t_course) {
		this.t_course = t_course;
	}

	public String getWorkNum() {
		return workNum;
	}

	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}

	public String getRelName() {
		return relName;
	}

	public void setRelName(String relName) {
		this.relName = relName;
	}
}
