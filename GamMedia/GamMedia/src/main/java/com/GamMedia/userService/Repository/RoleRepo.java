package com.GamMedia.userService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GamMedia.userService.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

}
