/**
 * @Company:中享思途   
 * @Title:Student.java 
 * @Author:Administrator   
 * @Date:2020年2月6日 下午1:38:15     
 */
package com.situ.layoa.student.damain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ClassName:Student
 * @Description:(这里用一句话描述这个类的作用)
 */
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long rowId;// 主键
	private String stuName;// 学生姓名
	private String stuAge;// 学生年龄
	private Integer stuSex;// 学生性别：0：女；1：男
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date stuBirthday;// 学生生日
	private List<String> stuLikes;// 学生爱好
	private String stuImg;// 学生头像

	public String getStuImg() {
		return stuImg;
	}

	public void setStuImg(String stuImg) {
		this.stuImg = stuImg;
	}

	public Long getRowId() {
		return rowId;
	}

	public String getStuName() {
		return stuName;
	}

	public String getStuAge() {
		return stuAge;
	}

	public Integer getStuSex() {
		return stuSex;
	}

	public Date getStuBirthday() {
		return stuBirthday;
	}

	public List<String> getStuLikes() {
		return stuLikes;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public void setStuAge(String stuAge) {
		this.stuAge = stuAge;
	}

	public void setStuSex(Integer stuSex) {
		this.stuSex = stuSex;
	}

	public void setStuBirthday(Date stuBirthday) {
		this.stuBirthday = stuBirthday;
	}

	public void setStuLikes(List<String> stuLikes) {
		this.stuLikes = stuLikes;
	}

	@Override
	public String toString() {
		return "Student [rowId=" + rowId + ", stuName=" + stuName + ", stuAge=" + stuAge + ", stuSex=" + stuSex
				+ ", stuBirthday=" + stuBirthday + ", stuLikes=" + stuLikes + ", stuImg=" + stuImg + "]";
	}

}
