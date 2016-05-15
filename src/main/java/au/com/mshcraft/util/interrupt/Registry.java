package au.com.mshcraft.util.interrupt;

import java.util.HashMap;


public class Registry {
	private HashMap<String, Object> objectList;
	// List<HashMap<String, Object>> objectList;
	
	public Registry() {
		objectList = new HashMap<String, Object>();
		// objectList = new ArrayList<HashMap<String, Object>>();
	}
	
	private boolean set(String key, Object value) {
		objectList.put(key, value);
		return true;
	}
	
	private Object get(String key) {
		if (objectList.containsKey(key)) {
			return objectList.get(key);
		}
		return null;
	}
		
}
