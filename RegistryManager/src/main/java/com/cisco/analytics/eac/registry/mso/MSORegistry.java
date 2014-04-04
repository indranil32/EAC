/**
 * @Title MSORegistry.java
 *
 * @Description  
 *
 * @Project RegistryManager
 * @Author imajumde
 * @Version 1.0
 * @Copyright (c) 2001, 2011-2012 by Cisco Systems, Inc.
 *
 * @History Mar 14, 2014
 */
package com.cisco.analytics.eac.registry.mso;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.cisco.analytics.eac.dto.constants.Constants;
import com.cisco.analytics.eac.registry.AbstractRegistry;

/**
 * MSORegistry - 
 *
 */
public class MSORegistry extends  AbstractRegistry implements Constants {

	
	/* (non-Javadoc)
	 * @see com.cisco.analytics.eac.eac.registry.IRegistry#getProgram(int)
	 */
	@Override
	public String getProgram(String usecase) throws IOException {
		String program = null;
		loadResourceDescription();
		String usecases = prop.getProperty(USE_CASES);
		//String hadoopHome = prop.getProperty(HADOOP_HOME);
		String mr = prop.getProperty(MR);
		for (String ucase : usecases.split(",")) {
			if (usecase.equalsIgnoreCase(usecase)) {
				// /usr/lib/hadoop/mr/CMD2K_REBOOT.jar
				program = mr + ucase.trim().toUpperCase() + SUFFIX_JAR;
			}
		}
		return program;
	}

	/* (non-Javadoc)
	 * @see com.cisco.analytics.eac.eac.registry.IRegistry#getInputPath()
	 */
	@Override
	public String getInputPath(String usecase) throws IOException {
		String input = null;
		loadResourceDescription();
		String usecases = prop.getProperty(USE_CASES);
		for (String ucase : usecases.split(",")) {
			if (usecase.equalsIgnoreCase(usecase)) {
				// /cmd2k/logs
				input = prop.getProperty(ucase.trim().toUpperCase() + INPUT);
			}
		}
		if (null == input)	// DEFAULT : /use-casename/input/
			input = usecase.trim().toUpperCase() + INPUT;
		return input;
	}

	/* (non-Javadoc)
	 * @see com.cisco.analytics.eac.eac.registry.IRegistry#getOutpath()
	 */
	@Override
	public String getOutpath(String usecase) throws IOException {
		String output = null;
		loadResourceDescription();
		String usecases = prop.getProperty(USE_CASES);
		for (String ucase : usecases.split(",")) {
			if (usecase.equalsIgnoreCase(usecase)) {
				// /cmd2k/out/
				output = prop.getProperty(ucase.trim().toUpperCase() + OUTPUT);
			}
		}
		if (null == output)	// DEFAULT : /use-casename/output/
			output = usecase.trim().toUpperCase() + OUTPUT;
		return output;
	}
	
	/* (non-Javadoc)
	 * @see com.cisco.analytics.eac.registry.IRegistry#getEnvProgram(java.lang.String)
	 */
	@Override
	public String getEnvProgram(String usecase) throws IOException {
		String env = null;
		loadResourceDescription();
		env = prop.getProperty(SCRIPT);
		if (null == env) {
			String usecases = prop.getProperty(USE_CASES);
			for (String ucase : usecases.split(",")) {
				if (usecase.equalsIgnoreCase(usecase)) {
					// /usr/lib/hadoop-2.0.0/run.sh
					env = prop.getProperty(ucase.trim().toUpperCase() + UNDERSCORE + SCRIPT);
				}
			}
		}
		return env;
	}

	/* (non-Javadoc)
	 * @see com.cisco.analytics.eac.eac.registry.AbstractRegistry#loadResourceDescription()
	 */
	@Override
	public void loadResourceDescription() throws IOException {
		prop = new Properties();
		FileInputStream inStream = new FileInputStream(RESOURCES);
		prop.load(inStream);
		inStream.close();
	}

	/* (non-Javadoc)
	 * @see com.cisco.analytics.eac.eac.registry.IRegistry#getResourceDescriptor()
	 */
	@Override
	public Properties getResourceDescriptor() throws IOException {
		loadResourceDescription();
		return prop;
	}

}
