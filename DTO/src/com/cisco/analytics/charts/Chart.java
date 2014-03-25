/*
######################################################
#
# @Copyright (c) 2014 by Cisco Systems, Inc.
#
# @Title Chart.java
# @Description This class represents the generic data model of Chart Widget
# @Version 1.0
# @author  vcbu_ui_team@cisco.com
#
######################################################
 */
package com.cisco.analytics.charts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "chart")
@XmlSeeAlso(value = { ChartType.class })
public abstract class Chart {

    @XmlElement(name="type")
    private ChartType type;

    public ChartType getType() {
	return type;
    }

    public void setType(ChartType type) {
	this.type = type;
    }

}
