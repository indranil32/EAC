/**
 * 
 */
package com.cisco.analytics.eac.pluginmanager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.cisco.analytics.eac.dto.cluster.DTO;
import com.cisco.analytics.eac.dto.tables.Table;
import com.cisco.analytics.eac.pluginmanager.IPluginManager;
import com.cisco.analytics.eac.pluginmanager.factory.AbstractFactroy;

/**
 * @author imajumde
 *
 */
public class PluginManagerImplTest {
	IPluginManager manager;
	
	@Before
	public void init(){
		manager = AbstractFactroy.getInstanceOfPluginManager();
	}
	
	@Test
	public void run() throws Exception {
		DTO dto = manager.run("CMD2K_REBOOT", null);
		// cannot be null
		Assert.assertNotNull(dto);
		for ( Table  table : dto.getTables()) {
			Assert.assertNotNull(table.getCols());
			Assert.assertNotNull(table.getData().getItems());
			Assert.assertNotNull(table.getData().getIdentifier());
			for (String key : table.getData().getItems().get(0).keySet()) {
				System.out.println(key);
			}
		}
	}
	
	public void search () {
		
	}
}
