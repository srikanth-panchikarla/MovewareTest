package com.moveware.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


/**
 * @author Siva Ram Khandrika
 *
 */
@SuppressWarnings("serial")
public class ReadMovewareTestExecutionProperties extends Properties {
	private static ReadMovewareTestExecutionProperties properties;
	private static FileReader file;

	//Private constructor to create Singleton Class
	private ReadMovewareTestExecutionProperties() {
		
	};
	
	//Creating an Instance for Properties file
	public static ReadMovewareTestExecutionProperties getInstance(){
		try {
			if (properties==null) {
				properties = new ReadMovewareTestExecutionProperties();
			}
			file = new FileReader(System.getProperty("user.dir")+"\\MovewareTestExecutionProperties.properties");
			properties.load(file);
		} catch(IOException io) {
			io.printStackTrace();
		}
		return properties;
	}
	
	//Read User Name
	public String readProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}
	
}
