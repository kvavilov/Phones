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
import java.util.HashMap;

import com.mysql.fabric.HashShardMapping;

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
		else if(param instanceof Boolean)
			stmt.setBoolean(index, (Boolean)param);
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
	
	public ArrayList<Department> getDepartments() throws SQLException
	{
		if(this.con == null)
			return null;
		
		String squery = "select \n"
				+ "depts.Id as dept_id, \n"
				+ "depts.Description as dept_description, \n"
				+ "depts.Region as dept_region, \n"
				+ "IFNULL(depts_contacts.info_type,-1) as contact_type, \n"
				+ "IFNULL(depts_contacts.info_value,'') as contact_value \n"
				+ "from Departments as depts \n"
				+ "left join Departments_contacts as depts_contacts on \n"
				+ " depts_contacts.department_id = depts.Id \n"
				+ "where depts.IsNotUsed = ? \n"
				+ "order by depts.id,depts_contacts.info_type ";
		//System.out.println(squery);

		ResultSet results = sendPreparedQuery(squery,false);
		ArrayList<Department> out = new ArrayList<>();
		int current_deptID = -1;
		Department dept = null;
		HashMap<ContactTypes, String> contact = null;
		ContactTypes[] contancttypes = ContactTypes.values();
		while(results.next()){
			if(results.getInt(1) != current_deptID)
			{
				if(dept != null)
					{
					 dept.setContacts(contact);
					 out.add(dept);
					}
				current_deptID = results.getInt(1);
				dept = new Department(results.getInt(1),results.getString(2), null);
				contact = new HashMap<>();
			}
			if(results.getInt(4) >= 0 && results.getInt(4) < contancttypes.length)
			{
				contact.put(contancttypes[results.getInt(4)], results.getString(5));
			}
		}
		results.close();
		return out;
	}
}
