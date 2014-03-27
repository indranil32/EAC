package com.cisco.registry.helper;

import java.io.FileInputStream;
import java.util.Properties;

import com.cisco.analytics.constants.Constants;

public class RegistryHelper implements Constants{

	private Properties loadResourceDescription() {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream();
		prop.load();
	}
	
}
