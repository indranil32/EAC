/**
 * 
 */
package com.cisco.analytics.eac.dto;

/**
 * @author imajumde
 *
 */
import com.cisco.analytics.eac.dto.charts.*;
import com.cisco.analytics.eac.dto.cluster.*;
import com.cisco.analytics.eac.dto.tables.*;
import com.cisco.analytics.eac.dto.utils.ERRORCODE;
import com.google.gson.Gson;
import java.util.*;

public class Test {


	public static void main(String[] args) {
		DTO dto=new DTO();

		dto.setTables(buildTables());
		dto.setCharts(buildCharts());
		//dto.setClusters(buildClusters());
		dto.setMsgCode(ERRORCODE.SUCCESS);
		dto.setMsgDesc("Success");
		
		Gson gson = new Gson();
		String json = gson.toJson(dto);

		System.out.println(json);
	}

	private static List<Table> buildTables(){
		List<Table> tabs=new ArrayList<Table>();
		//adding table items/data
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		Map<String,String> map=new HashMap<String,String>();
		map.put("value","112");
		list.add(map);	   
		Table tab=new Table();
		TableData data=new TableData();
		data.setItems(list);
		data.setIdentifier("id");
		//buildColumns
		Column c=new Column();
		c.setAttr("name");
		c.setEditable(true);
		c.setLabel("Name");
		c.setWidth(120);
		tab.getCols().add(c);
           
		tab.setData(data);
      
			tabs.add(tab);

		return tabs;
	}
	private static List<Chart> buildCharts(){

		List<Chart> list=new ArrayList<Chart>();

		PieChart pie=new PieChart();
		pie.setType(ChartType.PIE);
		List<Double> pieData=new ArrayList<Double>();
		pieData.add(30.0);pieData.add(70.0);pieData.add(10.0);
		List<String> pieTitles=new ArrayList<String>();
		pieTitles.add("300");pieTitles.add("Troy");pieTitles.add("Rise of VCBU");
		pie.setPieTitles(pieTitles);
		pie.setPieData(pieData);
		list.add(pie);

		LineChart line=new LineChart();
		line.setType(ChartType.LINE);
		line.setLineXMajorTick(1);
		line.setLineYMax(20);
		list.add(line);

		return list;
	}
	private static List<Cluster> buildClusters(){
		List<Cluster> clr=new ArrayList<Cluster>();
		Cluster c=new Cluster();
		c.setName("tom");
		c.setType("Minor");
		Cluster cc=new Cluster();
		cc.setName("linuxnode");
		cc.setType("Clear");
		List<Cluster> children=new ArrayList<Cluster>();
		children.add(cc);
		//c.setChildren(children);
		clr.add(c);
		return clr;
	}
}
