package org.Entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class EmployeeEntity  { 

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "ID")
      private Integer employeeId;
      String firstName;

      @OneToOne
      @JoinColumn(name="ACCOUNT_ID")
      private AccountEntity account;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

      //Other fields, getters, setters are hidden for brevity
}