package com.rbank.customer.statement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Michael Philomin Raj
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD) 
@Getter @Setter
public class RecordDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@XmlAttribute(name = "reference")
	private String transactionReference;

	@JsonIgnore
	private String accountNumber;

	private String description;

	@JsonIgnore
	private String startBalance;

	@JsonIgnore
	private String mutation;

	@JsonIgnore
	private String endBalance;


	private boolean isValid = true;

	private List<String> failureReasons = new ArrayList<>();	

}
