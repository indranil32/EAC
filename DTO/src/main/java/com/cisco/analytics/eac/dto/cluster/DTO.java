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

package com.cisco.analytics.eac.dto.cluster;

import java.util.ArrayList;
import java.util.List;

import com.cisco.analytics.eac.dto.charts.Chart;
import com.cisco.analytics.eac.dto.tables.Table;
import com.cisco.analytics.eac.dto.utils.ERRORCODE;

public class DTO {

    private List<Chart>   charts;
    private List<Table>   tables;
    private List<Cluster> clusters;
    private String	msgDesc;
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
