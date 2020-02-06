/**
 * @Company:中享思途   
 * @Title:StudentController.java 
 * @Author:Administrator   
 * @Date:2020年2月6日 下午1:41:48     
 */ 
package com.situ.layoa.student.controller;

import java.io.Serializable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.situ.layoa.student.damain.Student;

/** 
 * @ClassName:StudentController 
 * @Description:(这里用一句话描述这个类的作用)  
 */
@RestController
@RequestMapping("/student")
public class StudentController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PostMapping
	public Long saveStudent(Student student) {
		System.out.println(student);
		return 1L;
	}
	
	@GetMapping("/checkname")
	/**
	 * 
	 * @Title: checkStuName 
	 * @Description:(学生名称唯一校验)
	 * @param stuName
	 * @return 1：可以使用；0：不可以使用
	 */
	public Integer checkStuName(String stuName) {
		System.out.println("校验");
		Integer result = 1;
//		默认一个逻辑，假定‘张三’已经存在
		if("张三".equals(stuName)) {
			result = 0;
		}
		return result;
	}

}
