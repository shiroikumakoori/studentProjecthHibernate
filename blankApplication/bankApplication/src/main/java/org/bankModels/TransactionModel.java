package org.bankModels;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionModel  {

	String _userName ;
	Date _dateTime; 
	String _description;
	int _credit ;
	int _debit ;
	public String get_userName() {
		return _userName;
	}
	public void set_userName(String _userName) {
		this._userName = _userName;
	}
	public Date get_dateTime() {
		return _dateTime;
	}
	public void set_dateTime(String  _dateTime) {
		try {
			this._dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(_dateTime); ;
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	public void set_dateTime(Date _dateTime) {
		this._dateTime = _dateTime;
	}
	public String get_description() {
		return _description;
	}
	public void set_description(String _description) {
		this._description = _description;
	}
	public int get_credit() {
		return _credit;
	}
	public void set_credit(int _credit) {
		this._credit = _credit;
	}
	public int get_debit() {
		return _debit;
	}
	public void set_debit(int _debit) {
		this._debit = _debit;
	}
	
	public List<LoanModel> getAllpendingLoans()
	{
		// TODO customer detali not done 
		List<LoanModel> list  =  new ArrayList();
		try {
			
			ResultSet res = DbFunction.getEntry(DbFunction.TBs.tb_loans , new ArrayList(), "where status IS NULL");
	    	while(res.next())
	    	{	
	    		LoanModel m = new LoanModel();
	    		m._userName = res.getString(1);
	    		m._amt= res.getInt(2);
	    		m._loanType= res.getString(3);
	    		list.add(m);
	    	}
	    	return list;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println( e.getMessage());
			return list;
		}
	
	
	}


}
