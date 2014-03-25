/*
 * File 	: 	dataAnalytics.js
 * Comments	:	This file dynamically loads the data got from backend. 
 */

// Variable
var BIG_DATA_URL="rs/bigdata?chart=";	
var pieChart=null;
var chartbc=null;
var legendbc=null;
var legendpi=null;
var INIT_LOAD=true;
// Constants
var CHART_TYPE_PIE = "PIE";
var CHART_TYPE_LINE = "LINE";

var dataAnalysisCols = [  {
							label: 'Node Name',
							attr: 'name',
							width: 120,
							sortable:true
						
						},
						{
							label: 'Node IP Address',
							attr: 'ipAddr',
							width: 130,
							sortable:true							
						},
						{
							label: 'Node Type',
							attr: 'type',
							width:120,
							sortable:true
							
						}
							
						];


var dataAnalysisData={"items":[{"name":"tom-ucs","ipAddr":"10.78.203.75","type":"EC"},
                            	 {"name":"dn2","ipAddr":"10.78.216.96","type":"Linux Data Node"},
                            	 {"name":"tintin-ec","ipAddr":"10.78.203.206","type":"EC"},
                            	 {"name":"tintin-ecs-mgmt","ipAddr":"10.78.203.208","type":"ECS Node"},
                            	 {"name":"samba-mgmt","ipAddr":"10.78.203.219","type":"ECS Node"},
                            	 {"name":"devjbossslave1","ipAddr":"10.78.203.225","type":"IVSP Nod"}
                            	 ],"identifier":"name"};

var dataAnalysisStore = new dojo.data.ItemFileReadStore({
	data: dataAnalysisData,
	 clearOnClose: true,
	 urlPreventCache:true
});

/*
 * TEST DATA
 */


var bigDataJson={
		"pieYTooltips":["1,210 million","952 million","125 million"],
		"pieTitles":["Dhoom","Dhoom 2","300"],
		"pieData":[12.1,4.52,1.25],
		"lineTitles":["300","Hunger Games 2"],
		"lineData":[[2600, 1800, 2000, 1000, 1400, 700, 2000],
		             [6300, 1800, 3000, 500, 4400, 2700, 2000]
		             ],
		 "lineXMajorTick":1,
		 "lineXMinorTick":0.1,
		 "lineYMajorTick":1000,
		 "lineYMinorTick":100,
		 "lineYMax":7000,
	     "lineXYAxis":["Month","No of views"]
		 
};

 var DUMMY_RESP = {
		    "charts": [
		               {
		            	   "pieYTooltips":["1,210 million","952 million","125 million"],
		           		"pieTitles":["Dhoom","Dhoom 2","300"],
		           		"pieData":[12.1,4.52,1.25],		           	
		                   "type": "PIE"
		               },
		               {
		            	   "lineTitles":["300","Hunger Games 2"],
		           		"lineData":[[2600, 1800, 2000, 1000, 1400, 700, 2000],
		           		             [6300, 1800, 3000, 500, 4400, 2700, 2000]
		           		             ],
		           		 "lineXMajorTick":1,
		           		 "lineXMinorTick":0.1,
		           		 "lineYMajorTick":1000,
		           		 "lineYMinorTick":100,
		           		 "lineYMax":7000,
		           	     "lineXYAxis":["Month","No of views"],
		           	     "type": "LINE"
		               }
		           ],
		           "tables": [
		               {
		                   "cols": [
		                            {
		    							label: 'Node Name',
		    							attr: 'name',
		    							width: 120,
		    							sortable:true
		    						
		    						},
		    						{
		    							label: 'Node IP Address',
		    							attr: 'ipAddr',
		    							width: 130,
		    							sortable:true							
		    						},
		    						{
		    							label: 'Node Type',
		    							attr: 'type',
		    							width:120,
		    							sortable:true
		    							
		    						}
		                   ],
		                  "data" : { "items":[
		                            	{'name':'tom-ucs','ipAddr':'10.78.203.75','type':'EC'},
		                            	{'name':'devjbossslave1','ipAddr':'10.78.203.225','type':'IVSP Node'}
		                             ],
		                             "identifier": "name"
		                  	}	
		               }
		               
		           ],
		           "clusters": [
		               {
		                   "name": "tom",
		                   "type": "Minor",
		                   "children": [
		                       {
		                           "name": "linuxnode",
		                           "type": "Clear"
		                       }
		                   ]
		               }
		           ],
		           "msgDesc": "Success",
		           "msgCode": 0
		       };
 
 var DUMMY_RESP1 = {
		    "charts": [
		               {
		            	   "pieYTooltips":["1,210 million","952 million","125 million"],
		           		"pieTitles":["Troy","Dhoom 2","300"],
		           		"pieData":[1.1,14.52,21.25],		           	
		                   "type": "PIE"
		               },
		               {
		            	   "lineTitles":["Troy","Hunger Games 2", "Gladiator"],
		           		"lineData":[[1600, 2800, 3000, 1000,  2000],
		           		             [4300, 1800, 1000, 1500, 2400],
		           		          [300, 100, 6000, 500, 2000]
		           		             ],
		           		 "lineXMajorTick":1,
		           		 "lineXMinorTick":0.1,
		           		 "lineYMajorTick":1000,
		           		 "lineYMinorTick":100,
		           		 "lineYMax":7000,
		           	     "lineXYAxis":["Month","No of views"],
		           	     "type": "LINE"
		               }
		           ],
		           "tables": [
		               {
		                   "cols": [
		                            {
		    							label: 'Node Name',
		    							attr: 'name1',
		    							width: 120,
		    							sortable:true
		    						
		    						},
		    						{
		    							label: 'Node IP Address',
		    							attr: 'ipAddr1',
		    							width: 130,
		    							sortable:true							
		    						},
		    						{
		    							label: 'Node Type',
		    							attr: 'type',
		    							width:120,
		    							sortable:true
		    							
		    						}
		                   ],
		                  "data" :{ "items":[
		                            	{'name':'tom-ucs-sec','ipAddr':'10.78.203.75','type':'EC'},
		                            	{'name':'devjbossslave1-sec','ipAddr':'10.78.203.225','type':'IVSP Node'}
		                    	 ],
		                   "identifier": "name"
		                  }
		               }
		               
		           ],
		           "clusters": [
		               {
		                   "name": "tom",
		                   "type": "Minor",
		                   "children": [
		                       {
		                           "name": "linuxnode",
		                           "type": "Clear"
		                       }
		                   ]
		               }
		           ],
		           "msgDesc": "Success",
		           "msgCode": 0
		       };
/* end of TEST DATA*/

/*
 * Api' for Data Retrieval :- Rest call Interation with backend to retrieve data
 */
var counter = 0;
function makeGetCall(bigUrl){
	/*dojo.xhrGet({url:bigUrl, 
		sync:true,
		headers:{"Content-Type":"application/json"}, 
		handleAs:"json"}).addCallback(function(results){
			bigDataJson = results;
	});	*/
	counter++;
	var respData = DUMMY_RESP;
	if( counter % 2 == 0  ) respData = DUMMY_RESP1;
	
	if(respData!=null && respData!=""){
		renderComponents(respData);// charts, tables, clusters
   	}else{
   		console.error("Got null Data...");
   	}
}

function reloadCharts(){
	if(INIT_LOAD==true){
		makeGetCall(BIG_DATA_URL+"ivsp"); 
		INIT_LOAD=false;
	}else{
		var comboWidget = dijit.byId("trend");
		//var selChart=comboWidget.attr("displayedValue");
		var selValue=comboWidget.attr("value");
	    makeGetCall(BIG_DATA_URL+selValue); 
	}
	
}

//Render Components - charts, tables, clusters
function renderComponents(respData){
	buildCharts( respData.charts );
	buildTables( respData.tables );
	//buildClusters( respData.clusters );
}


/*********** end of Api' for Data Retrieval **********/

/*
 * Api's for Building Charts
 */

function buildCharts(/* array of charts*/ chartDataList){
	var len = chartDataList ? chartDataList.length : 0;
	for(var i=0; i < len; i++){
		var chartData = chartDataList[i];
		buildChartsDelegate(chartData.type, chartData);
	}
	
}
function buildChartsDelegate(chartType, chartData){
	if( chartType ==  CHART_TYPE_PIE ){
		buildPieChart(chartData);
	}else if( chartType ==  CHART_TYPE_LINE ){
		buildLineChart(chartData)
	}else{
		console.error("Invalid CHART type in response.")
	}

}

function buildPieChart(chartData){
	var pieJson=[];
	  for(var i=0;i<chartData.pieTitles.length;i++){
		  var tmp= {
					y: chartData.pieData[i],
					text: chartData.pieTitles[i],
					tooltip: chartData.pieYTooltips[i]
				};
		  pieJson[i]=tmp;
	  }
		pieChart.addSeries("",pieJson);
		pieChart.render();

}
/*
function buildLineChart(chartData){
	var chartTitle="";
	try{
	  for(var i=0;i<chartData.lineTitles.length;i++){
		  chartTitle=chartTitle+chartData.lineTitles[i];
		  if(i!=chartData.lineTitles.length-1){
			  chartTitle=chartTitle+" Vs "; 
		  }
	  }
	  
        chartbc.addAxis("x", {
            title: chartData.lineXYAxis[0], 
            titleOrientation: "away", 
            majorTickStep: chartData.lineXMajorTick,
            minorTickStep:chartData.lineXMajorTick, 
            type: "Default"
        })
        .addAxis("y", {
            vertical: true, 
            majorTickStep: chartData.lineYMajorTick,
            minorTickStep: chartData.lineYMinorTick, 
            type: "Default",
            max: chartData.lineYMax
        }).addAxis("y-title", {
            title: chartData.lineXYAxis[1],
            type: xwt.widget.charting.axis2d.TextOnly
        });
        for(var i=0;i<chartData.lineTitles.length;i++){
        	chartbc.addSeries(chartData.lineTitles[i],chartData.lineData[i]);
        } 
    chartbc.render();
    
	    
	}catch(e){
		alert(e);
	}
}*/

function buildLineChart(chartData){
	var chartTitle="";
	try{
	  for(var i=0;i<chartData.lineTitles.length;i++){
		  chartTitle=chartTitle+chartData.lineTitles[i];
		  if(i!=chartData.lineTitles.length-1){
			  chartTitle=chartTitle+" Vs "; 
		  }
	  }
		
		/*destroy previous chart widget*/
	  if( chartbc )	{	chartbc.destroy();}
	  if( legendbc ) {  legendbc.destroy();}
	  
	  /* create a new chart widget*/
	  chartbc = new dojox.charting.Chart("chartbc",{
		        title:""
		    });
	
		// 1. set themes and plots
	  chartbc.setTheme(xwt.widget.charting.themes.Prime);		      
		//tension with curved lines
		chartbc.addPlot("default", {type: "Lines",tension: 3, markers: true})
				.addPlot( "fills", { type: xwt.widget.charting.plot2d. 		ThresholdLines,  rightBorder: true, topBorder: true })
				.addPlot( "grid", { type: xwt.widget.charting.plot2d.Grid, hMajorLines: true, vMajorLines: false});
		// 2. set legends
	    legendbc = new xwt.widget.charting.widget.Legend({chart: chartbc, horizontal: true}, "legendbc");
	    
	    // 3. set axis - x, y, y-title
        chartbc.addAxis("x", {
            title: chartData.lineXYAxis[0], 
            titleOrientation: "away", 
            majorTickStep: chartData.lineXMajorTick,
            minorTickStep:chartData.lineXMajorTick, 
            type: "Default"
        })
        .addAxis("y", {
            vertical: true, 
            majorTickStep: chartData.lineYMajorTick,
            minorTickStep: chartData.lineYMinorTick, 
            type: "Default",
            max: chartData.lineYMax
        }).addAxis("y-title", {
            title: chartData.lineXYAxis[1],
            type: xwt.widget.charting.axis2d.TextOnly
        });
		// 4. set the series to chart
        for(var i=0;i<chartData.lineTitles.length;i++){
        	chartbc.addSeries(chartData.lineTitles[i],chartData.lineData[i]);
        } 
		// 5. render the chart details
		chartbc.render();
    
	    
	}catch(e){
		alert(e);
	}
}

/*********** end of Building Charts **********/

/*
 * Api's for Building Tables	[PLACEHOLDERS]
 */
function buildTables(/* array of tables*/ tableDataList){
	var len = tableDataList ? tableDataList.length : 0;
	for(var i=0; i < len; i++){
		buildTableData(tableDataList[i]);
	}
	
}

function buildTableData( tablesData){
	var djTable = dijit.byId("tablece");
	djTable.store.close();

	djTable.store= new dojo.data.ItemFileReadStore({
		data: tablesData.data,
		 clearOnClose: true,
		 urlPreventCache:true
	});
	
	djTable.render();
}


/**** end of Building Tables ****/

/*
 * Api's for Building Clusters [PLACEHOLDERS]
 */

function buildClusters( clusterData ){
	
}

/**** end of Building Clusters ****/

/*
 * Notification Methods
 */
function notifyAlert(message){
	var alert = new xwt.widget.notification.Alert({
		messageType:"warning",
		buttons: [{ label: "OK" } ],
		dontShowAgainOption: false
	});
	alert.setDialogContent(message);
	return alert; 
}

function warningAlert( message , actionHandler ){

	var alert = new xwt.widget.notification.Alert({
						messageType:"warning",
						buttons: [
						    {
						    	label: "OK",
						    	onClick: actionHandler
						    },
						    {
						    	label: "Cancel"
						    }
						 ],
						 dontShowAgainOption: false
					});
	alert.setDialogContent(message);	
	alert.show();

	return alert;
}