/**
 * @Title IDelegate.java
 *
 * @Description  
 *
 * @Project Prime
 * @Author imajumde
 * @Version 1.0
 * @Copyright (c) 2001, 2011-2012 by Cisco Systems, Inc.
 *
 * @History Mar 14, 2014
 */
package com.cisco.analytics;

import javax.ws.rs.core.Response;
/**
 * IDelegate - 
 *
 */
public interface IDelegate {
	public Response run(String usecase, String others);
}
