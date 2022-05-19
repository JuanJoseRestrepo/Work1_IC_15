package com.icesi.taller1.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.icesi.taller1.model.sales.*;

/**
 * The persistent class for the address database table.
 *
 */
@Entity
@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADDRESS_ADDRESSID_GENERATOR", allocationSize = 1, sequenceName = "ADDRESS_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_ADDRESSID_GENERATOR")
	private Integer addressid;
	
	@NotBlank(groups = BasicInfo.class)
	private String addressline1;

	private String addressline2;
	@Size(min=3,groups = BasicInfo.class)
	private String city;

	private Timestamp modifieddate;
	@Pattern(regexp="^(0|[1-9][0-9]*)$",groups = BasicInfo.class)
	@Size(min=6, max=6,groups = BasicInfo.class)
	private String postalcode;

	private Integer rowguid;

	private String spatiallocation;
	
	@OneToMany(mappedBy = "shiptoaddress")
	private List<Salesorderheader> salesorderheaders;

	// bi-directional many-to-one association to Stateprovince
	@NotNull(groups = BasicInfo.class)
	@ManyToOne
	@JoinColumn(name = "stateprovinceid")
	private Stateprovince stateprovince;

	// bi-directional many-to-one association to Businessentityaddress
	/**
	@OneToMany(mappedBy = "address")
	private List<Businessentityaddress> businessentityaddresses;
	**/
	public Address() {
	}


	public Integer getAddressid() {
		return this.addressid;
	}

	public String getAddressline1() {
		return this.addressline1;
	}

	public String getAddressline2() {
		return this.addressline2;
	}


	public String getCity() {
		return this.city;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public String getSpatiallocation() {
		return this.spatiallocation;
	}

	public Stateprovince getStateprovince() {
		return this.stateprovince;
	}


	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}


	public void setCity(String city) {
		this.city = city;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setSpatiallocation(String spatiallocation) {
		this.spatiallocation = spatiallocation;
	}

	public void setStateprovince(Stateprovince stateprovince) {
		this.stateprovince = stateprovince;
	}

}