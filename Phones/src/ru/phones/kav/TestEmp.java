package ru.phones.kav;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class TestEmp {

	public static void main(String[] args) {
		//
		HashMap<String,String> settings = PhonesSettings.readSettings();
		PhonesSQLConnector connector = new PhonesSQLConnector(settings.get("SQLserverSpec"),settings.get("SQLserverUID"), settings.get("SQLserverPassword"), settings.get("SQLserverAddr"), settings.get("SQLserverDatabase"));
		try {
			if(connector.isConnected())
			{
				System.out.println("Подключились!");
				ArrayList<Region> regions = connector.getRegions();
				for(Region region : regions){
					System.out.println("Департаменты региона: "+region.getRegionDescription());
					ArrayList<Department> depts = connector.getDepartmentsByRegion(region);
					ArrayList<Employee> employers = connector.getEmployers(region, null);
					for(Department d: depts){
						System.out.println(d.toString());
						ArrayList<Employee> found = (ArrayList<Employee>) employers.stream().filter(e -> e.getdepartmentID() == d.getID()).collect(Collectors.toList());
						for(Employee founded : found)
							System.out.println("-------> "+founded.toString());
					}
					/*Department df = depts.stream().filter(x -> x.getID() == 1).findFirst().orElse(null);
					if (df != null)
						System.out.println("Найдено подразделение: "+df.toString());*/

					/*for(Employee emp: employers){
						System.out.println(emp.toString());
						Department df = depts.stream().filter(x -> x.getID() == emp.getdepartmentID()).findFirst().orElse(null);
						if (df != null)
							System.out.println("Найдено подразделение: "+df.toString());
					}*/
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("Preved");
		//System.out.println(ContactTypes.valueOf("1"));
	}

}
