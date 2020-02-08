/**
 * @Company:中享思途   
 * @Title:RoleService.java 
 * @Author:Administrator   
 * @Date:2020年2月6日 下午8:17:44     
 */ 
package com.situ.layoa.role.service;

import java.util.List;

import com.situ.layoa.role.domain.Role;

/** 
 * @ClassName:RoleService 
 * @Description:(角色类的逻辑层)  
 */
public interface RoleService {

	Long roleSave(Role role);
	
	Role findByName(String roleName);
	
	List<Role> findAllRole();
	
	Integer getCount();
	
	Integer doDelete(Long rowId);
	
	Integer doUpdate(Role role);
	
	Role getByID(Long rowId);

	List<Role> findRoleByPage(Integer page, Integer limit);
}
