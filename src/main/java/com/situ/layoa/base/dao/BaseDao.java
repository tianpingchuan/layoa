/**
 * @Company:中享思途   
 * @Title:BaseDao.java 
 * @Author:Administrator   
 * @Date:2020年2月10日 下午2:23:28     
 */ 
package com.situ.layoa.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.situ.layoa.commons.Pagination;

/** 
 * @ClassName:BaseDao 
 * @Description:(Dao类的基本接口)  
 */
public interface BaseDao<T> {

	Long save(T t);
	
	void update(T t);
	
	void delete(Long rowId);
	
	T get(Long rowId);
	
	List<T> find();
	
	/**
	 * 
	 * @Title: getCount 
	 * @Description:(查询出数据的条数)
	 * @param t
	 * @return
	 */
	Integer getCount(@Param("searchParam") T t);
	
	/**
	 * 
	 * @Title: findByPage 
	 * @Description:(根据分页查询)
	 * @param pagination
	 * @param t 查询数据可以为空
	 * @return
	 */
	List<T> findByPage(@Param("pagination") Pagination pagination,@Param("searchParam") T t);
}
