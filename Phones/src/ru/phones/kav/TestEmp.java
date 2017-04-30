package ru.phones.kav;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestEmp {

	public static void main(String[] args) {
		//
		MySQLConnector connector = new MySQLConnector("root", "240981", "127.0.0.1", "Phones");
		try {
			if(connector.isConnected())
			{
				System.out.println("Подключились!");
				ArrayList<Region> regions = connector.getRegions();
				for(Region region : regions){
					System.out.println("Департаменты региона: "+region.getRegionDescription());
					ArrayList<Department> depts = connector.getDepartmentsByRegion(region);
					for(Department d: depts){
						System.out.println(d.toString());
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("Preved");
		//System.out.println(ContactTypes.valueOf("1"));
	}

}
