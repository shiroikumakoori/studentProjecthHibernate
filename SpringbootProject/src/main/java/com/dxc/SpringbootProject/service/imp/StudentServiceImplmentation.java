package com.dxc.SpringbootProject.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dxc.SpringbootProject.entity.Student;
import com.dxc.SpringbootProject.service.IStudentService;
import com.dxc.SpringbootProject.repository.*;
@Service
public class StudentServiceImplmentation implements IStudentService {

	private StudentRepository studentRepository;
	
	public StudentServiceImplmentation(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);	
	}

}
