package ru.phones.kav;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class MySQLConnector {

	private static final SQLException EUnknownParamterType = null;
	private Connection con;
	
	public MySQLConnector() {
		// TODO Auto-generated constructor stub
		this.con = null;
	}
	
	public MySQLConnector(String uid,String password,String srv,String database) {
		Connect(uid, password, srv, database);
	}
	
	public boolean Connect(String uid,String password,String srv,String database)
	{
		try {
			this.con = DriverManager.getConnection("jdbc:mysql://"+srv+"/"+database, uid, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}	
	
	private void sendPreparedQuery(String query,Object...params) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(query);
		int index = 0;
		for(Object param : params){
			index++;
			if(param instanceof Integer)
				stmt.setInt(index, (Integer)param);
			else if(param instanceof String)
				stmt.setString(index, (String)param);
			else if(param instanceof Long)
				stmt.setLong(index, (Long)param);
			else if(param instanceof Float)
				stmt.setFloat(index, (Float)param);
			else if(param instanceof Date)
				stmt.setDate(index, (java.sql.Date)param);
			else if(param instanceof BigDecimal)
				stmt.setBigDecimal(index, (BigDecimal)param);
			else 
				throw EUnknownParamterType;
		}
		//stmt.setString(arg0, arg1);
		//for()
		//Statement stmt = this.con.createStatement();
		//stmt.
		//this.con.
	}
}
