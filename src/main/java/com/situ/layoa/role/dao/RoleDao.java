/**
 * @Company:中享思途   
 * @Title:RoleDao.java 
 * @Author:Administrator   
 * @Date:2020年2月6日 下午8:13:25     
 */ 
package com.situ.layoa.role.dao;

import org.springframework.stereotype.Repository;

import com.situ.layoa.base.dao.BaseDao;
import com.situ.layoa.role.domain.Role;

/** 
 * @ClassName:RoleDao 
 * @Description:(角色类的Dao层)  
 */
@Repository
public interface RoleDao extends BaseDao<Role> {

	Role findByName(String roleName);
	
}
