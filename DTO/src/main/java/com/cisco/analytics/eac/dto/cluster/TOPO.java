/**
 * @Title TOPO.java
 *
 * @Description  
 *
 * @Project DataTransferObjects
 * @Author imajumde
 * @Version 1.0
 * @Copyright (c) 2001, 2011-2012 by Cisco Systems, Inc.
 *
 * @History Mar 14, 2014
 */
package com.cisco.analytics.eac.dto.cluster;

import java.util.List;

/**
 * TOPO - 
 *
 */

public class TOPO {
    
    private List<Cluster> children;

    public List<Cluster> getChildren() {
        return children;
    }

    public void setChildren(List<Cluster> children) {
        this.children = children;
    }
    
}
