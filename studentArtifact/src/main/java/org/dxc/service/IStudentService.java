package org.dxc.service;

import java.util.List;

import org.dxc.Bean.Student;

public interface IStudentService {
	Integer insert(Student s);
	List<Student> getAllStudent();
	Student getById(int id);
	void updateRecord(Student s);
	void deleteRecord(int d); 

}
