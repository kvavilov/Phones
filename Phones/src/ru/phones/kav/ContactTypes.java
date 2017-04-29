package ru.phones.kav;

public enum ContactTypes {
	EMAIL("Почта"),
	PHONE("Внутренний телефон"),
	EXTPHONE("Городской телефон");
	
	private String description; 
	
	ContactTypes(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
}
