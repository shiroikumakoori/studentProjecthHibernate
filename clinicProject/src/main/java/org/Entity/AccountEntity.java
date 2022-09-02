package org.Entity;

import java.io.Serializable;

import javax.persistence.*;



@Entity
public class AccountEntity  {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "ID")
      private Integer accountId;
      String accountNum;
      

      public Integer getAccountId() {
		return accountId;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	@OneToOne(mappedBy = "account")
      private EmployeeEntity employee;

      //Other fields, getters, setters are hidden for brevity
}

