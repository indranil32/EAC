/*
######################################################
#
# @Copyright (c) 2014 by Cisco Systems, Inc.
#
# @Title TableData.java
# @Description This class represents the data model of a Table Widget's data
# @Version 1.0
# @author  vcbu_ui_team@cisco.com
#
######################################################
*/
package com.cisco.analytics.eac.dto.tables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableData {

	private List<Map<String,String>> items;
	private String identifier;
	
	public List<Map<String, String>> getItems() {
		if (items == null) {
			items = new ArrayList<Map<String, String>>();
        }
		return items;
	}

	
	public void setItems(List<Map<String, String>> items) {
		this.items = items;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}


	@Override
	public String toString() {
		return "TableData [items=" + items + ", identifier=" + identifier + "]";
	}

	
}
