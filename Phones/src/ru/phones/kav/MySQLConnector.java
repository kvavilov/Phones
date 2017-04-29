package ru.phones.kav;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class MySQLConnector {

	private static final SQLException EUnknownParamterType = null;
	private Connection con;
	
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
	
	public boolean isConnected() throws SQLException{
		if(this.con == null)
			return false;
		return (!this.con.isClosed());
	}
	
	private void setStatmentParam(PreparedStatement stmt,Object param, int index) throws SQLException
	{
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
	
	private ResultSet sendPreparedQuery(String query,Object...params) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(query);
		int index = 0;
		for(Object param : params){
			setStatmentParam(stmt, param, ++index);
		}
		return stmt.executeQuery();
	}
	
	public ArrayList<Department> getDepartments()
	{
		if(this.con == null)
			return null;
		
		String squery = "select Id, Description, Region from Department where Id = ?";
		try{
			ResultSet results = sendPreparedQuery(squery, 1);
			ArrayList<Department> out = new ArrayList<>();
			while(results.next()){
				out.add(new Department(results.getInt(0),results.getString(1), null));
			}
		}catch (Exception e) {
			return null;
		}
		
		
		
		return null;
	}
}
