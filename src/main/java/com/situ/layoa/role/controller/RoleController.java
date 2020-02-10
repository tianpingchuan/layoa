/**
 * @Company:中享思途   
 * @Title:RoleController.java 
 * @Author:Administrator   
 * @Date:2020年2月6日 下午8:15:37     
 */ 
package com.situ.layoa.role.controller;

import java.io.Serializable;

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
	
	/**
	 * 
	 * @Title: goAdd 
	 * @Description:(进新增或修改页面)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/goadd")
	public ModelAndView goAdd(ModelAndView modelAndView) {
		modelAndView.setViewName(FORM_ROLE_ADD_EDIT);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: goUpdate 
	 * @Description:(进修改)
	 * @param rowId
	 * @return
	 */
	@GetMapping("/goupdate/{rowId}")
	public Role goUpdate(@PathVariable("rowId")Long rowId) {
		return roleService.getByID(rowId);
	}
	
	/**
	 * 
	 * @Title: RoleSave 
	 * @Description:(执行新增)
	 * @param role
	 * @return
	 */
	@PostMapping
	public Long RoleSave(Role role) {
		return roleService.roleSave(role);
	}
	
	/**
	 * 
	 * @Title: checkRoleName 
	 * @Description:(检测姓名的唯一性)
	 * @param roleName
	 * @return
	 */
	@GetMapping("/checkname")
	public Integer checkRoleName(String roleName) {
		return roleService.findByName(roleName);
	}
	
	/**
	 * 
	 * @Title: findAllRole 
	 * @Description:(根据分页查询数量)
	 * @param page 页号
	 * @param limit 每页显示的数据数量
	 * @return
	 */
	@GetMapping("/find/{page}/{limit}")
	public LayResult findAllRole(@PathVariable Integer page,@PathVariable Integer limit,Role searchRole) {
		return roleService.findRoleByPage(page, limit, searchRole);
	}
	
	/**
	 * 
	 * @Title: doDelete 
	 * @Description:(执行删除)
	 * @param rowId
	 * @return
	 */
	@DeleteMapping("/{rowId}")
	public Integer doDelete(@PathVariable("rowId")Long rowId) {
		return roleService.doDelete(rowId);
	}
	
	/**
	 * 
	 * @Title: doupdate 
	 * @Description:(执行修改)
	 * @param role
	 * @return
	 */
	@PutMapping
	public Integer doupdate(Role role) {
		return roleService.doUpdate(role);
	}
	
}
