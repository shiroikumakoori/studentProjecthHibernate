package com.dxc.SpringbootProject.service;

import java.util.List;

import com.dxc.SpringbootProject.entity.*;

public interface IStudentService {
	List<Student> getAllStudents();

	Student saveStudent(Student student);

	Student getStudentById(Long id);

	Student updateStudent(Student student);

	void deleteStudentById(Long id);
}
