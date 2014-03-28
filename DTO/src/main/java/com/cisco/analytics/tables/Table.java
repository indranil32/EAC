/*
######################################################
#
# @Copyright (c) 2014 by Cisco Systems, Inc.
#
# @Title Table.java
# @Description This class represents the data model of a Table Widget
# @Version 1.0
# @author  vcbu_ui_team@cisco.com
#
######################################################
*/

package com.cisco.analytics.tables;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "table")
public class Table {

	private List<Column> cols;
	private TableData data;
	
	public List<Column> getCols() {
		if (cols == null) {
			cols = new ArrayList<Column>();
        }
		return cols;
	}
	public void setCols(List<Column> cols) {
		this.cols = cols;
	}
	public TableData getData() {
		return data;
	}
	public void setData(TableData data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Table [cols=" + cols + ", data=" + data + "]";
	}
	

} 
