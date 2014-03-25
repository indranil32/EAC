/**
 * Copyright (c) 2012 by Cisco Systems, Inc.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Cisco Systems,  ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Cisco Systems.
 */
package data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;

/**
 * This is the rest service class for Big Data Aanlytics
 * 
 * @author imajumde
 * 
 */

public class BigDataService { 

	private static final String IVSP = "IVSP";
	private static final String LOG = "LOG";
	private static final String MEM = "MEM";
	
	private BigDataBackEnd backend = null;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/loadAvailableBigDataElements")  
	public Response loadAvailableBigDataElements(@QueryParam("chart") String chart) {
		Logger.getAnonymousLogger().log(Level.INFO,"Load BigData Element. : " + chart);
		Response.ResponseBuilder responseBuilder = null;
		StringBuilder bigDataJSon = new StringBuilder("");
		try {  
			backend = new BigDataBackEnd();
			BigDataBO bigDataBO = null;
			if (chart.equalsIgnoreCase(IVSP)) {
				bigDataBO = backend.getIVSPData();
				bigDataJSon.append("{\"pieYTooltips\" : [" + jsonFormatString(bigDataBO.getPieYTooltips()) + "]");
				bigDataJSon.append(", \"pieTitles\" : [" + jsonFormatString(bigDataBO.getPieTitles()) + "]" );
				bigDataJSon.append(", \"pieData\" : " + bigDataBO.getPieData() + "" );
				bigDataJSon.append(", \"lineTitles\" : [" + jsonFormatString(bigDataBO.getLineTitles()) + "]");
				bigDataJSon.append(", \"lineData\" : " + bigDataBO.getLineData() +"" );
				bigDataJSon.append(", \"lineXMajorTick\" : " + bigDataBO.getLineXMajorTick() +"" );			
				bigDataJSon.append(", \"lineXMinorTick\" : " + bigDataBO.getLineXMinorTick() +"" );
				bigDataJSon.append(", \"lineYMajorTick\" : " + bigDataBO.getLineYMajorTick() +"" );
				bigDataJSon.append(", \"lineYMinorTick\" : " + bigDataBO.getLineYMinorTick() +"");
				bigDataJSon.append(", \"lineYMax\" : " + bigDataBO.getLineYMax() +"" );
				bigDataJSon.append(", \"lineXYAxis\" : [" +jsonFormatString(bigDataBO.getLineXYAxis()) + "]}");
			} else if (chart.equalsIgnoreCase(LOG)) {
				bigDataBO = backend.getLOGData();
				bigDataJSon.append("{\"pieYTooltips\" : [" + jsonFormatString(bigDataBO.getPieYTooltips()) + "]");
				bigDataJSon.append(", \"pieTitles\" : [" + jsonFormatString(bigDataBO.getPieTitles()) + "]" );
				bigDataJSon.append(", \"pieData\" : " + bigDataBO.getPieData() + "" );
				bigDataJSon.append(", \"lineTitles\" : [" + jsonFormatString(bigDataBO.getLineTitles()) + "]");
				bigDataJSon.append(", \"lineData\" : " + bigDataBO.getLineData() +"" );
				bigDataJSon.append(", \"lineXMajorTick\" : " + bigDataBO.getLineXMajorTick() +"" );			
				bigDataJSon.append(", \"lineXMinorTick\" : " + bigDataBO.getLineXMinorTick() +"" );
				bigDataJSon.append(", \"lineYMajorTick\" : " + bigDataBO.getLineYMajorTick() +"" );
				bigDataJSon.append(", \"lineYMinorTick\" : " + bigDataBO.getLineYMinorTick() +"");
				bigDataJSon.append(", \"lineYMax\" : " + bigDataBO.getLineYMax() +"" );
				bigDataJSon.append(", \"lineXYAxis\" : [" +jsonFormatString(bigDataBO.getLineXYAxis()) + "]}");
			} else if (chart.equalsIgnoreCase(MEM)){
				bigDataBO = backend.getMEMData();
				bigDataJSon.append("{\"pieYTooltips\" : [" + jsonFormatString(bigDataBO.getPieYTooltips()) + "]");
				bigDataJSon.append(", \"pieTitles\" : [" + jsonFormatString(bigDataBO.getPieTitles()) + "]" );
				bigDataJSon.append(", \"pieData\" : " + bigDataBO.getPieData() + "" );
				bigDataJSon.append(", \"lineTitles\" : [" + jsonFormatString(bigDataBO.getLineTitles()) + "]");
				bigDataJSon.append(", \"lineData\" : " + bigDataBO.getLineData() +"" );
				bigDataJSon.append(", \"lineXMajorTick\" : " + bigDataBO.getLineXMajorTick() +"" );			
				bigDataJSon.append(", \"lineXMinorTick\" : " + bigDataBO.getLineXMinorTick() +"" );
				bigDataJSon.append(", \"lineYMajorTick\" : " + bigDataBO.getLineYMajorTick() +"" );
				bigDataJSon.append(", \"lineYMinorTick\" : " + bigDataBO.getLineYMinorTick() +"");
				bigDataJSon.append(", \"lineYMax\" : " + bigDataBO.getLineYMax() +"" );
				bigDataJSon.append(", \"lineXYAxis\" : [" +jsonFormatString(bigDataBO.getLineXYAxis()) + "]}");
			} else {
				// Should not come here as of now
			}
			System.out.println("***********ffffffffff***** BigDataJSon: "+bigDataJSon.toString());
			responseBuilder = Response.ok();		
			responseBuilder.type(MediaType.APPLICATION_JSON);  
			responseBuilder.entity(bigDataJSon.toString());   
		} catch (Exception ex) {
			ex.printStackTrace();
			return buildError("Error..."); 
		}
		return responseBuilder.build();   
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/loadTopo")
	public Response loadTopology(@QueryParam("chart") String daFilter){
		Response.ResponseBuilder responseBuilder = null;
		try {
			String provisioningJSon = null;
			List<TopoBO> dataBO = new ArrayList<TopoBO>();
			dataBO.add(new TopoBO("Alwin","10.78.203.26","60%","'30%","Online"));
			dataBO.add(new TopoBO("Tom","10.78.203.75","70%","50%","Offline"));
			dataBO.add(new TopoBO("Asterix","10.78.203.73","80%","32%","Online"));
			dataBO.add(new TopoBO("Black","10.78.203.89","70%","70%","Dead"));
			provisioningJSon = toJson("ecId", dataBO);
			responseBuilder = Response.ok();
			responseBuilder.type(MediaType.APPLICATION_JSON);
			responseBuilder.entity(provisioningJSon);
			return responseBuilder.build();
		 
		} catch (Exception ex) {
			return buildError("Error in loadAnalysisData...");
		}
	}
	
	private String jsonFormatString(List<String> str){
		  if(str == null || str.size() == 0) return "";
 		  StringBuilder formatted = new StringBuilder("");
	      for (int i = 0; i < str.size(); i++) {
	          formatted.append("\""+str.get(i)+"\"");
	          if (i < str.size() - 1) formatted.append(",");
	      }
	      
	      return formatted.toString();
	      
	}
	
	private String toJson(String identifier, Object obj) {
		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer();
		sb.append("{\"identifier\" : \"" + identifier + "\"");
		if(obj ==null )
			sb.append(", \"items\" : [] }");
		else
		sb.append(", \"items\" : " + gson.toJson(obj) + "}");

		return sb.toString();
	}
	
	private Response buildError(String error) {
		ResponseBuilder builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		builder.type(MediaType.TEXT_PLAIN);
		builder.entity(error);
		return builder.build();
	}
	
}
