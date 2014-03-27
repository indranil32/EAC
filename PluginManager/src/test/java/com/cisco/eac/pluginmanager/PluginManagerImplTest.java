/**
 * 
 */
package com.cisco.eac.pluginmanager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.cisco.analytics.cluster.DTO;
import com.cisco.analytics.tables.Table;
import com.cisco.eac.pluginmanager.factory.AbstractFactroy;

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
	public void run() {
		DTO dto = manager.run("CMD2K_REBOOT", null);
		// cannot be null
		Assert.assertNotNull(dto);
		for ( Table  table : dto.getTables()) {
			Assert.assertNotNull(table.getCols());
			Assert.assertNotNull(table.getItems());
			Assert.assertNotNull(table.getIdentifier());
			for (String key : table.getItems().get(0).keySet()) {
				System.out.println(key);
			}
		}
	}
	
	public void search () {
		
	}
}
