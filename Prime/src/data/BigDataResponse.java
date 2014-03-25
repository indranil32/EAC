/**
 * Copyright (c) 2012 by Cisco Systems, Inc.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Cisco Systems,  ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Cisco Systems.
 *
 *
 * @Project OAM
 * @Author amonninn
 * @Version 1.0
 */
 
package data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name ="bigdataResponse")
public class BigDataResponse {

	 private List<? extends Object> items;
	    private String identifier;
	    private int msgCode;
	    private String msgDesc;
	    
	    public BigDataResponse(){}
	    
	    public List<? extends Object> getItems() {
			return items;
		}
		
	    public void setItems(List<? extends Object> items) {
			this.items = items;
		}
		
	    public String getIdentifier() {
			return identifier;
		}
		
	    public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}

		public int getMsgCode() {
			return msgCode;
		}

		public void setMsgCode(int msgCode) {
			this.msgCode = msgCode;
		}

		public String getMsgDesc() {
			return msgDesc;
		}

		public void setMsgDesc(String msgDesc) {
			this.msgDesc = msgDesc;
		}		

}
