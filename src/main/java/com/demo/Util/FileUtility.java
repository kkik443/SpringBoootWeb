package com.demo.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileUtility {
	
	public static void getHeaderAndContentData(String fileName,List<String> contntList,StringBuffer headerInfo) throws IOException {
		
		File f= new File(fileName);
		BufferedReader br= new BufferedReader(new FileReader(f));
		
		
		String value =null;
		int count =0;
		while((value=br.readLine())!=null) {
			if(count==0) {
				headerInfo.append(value);
				
			}
			else {
				
				contntList.add(value);
			}
		count++;
		}
	}

}
