/**
 * @Company:中享思途   
 * @Title:RoleService.java 
 * @Author:Administrator   
 * @Date:2020年2月6日 下午8:17:44     
 */ 
package com.situ.layoa.role.service;

import com.situ.layoa.role.domain.Role;

/** 
 * @ClassName:RoleService 
 * @Description:(角色类的逻辑层)  
 */
public interface RoleService {

	Long roleSave(Role role);
	
	Role findByName(String roleName);
}
