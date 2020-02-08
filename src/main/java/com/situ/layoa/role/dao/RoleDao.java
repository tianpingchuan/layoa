/**
 * @Company:中享思途   
 * @Title:RoleDao.java 
 * @Author:Administrator   
 * @Date:2020年2月6日 下午8:13:25     
 */ 
package com.situ.layoa.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.layoa.role.domain.Role;

/** 
 * @ClassName:RoleDao 
 * @Description:(角色类的Dao层)  
 */
@Repository
public interface RoleDao {

	Long save(Role role);
	
	Role findByName(String roleName);
	
	List<Role> findAllRole();
	
	List<Role> findByPage(@Param("firstResult") Integer firstResult,@Param("maxResults") Integer maxResults);
	
	Integer getCount();
	
	void delete(Long rowId);
	
	void update(Role role);
	
	Role get(Long rowId);
}
