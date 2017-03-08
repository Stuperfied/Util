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

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import au.com.mshcraft.util.dictionaries.CommandDictionary;
import au.com.mshcraft.util.dictionaries.HashMapDictionary;
import au.com.mshcraft.util.factories.ClassBuilder;
import au.com.mshcraft.util.factories.ClassFactory;

public class CommandInterpreter implements CommandExecutor {
	
	private Plugin plugin;
	private String cName, mName, newLine;
	private boolean enableDebug;
	private Map<String, String> comData, subComClassMap, subComMethodMap;
	private Map<String, Map<String, String>> comMap;
	
	private ClassFactory<String, Object> classBuilder;
	private Object clsObj;
	private HashMapDictionary<String, Method> dictionary;
	private CommandHandler comHandler;
	
	private Object[] conArgs;

	public CommandInterpreter(Plugin instance, Map<String, Map<String, String>> commandMap, Object[] constructorArgs) {
		this.plugin = instance;
		this.comMap = commandMap;
		this.conArgs = constructorArgs;
		
		this.enableDebug = false;
		this.newLine = System.getProperty("line.separator");
		
		this.subComClassMap = new HashMap<>();
		this.subComMethodMap = new HashMap<>();
		
		for (String com : this.comMap.keySet()) {
			this.comData = comMap.get(com);
			this.cName = comData.get("class");
			this.mName = comData.get("method");
			this.subComClassMap.put(com, cName);
			this.subComMethodMap.put(com, mName);
			
		}
		
		this.classBuilder = new ClassBuilder();
		this.dictionary = new CommandDictionary();
		this.comHandler = new CommandHandler();

	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String subCom;
		if (args != null && args.length > 0) {
			subCom = args[0];
		} else {
			subCom = "default";
		}
		
		if (comMap.containsKey(subCom)) {
			this.cName = this.subComClassMap.get(subCom);
		} else {
			debug("args[] was null, No default method could be found", true);
			return false;
		}
		
		
		/*
		if (args != null && args.length > 0) {
			subCom = args[0];		
			debug("SubCommand: " + subCom, true);				
			this.cName = this.subComClassMap.get(subCom);
		} else if (this.comMap.containsKey("default")) {
			this.cName = this.subComClassMap.get("default");
		} else {
			debug("args[] was null, No default method could be found", true);
			return false;
		}
		*/
		
		debug("Constructing " + this.cName + 
				newLine + "With conArgs.length" + this.conArgs.length + 
				newLine + "With " + this.conArgs.toString() , true);
		
		if (this.conArgs != null) {
			this.classBuilder.setDefaultClassName(this.cName, this.conArgs);
			debug("conArgs not null here in interpreter", true);
		} else {
			this.classBuilder.setDefaultClassName(this.cName);
		}
		
		this.clsObj = this.classBuilder.getClassObject();
		debug("Populating Command Dictionary", true);
		this.dictionary.setData(clsObj, subComMethodMap);
		debug("Populating Command Handler", true);
		this.comHandler.setData(dictionary, classBuilder);
		
		
		debug("Passing command to Command Handler", true);
		if (this.comHandler.executeCommand(sender, cmd, label, args)) {
			return true;
		}
		
		return false;
	}
	
	
	
	private void debug(String info, boolean t) {
		String outPut = info;
		boolean test = t;
		if (this.enableDebug && test) {
			System.out.println(outPut);
		}
		return;
	}
}
