package ru.phones.kav;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PhonesSQLConnector {

	private static final SQLException EUnknownParamterType = null;
	private Connection con;
	
	public PhonesSQLConnector(String serverspec,String uid,String password,String srv,String database) {
		Connect(serverspec, uid, password, srv, database);
	}
	
	public boolean Connect(String serverspec,String uid,String password,String srv,String database)
	{
		try {
			this.con = DriverManager.getConnection("jdbc:"+serverspec+"://"+srv+"/"+database, uid, password);
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
	
	public ArrayList<Region> getRegions() throws SQLException{
		if(this.con == null)
			return null;
		
		String squery = "select "
				+ "reg.ID as regionId, \n"
				+ "reg.Description as regionDescription \n"
				+ "from Regions reg \n"
				+ "where \n"
				+ "  reg.IsNotUsed = ? \n"
				+ "order by reg.ID ";
		ResultSet results = sendPreparedQuery(squery, false);
		int colId   = results.findColumn("regionId");
		int colDesc = results.findColumn("regionDescription");
		
		ArrayList<Region> out = new ArrayList<>();
		while (results.next()){
			out.add(new Region(results.getInt(colId), results.getString(colDesc)));
		}
		return out;
	}
	
	public ArrayList<Department> getDepartmentsByRegion(Region region) throws SQLException
	{
		if(this.con == null)
			return null;
		
		if(region == null)
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
				+ " and depts.Region = ? "
				+ "order by depts.id,depts_contacts.info_type ";
		
		ResultSet results = sendPreparedQuery(squery,false,region.getID());
		ArrayList<Department> out = new ArrayList<>();
		int current_deptID = -1;
		Department dept = null;
		HashMap<ContactTypes, String> contact = null;
		ContactTypes[] contancttypes = ContactTypes.values();
		while(results.next()){
			if(results.getInt("dept_id") != current_deptID)
			{
				if(dept != null)
					{
					 dept.setContacts(contact);
					 out.add(dept);
					}
				current_deptID = results.getInt("dept_id");
				dept = new Department(results.getInt("dept_id"),results.getString("dept_description"), null);
				contact = new HashMap<>();
			}
			if(results.getInt("contact_type") >= 0 && results.getInt("contact_type") < contancttypes.length)
			{
				contact.put(contancttypes[results.getInt("contact_type")], results.getString("contact_value"));
			}
		}
		if(dept != null){
			dept.setContacts(contact);
			out.add(dept);
		}
		results.close();
		return out;
	}
	
	public ArrayList<Employee> getEmployers(Region region,Department department) throws SQLException{
		
		if(this.con == null)
			return null;
		
		String squery = ""
				+ "select \n"
				+ "  emps.ID as emp_id, \n"
				+ "  emps.FirstName as emp_firstname, \n"
				+ "  emps.MiddleName as emp_midllename, \n"
				+ "  emps.LastName as emp_lastname, \n"
				+ "  emps.Department_ID as emp_department, \n"
				+ "	 depts.Region as dept_region, \n"
				+ "  IFNULL(emps_contacts.info_type,-1) as contact_type, \n"
				+ "  IFNULL(emps_contacts.info_value,'') as contact_value \n"
				+ "from Employers as emps \n"
				+ " \n"
				+ "inner join Departments as depts on \n"
				+ "  depts.ID = emps.Department_ID \n"
				+ " and \n"
				+ "  depts.IsNotUsed = ? \n"
				+ " and (depts.id = ? or ? = true) \n"
				+ " and (depts.Region = ? or ? = true) \n"
				+ " \n"
				+ "left join Employers_contacts as emps_contacts on \n"
				+ " emps_contacts.Employer_ID = emps.Id \n"
				+ "where emps.IsNotUsed = ? \n"
				+ "order by emps.ID,IFNULL(emps_contacts.info_type,-1) ";

		ResultSet results = sendPreparedQuery(squery,false,department==null?-1:department.getID(),department==null?true:false,region==null?-1:region.getID(),region==null?true:false,false);
		ArrayList<Employee> out = new ArrayList<>();
		int current_empID = -1;
		Employee emp = null;
		HashMap<ContactTypes, String> contact = null;
		ContactTypes[] contancttypes = ContactTypes.values();
		while(results.next()){
			if(results.getInt("emp_id") != current_empID)
			{
				if(emp != null)
					{
					 emp.setContacts(contact);
					 out.add(emp);
					}
				current_empID = results.getInt("emp_id");
				emp = new Employee(results.getInt("emp_id"),results.getString("emp_firstname"),results.getString("emp_midllename"),results.getString("emp_lastname"),results.getInt("emp_department"), null);
				contact = new HashMap<>();
			}
			if(results.getInt("contact_type") >= 0 && results.getInt("contact_type") < contancttypes.length)
			{
				contact.put(contancttypes[results.getInt("contact_type")], results.getString("contact_value"));
			}
		}
		if(emp != null){
			emp.setContacts(contact);
			out.add(emp);
		}
		results.close();
		return out;
	}
}
