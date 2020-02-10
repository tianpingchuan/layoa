/**
 * @Company:中享思途   
 * @Title:Pagination.java 
 * @Author:Administrator   
 * @Date:2020年2月10日 下午2:39:21     
 */
package com.situ.layoa.commons;

import java.io.Serializable;

/**
 * @ClassName:Pagination
 * @Description:(分页类)
 */
public class Pagination implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer firstResult;// 分页开始的下标
	private Integer maxResults;// 分页查询的数量

	public Pagination(Integer firstResult, Integer maxResults) {
		super();
		this.firstResult = firstResult;
		this.maxResults = maxResults;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

}
