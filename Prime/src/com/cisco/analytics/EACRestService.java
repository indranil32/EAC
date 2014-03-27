/**
 * 
 */
package com.cisco.analytics;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cisco.analytics.cluster.DTO;
import com.cisco.analytics.utils.EACUtils;
import com.cisco.eac.pluginmanager.IPluginManager;
import com.cisco.eac.pluginmanager.factory.AbstractFactroy;

/**
 * @author imajumde
 *
 */
public class EACRestService implements IDelegate{

	
	/* (non-Javadoc)
	 * @see com.cisco.analytics.IDelegate#run(java.lang.String, java.lang.String)
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/run")  
	@Override
	public Response run(@QueryParam("usecase")String usecase, @QueryParam("others")String others) {
		Response response = null;
		Logger.getAnonymousLogger().log(Level.INFO,"EACRestService::run() : " + usecase);
		try {
			IPluginManager manager = AbstractFactroy.getInstanceOfPluginManager();
			DTO dto = manager.run(usecase, others);
			Logger.getAnonymousLogger().log(Level.INFO,"EACRestService::run() : DTO loaded");
			response = EACUtils.buildResponse(dto);
		} catch (Exception ex) {
			response = EACUtils.buildError(ex.getMessage());
		}
		return response;
	}
	
}
