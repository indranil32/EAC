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
package com.cisco.analytics.utils;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * ERRORCODE - 
 *
 */
@XmlType(name="errorcode")
@XmlEnum
public enum ERRORCODE {
    @XmlEnumValue(value = "200")
    SUCCESS(200,"Success"),
    @XmlEnumValue(value = "201")	
    NODATA(201,"No Data Found"),    
    @XmlEnumValue(value = "500")
    SERVERERROR(500, "Internal Server Error"),    
    @XmlEnumValue(value = "503")
    TIMEDOUT(503, "Time Out"),    
    @XmlEnumValue(value = "404")
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
