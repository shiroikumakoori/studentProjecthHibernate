package com.dxc.SpringbootProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.SpringbootProject.entity.Student;



@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
}
