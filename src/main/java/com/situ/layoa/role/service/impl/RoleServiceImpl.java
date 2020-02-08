/**
 * @Company:中享思途   
 * @Title:RoleServiceImpl.java 
 * @Author:Administrator   
 * @Date:2020年2月6日 下午8:19:27     
 */ 
package com.situ.layoa.role.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

	/** 
	 * @Title: findAllRole 
	 * @Description:(查找所有的用户实例)
	 * @return  
	 */  
	@Override
	public List<Role> findAllRole() {
		return roleDao.findAllRole();
	}

	/** 
	 * @Title: getCount 
	 * @Description:(查询有效数据条数)
	 * @return  
	 */  
	@Override
	public Integer getCount() {
		return roleDao.getCount();
	}

	/** 
	 * @Title: doDelete 
	 * @Description:(逻辑删除数据)
	 * @param rowId
	 * @return  
	 */  
	@Override
	public Integer doDelete(Long rowId) {
		roleDao.delete(rowId);
		return 1;
	}

	/** 
	 * @Title: doUpdate 
	 * @Description:(执行修改)
	 * @param role
	 * @return  
	 */  
	@Override
	public Integer doUpdate(Role role) {
		role.setUpdateBy("admin");
		role.setUpdateDate(new Date());
		roleDao.update(role);
		return 1;
	}

	/** 
	 * @Title: getByID 
	 * @Description:(根据id查询角色实例)
	 * @param rowId
	 * @return  
	 */  
	@Override
	public Role getByID(Long rowId) {
		return roleDao.get(rowId);
	}

	/** 
	 * @Title: findRoleByPage 
	 * @Description:(分页查询)
	 * @param page:第几页
	 * @param limit:一页几条数据
	 * @return  
	 */  
	@Override
	public List<Role> findRoleByPage(Integer page, Integer limit) {

		Integer firstResult = limit*(page-1);
		Integer maxResults = limit;
		
		return roleDao.findByPage(firstResult, maxResults);
	}

}
