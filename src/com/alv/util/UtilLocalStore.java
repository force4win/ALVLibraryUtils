package com.alv.util;

import java.io.File;
import java.util.HashMap;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
public class UtilLocalStore {
	
	private static String defaultPath = UtilFiles.getCurrentPath()+"\\LOCAL_STORAGE";
	public static void setDefultSavePath(String defaultPath) {
		UtilLocalStore.defaultPath = defaultPath;
	}
	
	public static void save(String key, Object obj)
	{			
		UtilObject.saveObject(UtilLocalStore.defaultPath+"\\"+key+".json", obj);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T load(String key, Type type) {
		
		if(!(new File(defaultPath+"\\"+key+".json")).exists()) {
			return null;
		}
		
		try {			
			return (T) UtilObject.loadObject(defaultPath+"\\"+key+".json", type);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return null;	    
	}
}
