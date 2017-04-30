package ru.phones.kav;

public class Region {
	private int regionId;
	private String regionDescription;

	public Region(int regionId,String regionDescription) {
		this.regionId = regionId;
		this.regionDescription = regionDescription;
	}
	
	public int getRegionId(){
		return this.regionId;
	}
	
	public String getRegionDescription(){
		return this.regionDescription;
	}
	
}
