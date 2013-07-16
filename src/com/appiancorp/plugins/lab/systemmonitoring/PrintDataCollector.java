package com.appiancorp.plugins.lab.systemmonitoring;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PrintDataCollector {
	
	public static void print(List<HashMap<String, Object>> data){
		for (HashMap<String, Object> hashMap : data) {
			Iterator<String> it = hashMap.keySet().iterator();
			while (it.hasNext()){	
				String key = it.next();
				System.out.println(key+ " - " + hashMap.get(key));
			}
		}
	}

}
