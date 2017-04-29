package ru.phones.kav;

import java.util.HashMap;

public class ContactInfo {
	private ContactTypes type;
	private String value;
	
	public ContactInfo(ContactTypes type,String value) {
		this.type  = type;
		this.value = value; 
	}
	
	public HashMap<ContactTypes, String> getContact()
	{
		HashMap<ContactTypes,String> contact = new HashMap<>();
		contact.put(this.type, this.value);
		return contact;
	}
	
	/*public void setContact(ContactTypes type,String value){
		this.type  = type;
		this.value = value;
	}*/
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{"+this.type.toString() + "," + this.value + "}";
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.type  = null;
		this.value = "";
	}
}
