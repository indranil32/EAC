/*
######################################################
#
# @Copyright (c) 2014 by Cisco Systems, Inc.
#
# @Title DTO.java
# @Description This class represents the data model for the entire data fed to the UI
# @Version 1.0
# @author  vcbu_ui_team@cisco.com
#
######################################################
 */

package com.cisco.analytics.cluster;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.cisco.analytics.charts.Chart;
import com.cisco.analytics.tables.Table;
import com.cisco.analytics.utils.ERRORCODE;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "charts", "tables", "clusters", "msgDesc", "msgCode" })
@XmlRootElement(name = "dto")
@XmlSeeAlso(value = { Chart.class, Table.class, Cluster.class, ERRORCODE.class })
public class DTO {

    @XmlElement(name = "charts")
    private List<Chart>   charts;
    @XmlElement(name = "tables")
    private List<Table>   tables;
    @XmlElement(name = "clusters")
    private List<Cluster> clusters;
    @XmlElement(name = "msgDesc")
    private String	msgDesc;
    @XmlElement(name = "msgCode")
    private ERRORCODE     msgCode;

    public List<Chart> getCharts() {
	if (charts == null) {
	    charts = new ArrayList<Chart>();
	}
	return charts;
    }

    public List<Table> getTables() {
	if (tables == null) {
	    tables = new ArrayList<Table>();
	}
	return tables;
    }

    public List<Cluster> getClusters() {
	if (clusters == null) {
	    clusters = new ArrayList<Cluster>();
	}
	return clusters;
    }

    public void setCharts(List<Chart> charts) {
	this.charts = charts;
    }

    public void setTables(List<Table> tables) {
	this.tables = tables;
    }

    public void setClusters(List<Cluster> clusters) {
	this.clusters = clusters;
    }

    public String getMsgDesc() {
	return msgDesc;
    }

    public void setMsgDesc(String msgDesc) {
	this.msgDesc = msgDesc;
    }

    public ERRORCODE getMsgCode() {
	return msgCode;
    }

    public void setMsgCode(ERRORCODE msgCode) {
	this.msgCode = msgCode;
    }

    @Override
    public String toString() {
	return "DTO [charts=" + charts + ", tables=" + tables + ", clusters=" + clusters + ", msgDesc=" + msgDesc + ", msgCode=" + msgCode + "]";
    }

}
