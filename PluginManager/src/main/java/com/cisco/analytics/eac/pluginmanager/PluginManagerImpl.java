/**
 * 
 */
package com.cisco.analytics.eac.pluginmanager;

import java.io.FileInputStream;
import java.io.IOException;

import com.cisco.analytics.eac.dto.cluster.DTO;
import com.cisco.analytics.eac.pluginmanager.factory.AbstractFactroy;
import com.cisco.analytics.eac.pluginmanager.helper.PluginManagerHelper;

/**
 * @author imajumde
 *
 */
public final class PluginManagerImpl implements IPluginManager {

	// eager loading and final
	//private static final IPluginManager manager = new PluginManagerImpl();
	
	// lazy loading and volatile
	private static volatile IPluginManager manager = null;
	
	// private constructor
	private PluginManagerImpl() {
		// TODO load time activities
	}
	
	// factory instance
	public static IPluginManager getPluginManager () {
		if (null == manager ) {
			synchronized (PluginManagerImpl.class) {
				if (null == manager) {
					manager = new PluginManagerImpl();
				}
			}
		}
		return manager;
	}
	
	
	/* (non-Javadoc)
	 * @see com.cisco.analytics.eac.eac.dto.pluginmanager.IPluginManager#run(String, String)
	 */
	@Override
	public DTO run(String usecase, String others) throws Exception {
		PluginManagerHelper helper = AbstractFactroy.getInstanceOfPluginManagerHelper();
		FileInputStream file = helper.run(helper.findEnv(usecase), helper.findExec(usecase)
				,helper.findInput(usecase),helper.findOuput(usecase), others);
		DTO dto = helper.format(file, DTO.class);
		return dto;
	}

	/* (non-Javadoc)
	 * @see com.cisco.analytics.eac.eac.dto.pluginmanager.IPluginManager#search(java.lang.String)
	 */
	@Override
	public DTO search(String commadelimKeyWords) {
		// TODO Auto-generated method stub
		return null;
	}

}
