/**
 * 
 */
package com.cisco.analytics.eac.pluginmanager.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cisco.analytics.eac.dto.charts.Chart;
import com.cisco.analytics.eac.dto.charts.ChartType;
import com.cisco.analytics.eac.dto.charts.LineChart;
import com.cisco.analytics.eac.dto.charts.PieChart;
import com.cisco.analytics.eac.dto.cluster.DTO;
import com.cisco.analytics.eac.dto.constants.Constants;
import com.cisco.analytics.eac.dto.tables.Column;
import com.cisco.analytics.eac.dto.tables.Table;
import com.cisco.analytics.eac.dto.tables.TableData;
import com.cisco.analytics.eac.dto.utils.ERRORCODE;
import com.cisco.analytics.eac.pluginmanager.factory.AbstractFactroy;
import com.cisco.analytics.eac.registry.IRegistry;
import com.cisco.analytics.eac.registry.mso.MSORegistry;


/**
 * @author imajumde
 *
 */
public class PluginManagerHelper implements Constants{

		private IRegistry registry;
		
		// eager loading and final
		//private static final PluginManagerHelper helper = new PluginManagerHelper();
		
		// lazy loading and volatile
		private static volatile PluginManagerHelper helper = null;
		
		// private constructor
		private PluginManagerHelper() {
			// TODO this also should be dynamic to support others registry
			registry = AbstractFactroy.getInstanceOfRegistry(MSORegistry.class);
			
		}
		
		// factory instance
		public static PluginManagerHelper getPluginManagerHelper() {
			if (null == helper ) {
				synchronized (PluginManagerHelper.class) {
					if (null == helper) {
						helper = new PluginManagerHelper();
					}
				}
			}
			return helper;
		}

		/**
		 * @param usecase 
		 * @return
		 * @throws IOException 
		 */
		public String findExec(String usecase) throws IOException {
			return registry.getProgram(usecase);
		}

		/**
		 * @param usecase 
		 * @return
		 * @throws IOException 
		 */
		public String findEnv(String usecase) throws IOException {
			return registry.getProgram(usecase);
		}
		
		/**
		 * @param usecase 
		 * @return
		 * @throws IOException 
		 */
		public String findInput(String usecase) throws IOException {
			return registry.getInputPath(usecase);
		}

		/**
		 * @param usecase 
		 * @return
		 * @throws IOException 
		 */
		public String findOuput(String usecase) throws IOException {
			return registry.getOutpath(usecase);
		}

		/**
		 * @param others - may be extra params for the map-reduce
		 * @param findExec
		 * @param findInput
		 * @param findOuput
		 * @return 
		 * @throws Exception 
		 */
		public FileInputStream run(String env, String exec, String input, String output, String others) throws Exception {
			 System.out.println("Running...");
			 Process p = null;
			 if (System.getenv("JAVA_HOME").isEmpty() || System.getenv("HADOOP_COMMON_HOME").isEmpty()) {
            	 p = Runtime.getRuntime().exec(env);
                 p.waitFor();
                 System.out.println("JAVA_HOME - " + System.getenv("JAVA_HOME"));
                 System.out.println("HADOOP_COMMON_HOME - " +System.getenv("HADOOP_COMMON_HOME"));
             } else {
            	 System.out.println("JAVA_HOME - " + System.getenv("JAVA_HOME"));
            	 System.out.println("HADOOP_COMMON_HOME - " +System.getenv("HADOOP_COMMON_HOME"));
             }
             p = null;
             p = Runtime.getRuntime().exec(exec + SPACE + input + SPACE + output + (others == null ? SPACE : others));
             error e = new error(p);
             Thread e_t = new Thread(e);
             e_t.start();
             info i = new info(p);
             Thread i_t = new Thread(i);
             i_t.start();
             int exit = p.waitFor();
             System.out.println("Exit : "+ exit);
             if (exit != 0) throw new Exception("Analysis could not be completed!!");
             return new FileInputStream(registry.getResourceDescriptor().getProperty(LAST_JOB_DIR)+registry.getResourceDescriptor().getProperty(JOB_OUTPUT));
     }

		/**
		 * @param file
		 * @param type 
		 * @return
		 * @throws IOException 
		 */
		public DTO format(FileInputStream file, Class<?> type) throws IOException {
			DTO dto = new DTO();
			List<Chart>   charts = new ArrayList<Chart>();
		    //List<Cluster> clusters = new ArrayList<Cluster>();
			List<Table> tables = new ArrayList<Table>(); 
			boolean drawpie = false;
			boolean drawline = false;
			LineChart linech = new LineChart();
			linech.setType(ChartType.LINE);
			PieChart piech = new PieChart();
			piech.setType(ChartType.PIE);
			charts.add(piech);
			charts.add(linech);
			Table table = new Table();
			TableData tdata = new TableData();
			table.setData(tdata);
			tables.add(table);
			dto.setTables(tables);
			List<Map<String, String>> items = new ArrayList<Map<String,String>>();
			List<String> colString = new ArrayList<String>();
			Scanner scan = new Scanner(file);
			String line = scan.nextLine();
			// getting columns
			String cols[] = line.split(registry.getResourceDescriptor().getProperty(UNIVERSAL_DELIMETER_PROP, UNIVERSAL_DELIMETER));
			for (int i = 0 ; i < cols.length ; i=i+2) {
				colString.add(cols[i].trim().toUpperCase());
			}
			table.setCols(fillcolumns(colString));
			tdata.setIdentifier(table.getCols().get(0).getAttr().trim().toLowerCase());
			if (isSuitableForLineChart(line)) { 
				drawpie = true;
				linech.setLineTitles(colString);
				//linech.se
			}
			List<Double> pieData = new ArrayList<Double>();
			if (isSuitableForPieChart(line)) { 
				drawline = true;
				piech.setPieTitles(colString);
				piech.setPieYTooltips(colString);
				piech.setPieData(pieData);
			}
			while (scan.hasNext()) {
				// one row
				Map<String, String> map = new LinkedHashMap<String, String>();
				// PID:=:{data} :=:TIMESTAMP:=:{data}:=:IP:=:{data}:=:REASON:=:{data}
				//getting data
				String data[] = line.split(registry.getResourceDescriptor().getProperty(UNIVERSAL_DELIMETER_PROP, UNIVERSAL_DELIMETER));
				System.out.println(data.length);
				for (int i = 0 ; i < data.length ; i=i+2) {
					System.out.println(data[i] + " :: " + data[i+1]);
					// table data
					map.put(data[i].trim().toLowerCase(), data[i+1].trim());
					// pie data
					if (drawpie) pieData.add(Double.parseDouble(data[i+1].trim()));
					// line data
					if (drawline) {}
				}
				items.add(map);
				line = scan.nextLine();
			}
			tdata.setItems(items );
			dto.setMsgCode(ERRORCODE.SUCCESS);
			dto.setMsgDesc("All Data loaded");
			return dto;
		}

		/**
		 * @param line 
		 * 
		 */
		private List<Column> fillcolumns(List<String> line) {
			List<Column> cols = new ArrayList<Column>();
			for (String label : line) {
				Column col = new Column();
				col.setAttr(label.trim().toLowerCase());
				col.setEditable(false);
				col.setHidden(false);
				col.setLabel(label.trim().toUpperCase());
				col.setSortable(true);
				//col.se
				cols.add(col);
			}
			return cols;
		}
		
		// Has to have two columns
		// 2nd column must be numeric [count]
		// bound by a timerange
		private boolean isSuitableForPieChart(String line) throws IOException{
			boolean is = false;
			String data[] = line.split(registry.getResourceDescriptor().getProperty(UNIVERSAL_DELIMETER_PROP, UNIVERSAL_DELIMETER));
			is = data.length > 1 ? true : false;;
			try {
				for (int i = 0 ; i < data.length ; i=i+2) {
					System.out.println(data[i] + " :: " + data[i+1]);
					Double.parseDouble(data[i+1].trim());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				is = false;
			}
			return is;
		}
		
		// atleast 3 columns
		// x - Month/Year y - count Z - type
		private boolean isSuitableForLineChart(String line) {
			return false;
		}
}


class error implements Runnable {
	Process  p = null;
	public error(Process  p) {
		this.p = p;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
            Scanner error = new Scanner(p.getErrorStream());
            if (error.hasNext()) System.out.println("ERROR : " + error.nextLine());
            error.close();
		} catch (Exception ex) {
            ex.printStackTrace();
		}
	}
	
}


class info implements Runnable {
	Process  p = null;
	public info(Process  p) {
		this.p = p;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
            Scanner info = new Scanner(p.getInputStream());
            if (info.hasNext()) System.out.println("ERROR : " + info.nextLine());
            info.close();
            //OutputStream stdout = p.getOutputStream();
		} catch (Exception ex) {
            ex.printStackTrace();
		}
	}
}