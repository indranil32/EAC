/*
######################################################
#
# @Copyright (c) 2014 by Cisco Systems, Inc.
#
# @Title Column.java
# @Description This class represents the data model of Column in a Table Widget
# @Version 1.0
# @author  vcbu_ui_team@cisco.com
#
######################################################
 */

package com.cisco.analytics.tables;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "column")
@XmlAccessorType(XmlAccessType.FIELD)
public class Column {
    @XmlElement(name="attr", required=true, nillable=false)
    private String  attr;
    @XmlElement(name="label")
    private String  label;
    @XmlElement(name="width")
    private int     width;
    @XmlElement(name="sortable")
    private boolean sortable = true;
    @XmlElement(name="editable")
    private boolean editable;
    @XmlElement(name="hidden")
    private boolean hidden;

    public String getAttr() {
	return attr;
    }

    public void setAttr(String attr) {
	this.attr = attr;
    }

    public int getWidth() {
	return width;
    }

    public void setWidth(int width) {
	this.width = width;
    }

    public boolean isSortable() {
	return sortable;
    }

    public void setSortable(boolean sortable) {
	this.sortable = sortable;
    }

    public boolean isEditable() {
	return editable;
    }

    public void setEditable(boolean editable) {
	this.editable = editable;
    }

    public String getLabel() {
	return label;
    }

    public void setLabel(String label) {
	this.label = label;
    }

    public boolean isHidden() {
	return hidden;
    }

    public void setHidden(boolean hidden) {
	this.hidden = hidden;
    }

    @Override
    public String toString() {
	return "Column [attr=" + attr + ", label=" + label + ", width=" + width + ", sortable=" + sortable + ", editable=" + editable + ", hidden=" + hidden + "]";
    }

}
