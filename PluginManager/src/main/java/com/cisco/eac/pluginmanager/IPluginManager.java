/**
 * @Title IPluginManager.java
 *
 * @Description  
 *
 * @Project IPluginManager
 * @Author imajumde
 * @Version 1.0
 * @Copyright (c) 2001, 2011-2012 by Cisco Systems, Inc.
 *
 * @History Mar 14, 2014
 */
package com.cisco.eac.pluginmanager;

import java.io.IOException;

import com.cisco.analytics.cluster.DTO;

/**
 * IPluginManager - 
 *
 */
public interface IPluginManager {
    public DTO run(String usecase, String others) throws IOException;
    public DTO search(String commadelimKeyWords);
}
