/**
 * Util, a collection of useful utilities.
 * Copyright (C) Stuperfied <http://www.mshcraft.com.au>
 * Copyright (C) Util team and contributors
 * 
 * This file is part of au.com.mshcraft.Util
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with au.com.mshcraft.Util.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * This project has been created as a community store, so that code fragments are all in one place. This type of code storage,
 * allows projects greater flexibility. Please feel free to ask questions or make comments about the code. Also feel free 
 * to create pull requests for your own code, so that others might benefit. 
 */

package au.com.mshcraft.util.dictionaries;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandDictionary extends HashMapDictionary<String, Method> {

	// <init> types
	private boolean enableDebug;
	private Object clsObj;
	private Map<String, String> commandMap;
	private List<String> commands;
	private List<String> methods;
	
	// dictionary types
	HashMap<String, Method> actions;
	
	public CommandDictionary() {
		this.enableDebug = false;
		
		// dictionary types
		this.commands = new ArrayList<>();
		this.methods = new ArrayList<>();
		this.actions = new HashMap<String, Method>();
		
	}
	
	public CommandDictionary(Object classObject, Map<String, String> comMap) {
		
		// <init> types
		this.enableDebug = false;
		this.clsObj = classObject;
		this.commandMap = comMap;
		
		// dictionary types
		this.commands = new ArrayList<>();
		this.methods = new ArrayList<>();
		this.actions = new HashMap<String, Method>();
		
	}
	
	@Override
	public void setData(Object classObject, Map<String, String> comMap) {
		this.clsObj = classObject;
		this.commandMap = comMap;
	}
	
	@Override
	public HashMap<String, Method> getMap() {
		debug("Building Dictionary", true);
		 
		 
		
		for (String key : commandMap.keySet()) {
			debug("Adding Command: " + key, true);
			commands.add(key);
			debug("Adding Method: " + commandMap.get(key), true);
			methods.add(commandMap.get(key));
		}
		debug("Constructing dictionary for " + clsObj.getClass().getName(), true);
		dictionary(clsObj, commands, methods);
		return this.actions;
    }
	


	private void dictionary(Object clsObj, List<String> commands, List<String> methods) {
		for (int i=0; i<commands.size(); i++) {
			Method m[] = clsObj.getClass().getDeclaredMethods();		
			try {
				for (int c = 0; c < m.length; c++) {
					debug("Comparing Method: " + m[c].getName() + " = " + methods.get(i), true);
					if (m[c].getName().equalsIgnoreCase(methods.get(i))) { 						
						this.actions.put(commands.get(i), m[c]);
					}
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	private void debug(String info, boolean t) {
		String outPut = info;
		boolean test = t;
		if (enableDebug && test) {
			System.out.println(outPut);
		}
		return;
	}
}
