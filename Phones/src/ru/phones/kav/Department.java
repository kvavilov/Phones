package ru.phones.kav;

import java.util.HashMap;
import java.util.Map.Entry;

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
	
	public void setContacts(HashMap<ContactTypes, String> contact){
		this.contact = contact;
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
	
	@Override
	public String toString() {
		String contacts = "";
		if (this.contact != null){
			contacts = "{";
			for(Entry<ContactTypes,String> info: this.contact.entrySet()){
				if(!contacts.equals("{")){
					contacts = contacts + " # ";
				}
				contacts = contacts + info.getKey().getDescription() + " " + info.getValue();		
			}
			contacts = contacts + "}";
		}
		return getID() + " " + getDepartmentName() + contacts;
	}
}
