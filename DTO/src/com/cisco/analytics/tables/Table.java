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
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "cols", "items", "identifier" })
@XmlRootElement(name = "table")
@XmlSeeAlso(value = { Column.class })
public class Table {

    @XmlElement(name = "cols", required=true, nillable=false)
    private List<Column>	      cols;
    @XmlElement(name = "items", required=true, nillable=false)
    private List<Map<String, String>> items;
    @XmlElement(name = "identifier", required=true, nillable=false)
    private String		    identifier;

    public List<Column> getCols() {
	this.cols = (this.cols == null) ? this.cols = new ArrayList<Column>() : this.cols;
	return this.cols;
    }

    public List<Map<String, String>> getItems() {
	this.items = (this.items == null) ? this.items = new ArrayList<Map<String, String>>() : this.items;
	return this.items;
    }

    public void setCols(List<Column> cols) {
	this.cols = cols;
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
	return "Table [cols=" + cols + ", items=" + items + ", identifier=" + identifier + "]";
    }

}
