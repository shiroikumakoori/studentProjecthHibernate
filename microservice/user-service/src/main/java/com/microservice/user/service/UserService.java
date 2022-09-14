package com.microservice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.user.VO.Department;
import com.microservice.user.VO.ResponseTemplateVO;
import com.microservice.user.entity.User;
import com.microservice.user.repositiory.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public RestTemplate restTemplate;

    public User saveUser(User user) {
        //log.info("Inside saveUser of UserService");
        System.out.println("inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        //log.info("Inside getUserWithDepartment of UserService");
        System.out.println("inside getUserWithDepartment of UserService");
        System.out.println("userID : "+ userId );
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Department department =
                restTemplate.getForObject("http://localhost:8080/departments/" + user.getDepartmentId()
                        ,Department.class);

        vo.setUser(user);
        vo.setDepartment(department);

        return  vo;
    }
}
