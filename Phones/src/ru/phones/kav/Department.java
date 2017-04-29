package ru.phones.kav;

import java.util.HashMap;

public class Department {
	private HashMap<ContactTypes,String> contact;
	private String departmentName;
	private int departmentID;
	//private Employee Chief;
	
	public Department(int departmentID,String departmentName,HashMap<ContactTypes,String> contact) {
		this.departmentID   = departmentID;
		this.contact        = contact;
		this.departmentName = departmentName;
	}
	
	public HashMap<ContactTypes,String> getContactsInfo(){
		return this.contact;
	}
	
	public String getDepartmentName(){
		return this.departmentName;
	}
	
	public int getID(){
		return this.departmentID;
	}
}
