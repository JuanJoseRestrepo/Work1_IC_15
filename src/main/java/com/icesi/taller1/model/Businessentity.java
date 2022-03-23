package com.icesi.taller1.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the businessentity database table.
 *
 */
@Entity
@NamedQuery(name = "Businessentity.findAll", query = "SELECT b FROM Businessentity b")
public class Businessentity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "BUSINESSENTITY_BUSINESSENTITYID_GENERATOR", allocationSize = 1, sequenceName = "BUSINESSENTITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUSINESSENTITY_BUSINESSENTITYID_GENERATOR")
	private Integer businessentityid;

	private Timestamp modifieddate;

	private Integer rowguid;



	public Businessentity() {
	}

	public Integer getBusinessentityid() {
		return this.businessentityid;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}


	public Integer getRowguid() {
		return this.rowguid;
	}


	public void setBusinessentityid(Integer businessentityid) {
		this.businessentityid = businessentityid;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}


	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

}