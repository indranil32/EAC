/**

 * @Title ProvisioningElementBO.java

 *

 * @Description  

 *

 * @Project RPS

 * @Author 

 * @Version 1.0

 * @Copyright (c) 2001, 2011-2012 by Cisco Systems, Inc.

 *

 * @History

 */

package data;

import java.util.ArrayList;
import java.util.List;

public class BigDataBO {

	private List<String> pieYTooltips = new ArrayList<String>();
	private List<String> pieTitles = new ArrayList<String>();
	private List<Double> pieData = new ArrayList<Double>();
	private List<String> lineTitles = new ArrayList<String>();
	private List<List<Integer>> lineData = new ArrayList<List<Integer>>();
	private List<String> lineXYAxis = new ArrayList<String>();
	private Double  lineXMinorTick = 0.0;
	private Integer lineXMajorTick = 0;
	private Integer lineYMajorTick = 0;
	private Integer lineYMinorTick = 0 ;
	private Integer  lineYMax = 0;
	
	public BigDataBO(){};
	public BigDataBO(List<String> pieYTooltips, List<String> pieTitles,
			List<Double> pieData, List<String> lineTitles,
			List<List<Integer>>lineData, List<String> lineXYAxis,
			Double lineXMinorTick, Integer lineXMajorTick,
			Integer lineYMajorTick, Integer lineYMinorTick, Integer lineYMax) {
		super();
		this.pieYTooltips = pieYTooltips;
		this.pieTitles = pieTitles;
		this.pieData = pieData;
		this.lineTitles = lineTitles;
		this.lineData = lineData;
		this.lineXYAxis = lineXYAxis;
		this.lineXMinorTick = lineXMinorTick;
		this.lineXMajorTick = lineXMajorTick;
		this.lineYMajorTick = lineYMajorTick;
		this.lineYMinorTick = lineYMinorTick;
		this.lineYMax = lineYMax;
	}
	public List<String> getPieYTooltips() {
		//pieYTooltips.add("1,210 million");
		//pieYTooltips.add("952 million");
		//pieYTooltips.add("125 million");
		return pieYTooltips;
	}
	public void setPieYTooltips(List<String> pieYTooltips) {
		this.pieYTooltips = pieYTooltips;
	}
	public List<String> getPieTitles() {
		//pieTitles.add("Dhoom");
		//pieTitles.add("Dhoom2");
		//pieTitles.add("Dhoom3");
		return pieTitles;
	}
	public void setPieTitles(List<String> pieTitles) {
		this.pieTitles = pieTitles;
	}
	public List<Double> getPieData() {
		//pieData.add(12.1);
		//pieData.add(4.52);
		//pieData.add(36.8);
		return pieData;
	}
	public void setPieData(List<Double> pieData) {
		this.pieData = pieData;
	}
	public List<String> getLineTitles() {
		//lineTitles.add("Dhoom3");
		//lineTitles.add("300");
		return lineTitles;
	}
	public void setLineTitles(List<String> lineTitles) {
		this.lineTitles = lineTitles;
	}
	public List<List<Integer>> getLineData() {
		//List<Integer> a = new ArrayList<Integer>();
		//a.add(2600);a.add(1800);a.add(2000);a.add(1000);a.add(1400);a.add(700);a.add(2000);
		//List<Integer> b = new ArrayList<Integer>();
		// b.add(6300); b.add(1800); b.add(3000); b.add(500); b.add(4400); b.add(2800); b.add(3500);
		//lineData.add(a);
		//lineData.add(b);
		
		return lineData;
	}
	public void setLineData(List<List<Integer>> lineData) {
		this.lineData = lineData;
	}
	public List<String> getLineXYAxis() {
		//lineXYAxis.add("Month");
		//lineXYAxis.add("No of views");
		return lineXYAxis;
	}
	public void setLineXYAxis(List<String> lineXYAxis) {
		this.lineXYAxis = lineXYAxis;
	}
	public Double getLineXMinorTick() {
		//lineXMinorTick = 2.5;
		return lineXMinorTick;
	}
	public void setLineXMinorTick(Double lineXMinorTick) {
		this.lineXMinorTick = lineXMinorTick;
	}
	public Integer getLineXMajorTick() {
		//lineXMajorTick =2;
		return lineXMajorTick;
	}
	public void setLineXMajorTick(Integer lineXMajorTick) {
		this.lineXMajorTick = lineXMajorTick;
	}
	public Integer getLineYMajorTick() {
		//lineYMajorTick = 500;
		return lineYMajorTick;
	}
	public void setLineYMajorTick(Integer lineYMajorTick) {
		this.lineYMajorTick = lineYMajorTick;
	}
	public Integer getLineYMinorTick() {
		//lineYMinorTick = 100;
		return lineYMinorTick;
	}
	public void setLineYMinorTick(Integer lineYMinorTick) {
		this.lineYMinorTick = lineYMinorTick;
	}
	public Integer getLineYMax() {
		//lineYMax = 8000;
		return lineYMax;
	}
	public void setLineYMax(Integer lineYMax) {
		this.lineYMax = lineYMax;
	}
	
	
	
}
