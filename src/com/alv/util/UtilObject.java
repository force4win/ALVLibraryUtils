package com.alv.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;

public class UtilObject {

	public static String serialize(Object o) {
		Gson gson = new Gson();
		String json = gson.toJson(o);
		return json;
	}

	public static boolean saveObject(String path, Object o) {

		try {

			Gson gson = new Gson();
			String json = gson.toJson(o);
			UtilFiles.Save(json, path, true);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static <T> T loadObject(String path, Type type) {
		try {
			String json = UtilFiles.load(path);
			Gson gson = new Gson();
			return (T) gson.fromJson(json, type);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}
