package com.cast.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonParser;

public class JSONUtility {

	public static void main(String[] args) throws FileNotFoundException {

          FileReader fir = new FileReader("‪C:\\Users\\akhil\\Downloads\\appComman.json");
 
          JsonParser par = new JsonParser();
          Object obj = par.parse("‪C:\\Users\\akhil\\Downloads\\appComman.json");
		
	}

}
