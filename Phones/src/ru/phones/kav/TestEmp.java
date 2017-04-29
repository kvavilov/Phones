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
		ArrayList<Employee> employers = new ArrayList<>();
		for(int i = 0; i < 2; i++)
		{
			Employee emp;
			if (i==0)
			{
				HashMap<ContactTypes,String> contact = new HashMap<>();
				contact.put(ContactTypes.EMAIL,"a@a.ru");
				contact.put(ContactTypes.PHONE,"12345");
				contact.put(ContactTypes.EXTPHONE,"+7812-12345");
				emp = new Employee(0,"Василий","Петрович","Пупкин", contact);
			}
			else 
			{
				HashMap<ContactTypes,String> contact = new HashMap<>();
				contact.put(ContactTypes.EMAIL,"b@b.ru");
				contact.put(ContactTypes.PHONE,"3456");
				contact.put(ContactTypes.EXTPHONE,"+7812-34567");
				emp = new Employee(0,"Семен","Иванович","Рогов", contact);
			}
			employers.add(employers.size(),emp);
		}
		
		for(Employee curEmp : employers){
			System.out.println(curEmp.toString());
		}
		
		MySQLConnector connector = new MySQLConnector("root", "240981", "127.0.0.1", "Phones");
		try {
			if(connector.isConnected())
				System.out.println("Подключились!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
