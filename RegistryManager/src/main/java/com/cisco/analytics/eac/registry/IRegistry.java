/**
 * @Title IRegistry.java
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
package com.cisco.analytics.eac.registry;

import java.io.IOException;
import java.util.Properties;

/**
 * IRegistry - 
 *
 */
public interface IRegistry {
    public String getProgram(String usecase) throws IOException;
    public String getEnvProgram(String usecase) throws IOException;
    public String getInputPath(String usecase) throws IOException;
    public String getOutpath(String usecase) throws IOException;
    public Properties getResourceDescriptor() throws IOException;
}
