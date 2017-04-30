package ru.phones.kav;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestEmp {

	public static void main(String[] args) {
		//
		HashMap<String,String> settings = PhonesSettings.readSettings();
		PhoneSQLConnector connector = new PhoneSQLConnector(settings.get("SQLserverSpec"),settings.get("SQLserverUID"), settings.get("SQLserverPassword"), settings.get("SQLserverAddr"), settings.get("SQLserverDatabase"));
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
					Department df = depts.stream().filter(x -> x.getID() == 1).findFirst().orElse(null);
					if (df != null)
						System.out.println("Найдено подразделение: "+df.toString());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("Preved");
		//System.out.println(ContactTypes.valueOf("1"));
	}

}
