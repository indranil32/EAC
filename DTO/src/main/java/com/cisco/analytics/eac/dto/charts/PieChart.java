/*
######################################################
#
# @Copyright (c) 2014 by Cisco Systems, Inc.
#
# @Title PieChart.java
# @Description This class represents the data model of a Pie Chart Widget
# @Version 1.0
# @author  vcbu_ui_team@cisco.com
#
######################################################
*/

package com.cisco.analytics.eac.dto.charts;

import java.util.ArrayList;
import java.util.List;

public class PieChart extends Chart {

	private List<String> pieYTooltips;
	private List<String> pieTitles;	
	private List<Double> pieData;
	
	public List<String> getPieYTooltips() {
		if (pieYTooltips == null) {
			pieYTooltips = new ArrayList<String>();
        }
		return pieYTooltips;
	}
	public void setPieYTooltips(List<String> pieYTooltips) {
		this.pieYTooltips = pieYTooltips;
	}
	public List<String> getPieTitles() {
		if (pieTitles == null) {
			pieTitles = new ArrayList<String>();
        }
		return pieTitles;
	}
	public void setPieTitles(List<String> pieTitles) {
		this.pieTitles = pieTitles;
	}
	public List<Double> getPieData() {
		if (pieData == null) {
			pieData = new ArrayList<Double>();
        }
		return pieData;
	}
	public void setPieData(List<Double> pieData) {
		this.pieData = pieData;
	}
	@Override
	public String toString() {
		return "PieChart [pieYTooltips=" + pieYTooltips + ", pieTitles="
				+ pieTitles + ", pieData=" + pieData + "]";
	}
	
	/* Sample data model for line chart fed into UI
	 * 
	 * "pieYTooltips":["1,210 million","952 million","125 million"],
		"pieTitles":["Dhoom","Dhoom 2","300"],
		"pieData":[12.1,4.52,1.25],
	 * */
	
	
}
