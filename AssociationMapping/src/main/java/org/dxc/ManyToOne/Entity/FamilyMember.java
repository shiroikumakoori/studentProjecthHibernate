package org.dxc.ManyToOne.Entity;


import javax.persistence.*;

@Entity
public class FamilyMember {
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)    
	private int familyMemberId;    
	private String name;    
	
	@ManyToOne(cascade=CascadeType.ALL)  
	private Address address;  
	
    public int getFamilyMemberId() {
		return familyMemberId;
	}

	public void setFamilyMemberId(int familyMemberId) {
		this.familyMemberId = familyMemberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	public void setAddressJoin(Address addr) {
		this.address = addr;
		address.setfMember(this);
	}


	

}



