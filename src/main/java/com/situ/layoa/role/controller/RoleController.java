/**
 * @Company:中享思途   
 * @Title:RoleController.java 
 * @Author:Administrator   
 * @Date:2020年2月6日 下午8:15:37     
 */ 
package com.situ.layoa.role.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.layoa.role.domain.Role;
import com.situ.layoa.role.service.RoleService;

/** 
 * @ClassName:RoleController 
 * @Description:(角色类的controller层)  
 */
@RestController
@RequestMapping("/role")
public class RoleController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGE_INDEX_ROLE = "role/role_add";

	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public ModelAndView goRoleAdd(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_INDEX_ROLE);
		return modelAndView;
	}
	
	@PostMapping
	public Long RoleSave(Role role) {
		return roleService.roleSave(role);
	}
	
	@GetMapping("/checkname")
	public Integer checkRoleName(String roleName) {
		Integer result = 1;
		Role role = roleService.findByName(roleName);
//		如果能查到角色，则角色名不可用
		if(role !=null ) {
			result = 0;
		}
		return result;
	}
}
