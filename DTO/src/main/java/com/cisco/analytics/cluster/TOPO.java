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
package com.cisco.analytics.cluster;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * TOPO - 
 *
 */

@XmlRootElement(name = "topo")
@XmlSeeAlso(value={Cluster.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class TOPO {
    
    @XmlElement(name="children")
    private List<Cluster> children;

    public List<Cluster> getChildren() {
        return children;
    }

    public void setChildren(List<Cluster> children) {
        this.children = children;
    }
    
}
