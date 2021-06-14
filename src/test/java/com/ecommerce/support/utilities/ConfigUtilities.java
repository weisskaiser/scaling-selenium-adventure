package com.ecommerce.support.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtilities {
	
	public static Properties LoadProperties(String filePath) throws IOException  {
		FileInputStream fileInputStream = new FileInputStream(filePath);
		Properties properties = new Properties();
		properties.load(fileInputStream);
		return properties;
	}
	
}
