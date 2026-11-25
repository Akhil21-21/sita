package com.cast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {

	public String getDataFromPropertiesFile(String key) throws IOException {

		
		FileInputStream fis = new FileInputStream("\"C:\\Users\\akhil\\Desktop\\daata\\Fli.xlsx\"");
        Properties pObj	= new Properties(); 
		pObj.load(fis);
		String  data = pObj.getProperty(key);
		
		
		return data;
	}

}
