/**
 * @Company:中享思途   
 * @Title:UploadController.java 
 * @Author:Administrator   
 * @Date:2020年2月7日 上午9:23:40     
 */ 
package com.situ.layoa.upload.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.layoa.commons.LayResult;
import com.situ.layoa.commons.UploadFile;

/** 
 * @ClassName:UploadController 
// * @Description:(uploadComtroller)  
 */
@Controller
public class UploadController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ResponseBody
	@RequestMapping("/upload")
	public LayResult doUploadFile(UploadFile uploadFile) {
		System.out.println(uploadFile.getUploadFile());
//		将上传的文件写出到磁盘后得到的一个文件路径和
		String src = "layoafile/xxx.img";
		return new LayResult(0,"",0,src);
	}
}
