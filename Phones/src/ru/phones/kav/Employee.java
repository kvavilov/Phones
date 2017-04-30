package ru.phones.kav;

import java.util.HashMap;
import java.util.Map.Entry;

public class Employee {
	
	private HashMap<ContactTypes,String> contact;
	private String 		  employeeFirstName;
	private String 		  employeeMiddleName;
	private String 		  employeeLastName;
	private int 		  employeeId;
	private int 		  departmentId;
	
	public Employee(int employeeId,String employeeFirstName,String employeeMiddleName,String employeeLastName,int departmentId,HashMap<ContactTypes,String> contact) {
		this.employeeId			= employeeId;
		this.contact     	    = contact;
		this.employeeFirstName  = employeeFirstName;
		this.employeeMiddleName = employeeMiddleName;
		this.employeeLastName   = employeeLastName;
		this.departmentId       = departmentId;
	}
	
	public String getEmployeeFirstName(){
		return this.employeeFirstName;
	}

	public String getEmployeeMiddleName(){
		return this.employeeMiddleName;
	}

	public String getEmployeeLastName(){
		return this.employeeLastName;
	}
	
	public HashMap<ContactTypes,String> getContactsInfo(){
		return this.contact;
	}
	
	public void setContacts(HashMap<ContactTypes, String> contact){
		this.contact = contact;
	}
	
	public int getID(){
		return this.employeeId;
	}
	
	public int getdepartmentID(){
		return this.departmentId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String contacts = "";
		if(this.contact != null){ 
			contacts = "{";
			for(Entry<ContactTypes,String> info: this.contact.entrySet()){
				if(!contacts.equals("{")){
					contacts = contacts + " # ";
				}
				contacts = contacts + info.getValue();		
			}
			contacts = contacts + "}";
		}
		return "{"+this.employeeFirstName + "," + this.employeeMiddleName+","+this.employeeLastName + ", deptID=" + this.departmentId + " : " + contacts + "}";   
	}

}
