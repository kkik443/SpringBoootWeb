package com.demo.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.ContentMaster;
import com.demo.model.HeaderMaster;
import com.demo.repository.ContentMasterRepository;
import com.demo.repository.HeaderMasterRepository;

@Service
public class FileHandlerService {
	
	@Autowired
	HeaderMasterRepository headerMasterRepository;
	
	@Autowired
	ContentMasterRepository contentMasterRepository;
	public HeaderMaster saveHeaderData(String[] headerInfData) {
		
		HeaderMaster headerMaster = new HeaderMaster();
		headerMaster.setField1(headerInfData[0]);
		headerMaster.setField2(headerInfData[1]);
		headerMaster.setField3(headerInfData[2]);
		headerMaster.setField4(headerInfData[3]);
		headerMaster.setField5(headerInfData[4]);
		headerMaster.setField6(headerInfData[5]);
		headerMaster.setField7(headerInfData[6]);
		headerMaster.setField8(headerInfData[7]);
		headerMaster.setField9(headerInfData[8]);
		headerMaster.setField10(headerInfData[9]);
		headerMaster.setField11(headerInfData[10]);
		return headerMasterRepository.save(headerMaster);
	}

	public void saveContentData(List<String> contentList) {
		//List<String> copyList =contentList;
		//List<ContentMaster> contentMasters = new ArrayList<ContentMaster>;
		contentList.stream().forEach(val->{
			String value  =val.replace("|", ",");
			String[] contentDataArray = value.split(",");
			ContentMaster contentMaster = new ContentMaster();
			contentMaster.setField1(contentDataArray[0]);
			contentMaster.setField2(contentDataArray[1]);
			contentMaster.setField3(contentDataArray[2]);
			contentMaster.setField4(contentDataArray[3]);
			contentMaster.setField5(contentDataArray[4]);
			contentMaster.setField6(contentDataArray[5]);
			contentMaster.setField7(contentDataArray[6]);
			contentMaster.setField8(contentDataArray[7]);
			contentMaster.setField9(contentDataArray[8]);
			
			//contentMasters.add(contentMaster);
			contentMasterRepository.save(contentMaster);
		});
	}
	
	public String getWriteFile() {
		List<HeaderMaster> headerList = headerMasterRepository.findAll();
		StringBuffer buffer = new StringBuffer();
		buidHeaderString(headerList,buffer);
		buffer.append(System.lineSeparator());
		List<ContentMaster> contentMasterList = contentMasterRepository.findAll();
		buidContentString(contentMasterList,buffer);
		System.out.println(buffer.toString());
		writeStringtoFile("F:\\Gouse\\Data\\test1_data.txt",buffer.toString());
		return buffer.toString();
	}
	
	private void writeStringtoFile(String fileName,String data) {
		File file = new File(fileName);
        
       // String data = "Hello World!\nWelcome to www.tutorialkart.com";
         
        try(FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            //convert string to byte array
            byte[] bytes = data.getBytes();
            //write byte array to file
            bos.write(bytes);
            bos.close();
            fos.close();
            System.out.print("Data written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private void buidHeaderString(List<HeaderMaster> headerList,StringBuffer stringBuffer) {
		HeaderMaster headerMaster = headerList.get(0);
		stringBuffer.append(headerMaster.getField1()+"|");
		stringBuffer.append(headerMaster.getField2()+"|");
		stringBuffer.append(headerMaster.getField3()+"|");
		stringBuffer.append(headerMaster.getField4()+"|");
		stringBuffer.append(headerMaster.getField5()+"|");
		stringBuffer.append(headerMaster.getField6()+"|");
		stringBuffer.append(headerMaster.getField7()+"|");
		stringBuffer.append(headerMaster.getField8()+"|");
		stringBuffer.append(headerMaster.getField9()+"|");
		stringBuffer.append(headerMaster.getField10()+"|");
		stringBuffer.append(headerMaster.getField11());
	}
	
	private void buidContentString(List<ContentMaster> contentMasterList,StringBuffer stringBuffer) {
		for(ContentMaster contentMaster:contentMasterList) {
		stringBuffer.append(contentMaster.getField1()+"|");
		stringBuffer.append(contentMaster.getField2()+"|");
		stringBuffer.append(contentMaster.getField3()+"|");
		stringBuffer.append(contentMaster.getField4()+"|");
		stringBuffer.append(contentMaster.getField5()+"|");
		stringBuffer.append(contentMaster.getField6()+"|");
		stringBuffer.append(contentMaster.getField7()+"|");
		stringBuffer.append(contentMaster.getField8()+"|");
		stringBuffer.append(contentMaster.getField9());
		stringBuffer.append(System.lineSeparator());
		
		}
		
	}
	
	

}
