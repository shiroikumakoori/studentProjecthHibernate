package org.dxc.ManyToOne.Entity;

import javax.persistence.*;

@Entity
public class Address {

	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
    private int addressId;    
    private String address,blk;    
    private int postalCode;    
    
   @OneToOne(cascade=CascadeType.ALL)  
    private FamilyMember fMember;  
   
	 public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBlk() {
		return blk;
	}

	public void setBlk(String blk) {
		this.blk = blk;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public FamilyMember getfMember() {
		return fMember;
	}

	public void setfMember(FamilyMember fMember) {
		this.fMember = fMember;
	}


}


