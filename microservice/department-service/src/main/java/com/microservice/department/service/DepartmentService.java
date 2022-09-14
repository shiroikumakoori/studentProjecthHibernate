package com.microservice.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.department.entity.Department;
import com.microservice.department.repositiory.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentRepository.save(department);
	}

	public Department findDepartmentById(Long departmentId) {
		// TODO Auto-generated method stub
		return departmentRepository.findByDepartmentId(departmentId);
	}
	
}
