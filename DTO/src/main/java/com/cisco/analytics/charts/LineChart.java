/*
######################################################
#
# @Copyright (c) 2014 by Cisco Systems, Inc.
#
# @Title LineChart.java
# @Description This class represents the data model of a Line Chart
# @Version 1.0
# @author  vcbu_ui_team@cisco.com
#
######################################################
*/

package com.cisco.analytics.charts;

import java.util.ArrayList;
import java.util.List;

public class LineChart extends Chart {
 
	private int lineXMajorTick;
    private double lineXMinorTick;
	private int lineYMajorTick;
	private int lineYMinorTick;
	private int lineYMax;
	private List<String> lineXYAxis;
	private List<String> lineTitles;
	private List<List<Integer>> lineData;
	
	public int getLineXMajorTick() {
		return lineXMajorTick;
	}
	public void setLineXMajorTick(int lineXMajorTick) {
		this.lineXMajorTick = lineXMajorTick;
	}
	public double getLineXMinorTick() {
		return lineXMinorTick;
	}
	public void setLineXMinorTick(double lineXMinorTick) {
		this.lineXMinorTick = lineXMinorTick;
	}
	public int getLineYMajorTick() {
		return lineYMajorTick;
	}
	public void setLineYMajorTick(int lineYMajorTick) {
		this.lineYMajorTick = lineYMajorTick;
	}
	public int getLineYMinorTick() {
		return lineYMinorTick;
	}
	public void setLineYMinorTick(int lineYMinorTick) {
		this.lineYMinorTick = lineYMinorTick;
	}
	public int getLineYMax() {
		return lineYMax;
	}
	public void setLineYMax(int lineYMax) {
		this.lineYMax = lineYMax;
	}
	public List<String> getLineXYAxis() {
		if (lineXYAxis == null) {
			lineXYAxis = new ArrayList<String>();
        }
		return lineXYAxis;
	}
	public void setLineXYAxis(List<String> lineXYAxis) {
		this.lineXYAxis = lineXYAxis;
	}
	public List<String> getLineTitles() {
		if (lineTitles == null) {
			lineTitles = new ArrayList<String>();
        }
		return lineTitles;
	}
	public void setLineTitles(List<String> lineTitles) {
		this.lineTitles = lineTitles;
	}
	public List<List<Integer>> getLineData() {
		if (lineData == null) {
			lineData = new ArrayList<List<Integer>>();
        }
		return lineData;
	}
	public void setLineData(List<List<Integer>> lineData) {
		this.lineData = lineData;
	}
	@Override
	public String toString() {
		return "LineChart [lineXMajorTick=" + lineXMajorTick
				+ ", lineXMinorTick=" + lineXMinorTick + ", lineYMajorTick="
				+ lineYMajorTick + ", lineYMinorTick=" + lineYMinorTick
				+ ", lineYMax=" + lineYMax + ", lineXYAxis=" + lineXYAxis
				+ ", lineTitles=" + lineTitles + ", lineData=" + lineData + "]";
	}
	
	/* Sample data model for line chart fed into UI
	 * "lineTitles":["300","Hunger Games 2"],
		"lineData":[[2600, 1800, 2000, 1000, 1400, 700, 2000],
		             [6300, 1800, 3000, 500, 4400, 2700, 2000]
		             ],
		 "lineXMajorTick":1,
		 "lineXMinorTick":0.1,
		 "lineYMajorTick":1000,
		 "lineYMinorTick":100,
		 "lineYMax":7000,
	     "lineXYAxis":["Month","No of views"]
	     
	 * */
	
}
