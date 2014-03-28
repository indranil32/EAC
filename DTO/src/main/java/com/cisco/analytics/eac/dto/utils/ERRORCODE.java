/**
 * @Title ERRORCODE.java
 *
 * @Description  
 *
 * @Project DataTransferObjects
 * @Author imajumde
 * @Version 1.0
 * @Copyright (c) 2001, 2011-2012 by Cisco Systems, Inc.
 *
 * @History Mar 14, 2014
 */
package com.cisco.analytics.eac.dto.utils;


/**
 * ERRORCODE - 
 *
 */
public enum ERRORCODE {
    SUCCESS(200,"Success"),
    NODATA(201,"No Data Found"),    
    SERVERERROR(500, "Internal Server Error"),    
    TIMEDOUT(503, "Time Out"),    
    UNAVAILABLE(404,"Not Found");
    
    private final int code;
    private final String value;
    
    ERRORCODE(int c, String v) {
        code = c;
	value = v;
    }
 
    public String value() {
        return value;
    }
    
    public int code() {
	return code;
    }
 
    public static ERRORCODE fromValue(String v) {
        for (ERRORCODE c: ERRORCODE.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
