/**
 * 
 */
package com.cisco.analytics.eac.pluginmanager.factory;

import com.cisco.analytics.eac.pluginmanager.IPluginManager;
import com.cisco.analytics.eac.pluginmanager.PluginManagerImpl;
import com.cisco.analytics.eac.pluginmanager.helper.PluginManagerHelper;
import com.cisco.analytics.eac.registry.AbstractRegistry;
import com.cisco.analytics.eac.registry.IRegistry;

/**
 * The implementation is as per requirement
 * 
 * @author imajumde
 *
 */
public abstract class AbstractFactroy {

	public static IPluginManager  getInstanceOfPluginManager() {
			return PluginManagerImpl.getPluginManager();
	}
	
	public static PluginManagerHelper  getInstanceOfPluginManagerHelper() {
		return PluginManagerHelper.getPluginManagerHelper();
	}
	
	public static IRegistry  getInstanceOfRegistry(Class<? extends AbstractRegistry> c) {
		return AbstractRegistry.getRegistry(c);
	}
	
}
