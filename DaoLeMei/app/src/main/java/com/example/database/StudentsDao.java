package com.example.database;
/**
 * 操作数据库
 * @author Cactus
 *
 */

public interface StudentsDao {
	public void add(StudentBase s);

	public void delete(int id);

	public void updata(StudentBase s);

	public StudentBase findById(int id);
}
