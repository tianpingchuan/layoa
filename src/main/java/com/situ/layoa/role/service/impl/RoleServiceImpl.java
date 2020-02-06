/**
 * @Company:中享思途   
 * @Title:RoleServiceImpl.java 
 * @Author:Administrator   
 * @Date:2020年2月6日 下午8:19:27     
 */ 
package com.situ.layoa.role.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.layoa.role.dao.RoleDao;
import com.situ.layoa.role.domain.Role;
import com.situ.layoa.role.service.RoleService;

/** 
 * @ClassName:RoleServiceImpl 
 * @Description:(这里用一句话描述这个类的作用)  
 */
@Service
public class RoleServiceImpl implements Serializable, RoleService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RoleDao roleDao;
	
	/** 
	 * @Title: roleSave 
	 * @Description:(保存角色实例)
	 * @param role
	 * @return  
	 */
	@Override
	public Long roleSave(Role role) {
		role.setActiveFlag(1);
		role.setCreateBy("admin");
		role.setCreateDate(new Date());
		return roleDao.save(role);
	}

	/** 
	 * @Title: findByName 
	 * @Description:(通过name查找角色实例)
	 * @param roleName
	 * @return  
	 */  
	@Override
	public Role findByName(String roleName) {
		return roleDao.findByName(roleName);
	}

}
