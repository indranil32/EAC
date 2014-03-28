/*
######################################################
#
# @Copyright (c) 2014 by Cisco Systems, Inc.
#
# @Title Cluster.java
# @Description This class represents the data model for the entire Node Cluster
# @Version 1.0
# @author  vcbu_ui_team@cisco.com
#
######################################################
*/

package com.cisco.analytics.cluster;


public class Cluster {
    	
	private String name;
   	private String type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Cluster [name=" + name + ", type=" + type + "]";
	}
	
}
