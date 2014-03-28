/**
 * 
 */
package com.cisco.analytics.eac.registry;

import java.io.IOException;
import java.util.Properties;

/**
 * @author imajumde
 *
 */
public abstract class AbstractRegistry implements IRegistry{

	// resource description loading
	public Properties prop;
	
	// lazy loading and volatile
	private volatile static IRegistry registry;
	
	// factory singelton method
	public static IRegistry getRegistry(Class<? extends AbstractRegistry> c) {
		if ( null == registry ) {
			synchronized (AbstractRegistry.class) {
				if ( null == registry ) {
					try {
						registry = c.newInstance();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return registry;
	}
	
	// load resource description
	public abstract void loadResourceDescription() throws IOException;
	
}
