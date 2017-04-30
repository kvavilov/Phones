package ru.phones.kav;

import java.util.HashMap;

public final class PhonesSettings {
	private static HashMap<String,String> settings;
	
	public static HashMap<String,String> readSettings(){
		// Здесь будем читать настройки 
		// Пока достаточно задать их статически
		HashMap<String,String> readedsettings= new HashMap<>();
		readedsettings.put("SQLserverSpec"    , "mysql");
		readedsettings.put("SQLserverAddr"    , "localhost");
		readedsettings.put("SQLserverUID"     , "root");
		readedsettings.put("SQLserverPassword", "240981");
		readedsettings.put("SQLserverDatabase", "Phones");
		settings = readedsettings;
		return getSettings();
	}
	
	public static HashMap<String,String> getSettings(){
		return settings;
	}
}
