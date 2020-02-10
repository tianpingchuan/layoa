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

import com.situ.layoa.commons.LayResult;
import com.situ.layoa.role.dao.RoleDao;
import com.situ.layoa.role.domain.Role;
import com.situ.layoa.role.service.RoleService;
import com.situ.layoa.util.DAOUtils;

/**
 * @ClassName:RoleServiceImpl
 * @Description:(角色类的逻辑层的实现类)
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
	public Integer findByName(String roleName) {
		Role role = roleDao.findByName(roleName);
//		判断不等于null返回1，标示值已经被别人使用了
		return role != null ? 1 : 0;
	}

	/**
	 * @Title: findAllRole
	 * @Description:(查找所有的用户实例)
	 * @return
	 */
	@Override
	public List<Role> findAllRole() {
		return roleDao.find();
	}

	/**
	 * @Title: getCount
	 * @Description:(查询有效数据条数)
	 * @return
	 */
	@Override
	public Integer getCount(Role searchRole) {
		return roleDao.getCount(searchRole);
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
	public LayResult findRoleByPage(Integer page, Integer limit, Role searchRole) {
//		通过反射机制将类的实例中的""重新赋值为null，方便Mybatis中的多条件查询SQL语句的拼写
		Role searchParam = DAOUtils.buildSearchParam(searchRole);
//		查询出符合条件的一共有多少条数据
		Integer dataCount = roleDao.getCount(searchRole);
//		根据分页的请求信息查询出数量列表
		List<Role> roleList = roleDao.findByPage(DAOUtils.buildPagination(page, limit), searchParam);
		return new LayResult(0, "", dataCount, roleList);
	}

}
