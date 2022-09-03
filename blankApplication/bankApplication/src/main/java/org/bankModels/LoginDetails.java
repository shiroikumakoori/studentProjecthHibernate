package org.bankModels;

import java.sql.ResultSet;
import java.util.List;

public class LoginDetails {
	private String _userName;
	private  String _password;
	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	public String get_userName() {
		return _userName;
	}

	public void set_userName(String _userName) {
		this._userName = _userName;
	}

	
	LoginDetails(String userName, String password)
	{
		this._userName = userName;
		this._password = password ;
	}
	
	boolean iscreditalCorrect(PersonlModel m, List l)
	{
		
		try {
			ResultSet res =DbFunction.getEntry( m._tb, l,  "where userName=? and password=?");
			if(res.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false; 
		}
		
	
	}

}