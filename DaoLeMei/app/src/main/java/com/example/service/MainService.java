package com.example.service;

import java.util.List;

import com.example.database.StudentBase;
import com.example.database.StudentDaoImpl;

import android.content.Context;

/**
 * 处理其它，在fragment或者activity中调用
 * @author Cactus
 *
 */
public class MainService {
	// 获取班级列表（教学班列表、花名册列表）
	public List<StudentBase> getStuList(Context con, String cname)
	{
		return StudentDaoImpl.getAllStu(con, cname);
	}

	public boolean saveState(Context con, StudentBase s)
	{
		return StudentDaoImpl.saveState(con, s);
	}

}
