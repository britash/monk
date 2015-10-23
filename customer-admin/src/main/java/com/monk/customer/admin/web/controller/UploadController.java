package com.monk.customer.admin.web.controller;


import static com.monk.customer.common.constants.Constants.RES_SUCC_CODE;
import static com.monk.customer.common.constants.Constants.RES_SUCC_MSG;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monk.customer.common.util.JsonResult;
import com.monk.customer.service.file.LocalStoreService;

@RestController
public class UploadController extends BaseController {
	private static final transient Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private LocalStoreService localStoreService;
	
	@RequestMapping({ "/file/upload" })
	public String upload(
			@RequestParam("fileName")String fileName, MultipartFile file,HttpServletRequest request) throws IOException{
		logger.info("fileName:{},  fileSize:{}", fileName,  file.getBytes().length);
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		// 取文件名
		String url =  localStoreService.getDomain() + localStoreService.uploadFile(fileName, file.getBytes());
		result.setData(url);
		return logAndEscapeResult(result.toJsonString(), request, logger);
	}
	
}
