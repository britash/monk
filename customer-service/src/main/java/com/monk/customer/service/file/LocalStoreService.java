package com.monk.customer.service.file;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.monk.customer.common.util.FileNameUtils;

@Service("localStoreService")
public class LocalStoreService {
	
	private static final Logger log = LoggerFactory.getLogger(LocalStoreService.class);
	
	@Value("#{config['file.save.path']}")
	private String localRoot;
	
	@Value("#{config['file.domain']}")
	private String domain;
	
	private String split ="/";
	
	@PostConstruct
	public void init(){
		localRoot = localRoot + (localRoot.endsWith("/") ? "" : "/") ;
				
	}
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String uploadFile(String originName, byte[] filedata) {
		String fileName = null;
		// 取文件名
		if (StringUtils.isBlank(originName)) {
			String ext = FilenameUtils.getExtension(originName);
			fileName = FileNameUtils.genFileName(ext);
		} else {
			fileName = FilenameUtils.getName(fileName);
		}
		String relPath = split + FileNameUtils.genPathName() + split + fileName;
		File file = new File(localRoot, relPath);
		mkDirs(relPath);
		try {
			FileCopyUtils.copy(filedata, file);
			return relPath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private void mkDirs(String path){
		int index  = path.lastIndexOf("/");
		File file = new File(localRoot, path.substring(0,index));
		if(!file.exists()){
			file.mkdirs();
		}
	}
	

	
}
