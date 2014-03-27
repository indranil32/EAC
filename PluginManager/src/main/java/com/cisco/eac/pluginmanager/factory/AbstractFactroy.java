/**
 * 
 */
package com.cisco.eac.pluginmanager.factory;

import com.cisco.eac.pluginmanager.IPluginManager;
import com.cisco.eac.pluginmanager.PluginManagerImpl;
import com.cisco.eac.pluginmanager.helper.PluginManagerHelper;
import com.cisco.registry.AbstractRegistry;
import com.cisco.registry.IRegistry;

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
