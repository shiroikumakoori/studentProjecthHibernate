package com.microservice.department.repositiory;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.microservice.department.entity.*;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

	Department findByDepartmentId(Long departmentId);

}
