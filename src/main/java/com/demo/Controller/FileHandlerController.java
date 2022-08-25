package com.demo.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Service.FileHandlerService;
import com.demo.Util.FileUtility;
import com.demo.model.HeaderMaster;

@RestController
@RequestMapping("/fileHandle")
public class FileHandlerController {
	
	@Value("${filePath}")
	private String filepath;
	
	@Autowired
	FileHandlerService service;
	
	
	@GetMapping("/readSave")
	public ResponseEntity<String> readAndSaveFileata(){
		
		List<String> contentList = new ArrayList<String>();
		StringBuffer headerInfo = new StringBuffer();
		HeaderMaster saveHeaderData =null;
		
		try {
			FileUtility.getHeaderAndContentData(filepath, contentList, headerInfo);
			String headerdata =headerInfo.toString();
			headerdata = headerdata.replace("|", ",");
			String[] headerInfData = headerdata.split(",");
			System.out.println(headerInfData);
			System.out.println(contentList);
			System.out.println(headerInfo);
			saveHeaderData = service.saveHeaderData(headerInfData);
			service.saveContentData(contentList);
		} catch (IOException e) {
			
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity("header and contnt saved succssfully", HttpStatus.OK);
	}
	@GetMapping("/getWrite")
	public ResponseEntity<String> getWriteFile(){
		String content = service.getWriteFile();
		return new ResponseEntity(content, HttpStatus.OK);
	}
	
	
	

}


