/**
 * 
 */
package com.cisco.eac.pluginmanager.helper;

import java.io.FileInputStream;
import java.io.IOException;

import com.cisco.analytics.cluster.DTO;
import com.cisco.analytics.constants.Constants;
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
			int exit = Runtime.getRuntime().exec(exec).waitFor();
			if (exit != 0) throw new Exception("Analysis could not be completed!!");
			return new FileInputStream(LAST_JOB_OUTPUT);
		}

		/**
		 * @param file
		 * @param type 
		 * @return
		 */
		public DTO format(FileInputStream file, Class<?> type) {
			DTO dto = new DTO();
			return null;
		}
}
