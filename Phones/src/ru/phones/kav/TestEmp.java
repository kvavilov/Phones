package ru.phones.kav;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
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
		
		ArrayList a = new ArrayList();
		a.add("Bla-bla-bla");
		a.add(5);
		for(Object e: a){
			if(e instanceof Integer){
				System.out.println("Число!");
			}
			else 
				System.out.println("Строка!");
			System.out.println(e.getClass());
		}
		
		try {
			Class cl = Class.forName("ru.phones.kav.MySQLConnector");
			Method[] m = cl.getDeclaredMethods();
			for(Method mt : m)
			{
				System.out.print(Modifier.toString(mt.getModifiers()) + " " +  mt.getName()+"(");
				Parameter[] p = mt.getParameters();
				for(Parameter pt: p)
				{
					System.out.print(pt.toString()+",");
				}
				System.out.println(");");
				
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Priveeet");
		System.out.println("Privet 2");
		
	}

}
