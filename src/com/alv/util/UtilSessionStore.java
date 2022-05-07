package com.alv.util;

import java.util.HashMap;

public class UtilSessionStore {
private static HashMap<String, Object> session;
	
	public static void saveObject(String key, Object obj)
	{
		if( session == null){
			session = new HashMap<String, Object>();
		}
		
		session.put(key, obj);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getObject(String key) {
		try {			
			return ((T) session.get(key));			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return null;	    
	}
}
