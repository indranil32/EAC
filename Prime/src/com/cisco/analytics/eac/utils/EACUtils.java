/**
 * 
 */
package com.cisco.analytics.eac.utils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cisco.analytics.eac.dto.cluster.DTO;
import com.cisco.analytics.eac.dto.utils.ERRORCODE;
import com.google.gson.Gson;

/**
 * @author imajumde
 *
 */
public class EACUtils {
	private static Gson gson = new Gson();
	
	public static Response buildResponse(DTO dto) {
		return Response.ok(gson.toJson(dto), MediaType.APPLICATION_JSON).build();
	}

	/**
	 * @param message
	 * @return
	 */
	public static Response buildError(String message) {
		Response.ResponseBuilder builder = Response.serverError();
		DTO dto = new DTO();
		dto.setMsgDesc(message);
		dto.setMsgCode(ERRORCODE.SERVERERROR);
		builder.entity(dto);
		builder.type(MediaType.APPLICATION_JSON);
		return builder.build();
	}
	
}
