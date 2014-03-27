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
package com.cisco.registry.mso;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.cisco.analytics.constants.Constants;
import com.cisco.registry.AbstractRegistry;

/**
 * MSORegistry - 
 *
 */
public class MSORegistry extends  AbstractRegistry implements Constants {

	/* (non-Javadoc)
	 * @see com.cisco.registry.IRegistry#getProgram(int)
	 */
	@Override
	public String getProgram(String usecase) throws IOException {
		String program = null;
		loadResourceDescription();
		String usecases = prop.getProperty(USE_CASES);
		String hadoopHome = prop.getProperty(HADOOP_HOME);
		String mr = prop.getProperty(MR);
		for (String ucase : usecases.split(",")) {
			if (usecase.equalsIgnoreCase(usecase)) {
				// /usr/lib/hadoop/mr/CMD2K_REBOOT.jar
				program = hadoopHome + mr + ucase.toUpperCase() + SUFFIX_JAR;
			}
		}
		return program;
	}

	/* (non-Javadoc)
	 * @see com.cisco.registry.IRegistry#getInputPath()
	 */
	@Override
	public String getInputPath(String usecase) throws IOException {
		String input = null;
		loadResourceDescription();
		String usecases = prop.getProperty(USE_CASES);
		for (String ucase : usecases.split(",")) {
			if (usecase.equalsIgnoreCase(usecase)) {
				// /usr/lib/hadoop/mr/CMD2K_REBOOT.jar
				input = prop.getProperty(ucase+INPUT);
			}
		}
		if (null == input)	// DEFAULT : /use-casename/input/
			input = usecase+INPUT;
		return input;
	}

	/* (non-Javadoc)
	 * @see com.cisco.registry.IRegistry#getOutpath()
	 */
	@Override
	public String getOutpath(String usecase) throws IOException {
		String output = null;
		loadResourceDescription();
		String usecases = prop.getProperty(USE_CASES);
		for (String ucase : usecases.split(",")) {
			if (usecase.equalsIgnoreCase(usecase)) {
				// /usr/lib/hadoop/mr/CMD2K_REBOOT.jar
				output = prop.getProperty(ucase+OUTPUT) + "/" +Math.random();
			}
		}
		if (null == output)	// DEFAULT : /use-casename/input/
			output = usecase+OUTPUT;
		return output;
	}

	/* (non-Javadoc)
	 * @see com.cisco.registry.AbstractRegistry#loadResourceDescription()
	 */
	@Override
	public void loadResourceDescription() throws IOException {
		prop = new Properties();
		FileInputStream inStream = new FileInputStream(RESOURCES);
		prop.load(inStream);
		inStream.close();
	}

}
