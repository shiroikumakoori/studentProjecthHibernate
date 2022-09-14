package com.microservice.department.controller;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;
import com.microservice.department.entity.Department;
import com.microservice.department.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department) {
        
    	System.out.println("Inside saveDepartment method of DepartmentController");
    	//logger.info("Inside saveDepartment method of DepartmentController");
        return  departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId) {
        System.out.println("Inside findDepartmentById method of DepartmentController");
    	//log.info("Inside findDepartmentById method of DepartmentController");
        return departmentService.findDepartmentById(departmentId);
    }

}