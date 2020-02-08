/**
 * @Company:中享思途   
 * @Title:RoleController.java 
 * @Author:Administrator   
 * @Date:2020年2月6日 下午8:15:37     
 */ 
package com.situ.layoa.role.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.layoa.commons.LayResult;
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
	private static final String FORM_ROLE_ADD_EDIT = "role/role_add_edit";

	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public ModelAndView goRoleAdd(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_INDEX_ROLE);
		return modelAndView;
	}
	
	@GetMapping("/goadd")
	public ModelAndView goAdd(ModelAndView modelAndView) {
		modelAndView.setViewName(FORM_ROLE_ADD_EDIT);
		return modelAndView;
	}
	
	@GetMapping("/goupdate/{rowId}")
	public ModelAndView goUpdate(ModelAndView modelAndView,@PathVariable("rowId")Long rowId) {
		modelAndView.addObject("role", roleService.getByID(rowId));
		modelAndView.setViewName(FORM_ROLE_ADD_EDIT);
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
	
	@GetMapping("/find/{page}/{limit}")
	public LayResult findAllRole(@PathVariable Integer page,@PathVariable Integer limit) {
		System.out.println(page);
		System.out.println(limit);
//		查询表中数据的数量
		Integer count = roleService.getCount();
//		进行分页
//		List<Role> roleList = roleService.findAllRole();
		List<Role> roleList = roleService.findRoleByPage(page,limit);
		return new LayResult(0,"",count,roleList);
	}
	
	@DeleteMapping("/{rowId}")
	public Integer doDelete(@PathVariable("rowId")Long rowId) {
		roleService.doDelete(rowId);
		return 1;
	}
	
	@PutMapping
	public Integer doupdate(Role role) {
		roleService.doUpdate(role);
		return 1;
	}
	
}
