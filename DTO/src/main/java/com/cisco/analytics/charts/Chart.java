/*
######################################################
#
# @Copyright (c) 2014 by Cisco Systems, Inc.
#
# @Title Chart.java
# @Description This class represents the generic data model of Chart Widget
# @Version 1.0
# @author  vcbu_ui_team@cisco.com
#
######################################################
 */
package com.cisco.analytics.charts;



public abstract class Chart {

    private ChartType type;

    public ChartType getType() {
	return type;
    }

    public void setType(ChartType type) {
	this.type = type;
    }

}
