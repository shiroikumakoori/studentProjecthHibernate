package org.bankModels;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.bankModels.DbFunction.TBs;

public class LoanModel {
	DbFunction.TBs _tb ; 
	String _userName;
	int	   _amt;
	String _loanType;
	String _status ="''";
	public DbFunction.TBs get_tb() {
		return _tb;
	}
	public void set_tb(DbFunction.TBs _tb) {
		this._tb = _tb;
	}
	public String get_status() {
		return _status;
	}
	public void set_status(String _status) {
		this._status = _status;
	}
	public String get_comments() {
		return _comments;
	}
	public void set_comments(String _comments) {
		this._comments = _comments;
	}

	String _comments ="''";
	public String get_userName() {
		return _userName;
	}
	public void set_userName(String _userName) {
		this._userName = _userName;
	}
	public int get_amt() {
		return _amt;
	}
	public void set_amt(int _amt) {
		this._amt = _amt;
	}
	public String get_loanType() {
		return _loanType;
	}
	public void set_loanType(String _loanType) {
		this._loanType = _loanType;
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
	public void updateLoan()
	{
		List l = new ArrayList<>();
		
		l.add(_status);
		l.add(_comments);
		l.add(_userName);
		l.add(_loanType);
	
		
		String q = "status=?, comments=? where userName=? and loanType=?";
		DbFunction.updateEntry( DbFunction.TBs.tb_loans ,  l,  q );
	}
	
	
	public int insertLoanIntoTable()
	{
		List l = new ArrayList<>();
		

		l.add(_userName);
		l.add(_amt);
		l.add(_loanType);
		l.add(_status);
		l.add(_comments);
	
		
		return DbFunction.insertEntry(DbFunction.TBs.tb_loans,l);
		
	}
	
	int insertEntry(List l) {
		return DbFunction.insertEntry(_tb, l);

	}
	
	
}
