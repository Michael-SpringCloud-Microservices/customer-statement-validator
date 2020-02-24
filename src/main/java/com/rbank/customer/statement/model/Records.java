package com.rbank.customer.statement.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Michael Philomin Raj
 *
 */

@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD) 
@Getter @Setter
public class Records
{
	@XmlElement(name = "record")
	private RecordDetail[] recordDetails;
}
