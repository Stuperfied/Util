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

package au.com.mshcraft.util.interpreters.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import au.com.mshcraft.util.dictionaries.HashMapDictionary;
import au.com.mshcraft.util.factories.ClassFactory;

public class CommandHandler {
	
	private boolean enableDebug;
	private HashMapDictionary<String, Method> dictionary;
	private ClassFactory<String, Object> classFactory;
	private Object methodLibrary;
	private Map<String, Object> types;
	
	// dictionary types
	HashMap<String, Method> actions;
	
	public CommandHandler() {
		
		// declared types 
		this.types = new HashMap<String, Object>();
		this.enableDebug = false;
	}
	
	public CommandHandler(HashMapDictionary<String, Method> commandDictionary, ClassFactory<String, Object> classBuilder) {
		
		// declared types 
		this.types = new HashMap<String, Object>();
		
		this.dictionary = commandDictionary;
		this.classFactory = classBuilder;
		
		// initialized types
		this.actions = this.dictionary.getMap();
		debug("got the Map", true);
		this.methodLibrary = this.classFactory.getClassObject();
		debug("Got the Library", true);
	}
	
	public void setData(HashMapDictionary<String, Method> commandDictionary, ClassFactory<String, Object> classBuilder) {
		
		this.dictionary = commandDictionary;
		this.classFactory = classBuilder;
		
		// initialized types
		this.actions = this.dictionary.getMap();
		debug("got the Map", true);
		this.methodLibrary = this.classFactory.getClassObject();
		debug("Got the Library", true);
	}
	
	public boolean executeCommand(CommandSender sender, Command cmd, String label, String[] args) {
		debug("Begining CommandHandler", true);
		if (args.length > 0) {
			debug("Got the args", true);
			Method method;
			List<Object> params = new ArrayList<>();
			Object[] par;
			String arg = args[0].toLowerCase();
			types.put(CommandSender.class.getName(), sender);
			types.put(String[].class.getName(), args);	
			
			if (sender instanceof Player) { 
				debug("Got the player", true);
				Player player = (Player) sender;
				types.put(Player.class.getName(), player);
			}
			
			// I did this instead of contains, because			
			// we can't guarantee correct capitalization.
			debug("Commands: " + this.actions.keySet().toString(), true);
			for (String key : this.actions.keySet()) {
				debug("Checking action: " + key + " against: " + arg, true);
				if (key.equalsIgnoreCase(arg)) {
					method = actions.get(arg);
					params = classFactory.getMethodParameters(method, types);
					par = params.toArray();
					
					debug("invoking " + method.getClass().getName() + ".invoke(" + methodLibrary.getClass().getName() + "," + par.toString(), true);
					
					try {
						method.invoke(methodLibrary, par);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				}
			}
			
		}
		return false;
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

