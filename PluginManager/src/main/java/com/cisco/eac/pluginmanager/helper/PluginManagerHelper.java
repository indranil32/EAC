/**
 * 
 */
package com.cisco.eac.pluginmanager.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.cisco.analytics.cluster.DTO;
import com.cisco.analytics.constants.Constants;
import com.cisco.analytics.tables.Column;
import com.cisco.analytics.tables.Table;
import com.cisco.analytics.tables.TableData;
import com.cisco.analytics.utils.ERRORCODE;
import com.cisco.eac.pluginmanager.factory.AbstractFactroy;
import com.cisco.registry.IRegistry;
import com.cisco.registry.mso.MSORegistry;


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
		 * @param findExec
		 * @param findInput
		 * @param findOuput
		 * @return 
		 * @throws Exception 
		 */
		public FileInputStream run(String exec, String input, String ouput) throws Exception {
			//int exit = Runtime.getRuntime().exec(exec).waitFor();
			//if (exit != 0) throw new Exception("Analysis could not be completed!!");
			//return new FileInputStream(LAST_JOB_OUTPUT);
			return new FileInputStream(new File("C:\\Users\\imajumde\\git\\EAC\\PluginManager\\src\\main\\resources\\analysis.out"));
		}

		/**
		 * @param file
		 * @param type 
		 * @return
		 */
		public DTO format(FileInputStream file, Class<?> type) {
			DTO dto = new DTO();
			List<Table> tables = new ArrayList<Table>(); 
			Table table = new Table();
			TableData tdata = new TableData();
			List<Map<String, String>> items = new ArrayList<Map<String,String>>();
			List<String> colString = new ArrayList<String>();
			boolean foundcolumns = true;
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				// one row
				Map<String, String> map = new LinkedHashMap<String, String>();
				String line = scan.nextLine();
				// PID:=:{data} TIMESTAMP:=:{data}:=:IP:=:{data}:=:REASON:=:{data}
				if (foundcolumns) {
					// getting columns
					String cols[] = line.split(UNIVERSAL_DELIMETER);
					for (int i = 0 ; i < cols.length ; i=i+2) {
						colString.add(cols[i].trim().toUpperCase());
					}
					foundcolumns = false;
				}
				//getting data
				String data[] = line.split(UNIVERSAL_DELIMETER);
				System.out.println(data.length);
				for (int i = 0 ; i < data.length ; i=i+2) {
					System.out.println(data[i] + " :: " + data[i+1]);
					map.put(data[i].trim().toLowerCase(), data[i+1].trim());
				}
				items.add(map);
			}
			table.setCols(fillcolumns(colString));
			tdata.setIdentifier(table.getCols().get(0).getAttr().trim().toLowerCase());
			tdata.setItems(items );
			table.setData(tdata);
			tables.add(table);
			dto.setTables(tables);
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
}
