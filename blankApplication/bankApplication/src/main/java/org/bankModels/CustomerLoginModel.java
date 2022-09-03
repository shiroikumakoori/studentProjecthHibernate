package org.bankModels;


import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CustomerLoginModel extends PersonlModel {

	String _name;
	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	int _amt;
	String _loanType;
	String _email;
	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public String get_loanType() {
		return _loanType;
	}

	public void set_loanType(String _loanType) {
		this._loanType = _loanType;
	}

	public int get_amt() {
		return _amt;
	}

	public void set_amt(int _amt) {
		this._amt = _amt;
	}

	public CustomerLoginModel(String userName, String password) {
		super(userName, password);
		set_Table( DbFunction.TBs.tb_customerdetails);
		// TODO Auto-generated constructor stub
	}
	
	public List<CustomerLoginModel> getAllcusModel()
	{
		// TODO customer detali not done 
		List<CustomerLoginModel> list  =  new ArrayList();
		try {
			var query = "select userName from "+  DbFunction.TBs.tb_customerdetails;
			ResultSet res = DbFunction.getEntry(get_Table() , new ArrayList(), "");
	    	while(res.next())
	    	{	
	    		CustomerLoginModel cus = new CustomerLoginModel("","");
	    		cus.get_loginDetails().set_userName(res.getString(1));
	    		cus.set_name(res.getString(3));
	    		cus.set_amt(res.getInt(4));
	    	
	    	
	    		
	    		list.add(cus);
	    	}
	    	return list;
		} catch (Exception e) {
			// TODO: handle exception
			return list;
		}
	
	
	}
	public void addTransaction(boolean isDebit , String desc)
	{
		List l = new ArrayList<>();
		TransactionModel m = new TransactionModel();
		m.set_userName(_loginDetails.get_userName());
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sdf.format(dt);
   
		m.set_description(desc);

		l.add(    sdf.format(dt));
		l.add(desc);
		if(isDebit)
		{
			m.set_debit(_amt);
			l.add(0);
			l.add(_amt);
		}
		else
		{
			m.set_credit(_amt);
			l.add(_amt);
			l.add(0);
		}
		
		DbFunction.insertEntry(DbFunction.TBs.tb_transaction,l, "_"+_loginDetails.get_userName());
		
	}
	
	public void updatePassword()
	{
		// TODO customer detali not done 
		List list  =  new ArrayList();
		list.add(_loginDetails.get_password());
		list.add(_loginDetails.get_userName());

		var query = "password=? where userName=? ";
		UpdateEntry(_tb, list, query);
			
	}
	public void updateMoney()
	{
		List list  =  new ArrayList();
		list.add(_amt);
		list.add(_loginDetails.get_userName());

		var query = "amt=? where userName=? ";
		UpdateEntry(_tb, list, query);
	}
	public void debitMoney(int amt, String string)
	{
		
		int balance = _amt + amt;
		_amt = amt;
		addTransaction(true, string);
		
		_amt = balance;
		updateMoney();
	}
	public void creditMoney(int amt, String string)
	{
		
		int balance = _amt - amt;
		_amt = amt;
		addTransaction(false, string);
		
		_amt = balance;
		updateMoney();
	}
	public void retriveDetails()
	{
		var query = "select * from "+  DbFunction.TBs.tb_customerdetails;
		List list  =  new ArrayList();

		list.add(_loginDetails.get_userName());
		list.add(_loginDetails.get_password());
		ResultSet res = DbFunction.getEntry(get_Table() , list, "where userName=? and password=?");
		try {
			while(res.next())
	    	{	
	    	
				_amt= res.getInt(4);

	    	}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	public List<TransactionModel>   retriveTransactionDetails(String from, String to)
	{
		List<TransactionModel> list  =  new ArrayList();
		var query = "select * from "+  DbFunction.TBs.tb_transaction + "_"+_loginDetails.get_userName();
		var q = "where dateTime between "+from +" and "+to +" order by dateTime desc";
		
		ResultSet res = DbFunction.getEntry( DbFunction.TBs.tb_transaction + "_"+_loginDetails.get_userName() , new ArrayList<>(), q);
		try {
			while(res.next())
	    	{			
				TransactionModel cus = new TransactionModel();
	    		cus.set_dateTime( res.getString(1));
	    		cus.set_description(res.getString(2));
	    		cus.set_credit(res.getInt(3));
	    		cus.set_debit(res.getInt(4));
	    		list.add(cus);
	    	}
			return list;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}

	}
	public int  registerAccount()
	{
		String query =   "CREATE TABLE " +
					DbFunction.TBs.tb_transaction + "_"+_loginDetails.get_userName()+ " "+
				  "( "+				  
				  "dateTime DATETIME NOT NULL, " +
				  "description VARCHAR(500) NULL," +
				  "credit INT NULL, " +
				  "debit INT NULL, " + 
				  "PRIMARY KEY (dateTime))";
		
		DbFunction.createTableWholeQuery(query);
		List list  =  new ArrayList();

		list.add(_loginDetails.get_userName());
		list.add(_loginDetails.get_password());
		list.add(_name);
		list.add(0);
		return DbFunction.insertEntry(_tb, list);
	}

	public List<CustomerLoginModel> getAllpendingLoans()
	{
		// TODO customer detali not done 
		List<CustomerLoginModel> list  =  new ArrayList();
		try {
			var query = "select * from "+  DbFunction.TBs.tb_loans;
			ResultSet res = DbFunction.getEntry(get_Table() , new ArrayList(), "");
	    	while(res.next())
	    	{	
	    		CustomerLoginModel cus = new CustomerLoginModel("","");
	    		cus._loginDetails.set_userName( res.getString(1));
	    		cus._amt= res.getInt(2);
	    		cus._loanType= res.getString(3);
	    		list.add(cus);
	    	}
	    	return list;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println( e.getMessage());
			return list;
		}
	
	
	}
	
	

}
