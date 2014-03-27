/*
######################################################
#
# @Copyright (c) 2014 by Cisco Systems, Inc.
#
# @Title ChartType.java
# @Description The enum that specifies the Chart types that can be displayed in UI
# @Version 1.0
# @author  vcbu_ui_team@cisco.com
#
######################################################
 */

package com.cisco.analytics.charts;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "charttype")
@XmlEnum
public enum ChartType {
    @XmlEnumValue(value = "PIE")
    PIE("PIE"), 
    @XmlEnumValue(value = "LINE")
    LINE("LINE");

    private final String value;

    private ChartType(final String s) {
	value = s;
    }

    public String toString() {
	return value;
    }
    
    public static ChartType fromValue(String v) {
        for (ChartType c: ChartType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
