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

package au.com.mshcraft.util.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.enhance.agent.DefaultConstructor;

public class ClassBuilder extends ClassFactory<String, Object> {
	
	private boolean enableDebug;
	private String className;
	private Object[] cArgs;
		
	/**
	 * Used to pre-instantiate the class with no settings. Typically not recommended as no error control is provided.
	 *  
	 */
	public ClassBuilder() {
		
		// declared types 
		this.enableDebug = false;
	}
	
	/**
	 * Used to build a new class instance and methods for the specified name.
	 * @param cName - As package.className
	 */
	public ClassBuilder(String cName) {
		
		// inherited types
		this.className = cName;
		
		// declared types 
		this.enableDebug = false;
	}
	
	/**
	 * Used to build a new class instance and methods for the specified name.
	 * @param cName - As package.className
	 * @param constructorArgs - The arguments to pass to the constructor
	 */
	public ClassBuilder(String cName, Object[] constructorArgs) {
		
		// inherited types
		this.className = cName;
		this.cArgs = constructorArgs;
		
		// declared types 
		this.enableDebug = false;
	}
	
	
	// Getter's
	
	/**
	 * Gets a new instance of the class specified in the constructor
	 * @throws NullPointerException if class name not provided previously.
	 * @throws ClassNotFoundException if class name does not exist.
	 */
	@Override
	public Object getClassObject() {
		return builtObject();
	}
	
	/**
	 * Gets a new instance of the class specified.
	 * @param cName - The name of the class as package.className
	 * @throws ClassNotFoundException if class name does not exist.
	 */
	@Override
	public Object getClassObjectForName(String cName) {
		this.className = cName;
		return builtObject();
	}
	
	/**
	 * Gets a new instance of the class specified.
	 * @param cName - The name of the class as package.className
	 * @param constructorArgs - The arguments to pass to the constructor
	 * @throws ClassNotFoundException if class name does not exist.
	 */
	@Override
	public Object getClassObjectForName(String cName, Object[] constructorArgs) {
		this.className = cName;
		this.cArgs = constructorArgs;
		return builtObject();
	}
	
	/**
	 * Builds an object list of the arguments required by the method supplied
	 * @param rawMethod - The method to be invoked
	 * @param eventArgs - Special arguments not supplied by user input such as player or sender
	 * return par - The object list of arguments pre-populated for use with invoke
	 */
	@Override
	public List<Object> getMethodParameters(Method rawMethod, Map<String, Object> eventArgs) {
		String[] args = null;
		List<Object> params = buildParameters(rawMethod, eventArgs, args);
		return params;
	}
	
	/**
	 * Builds an object list of the arguments required by the method supplied
	 * @param rawMethod - The method to be invoked
	 * @param eventArgs - Special arguments not supplied by user input such as player or sender
	 * @param inputArgs - Arguments supplied by the user 
	 * return par - The object list of arguments pre-populated for use with invoke
	 */
	@Override
	public List<Object> getMethodParameters(Method rawMethod, Map<String, Object> eventArgs, String[] inputArgs) {
		String[] args = inputArgs;
		List<Object> params = buildParameters(rawMethod, eventArgs, args);
		return params;
	}
	
	
	// Setter's
	
	public void debug(boolean debugState) {
		this.enableDebug = debugState;
	}
	
	/**
	 * Sets the default class name scope
	 * @param cName - The name of the class to default to
	 * @return true - if assigned correctly
	 */
	@Override
	public boolean setDefaultClassName(String cName) {
		this.className = cName;
		return this.className.equalsIgnoreCase(cName);
	}
	
	/**
	 * Sets the default class name scope
	 * @param cName - The name of the class to default to
	 * @param constructorArgs - The arguments to pass to the constructor
	 * @return true - if assigned correctly
	 */
	@Override
	public boolean setDefaultClassName(String cName, Object[] constructorArgs) {
		this.className = cName;
		this.cArgs = constructorArgs;		
		return this.className.equalsIgnoreCase(cName);
	}
	
	
	// mutators
	private Object builtObject() {
		
		Class<?> cls = null;
		Object handler = null;
		try {
			cls = Class.forName(this.className);		
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Get the first constructor which contains matching parameters for cArg
		List<String> cArgTypes = new ArrayList<>();
		Constructor<?> clsCtor = null;
		
		if (this.cArgs != null) {
			for (Object cArg : this.cArgs) {
				cArgTypes.add(cArg.getClass().getName());
			}
			
			Constructor<?>[] ctors = cls.getDeclaredConstructors();
			for (Constructor<?> ctor: ctors) {
				
				Class<?>[] ctorTypes = ctor.getParameterTypes().length == cArgTypes.size() ? ctor.getParameterTypes() : null;
				if (ctorTypes == null) {
					debug("Incorrect arguement length for constructor", true);
					debug("ctor size: " + ctor.getParameterTypes().length, true);	
				} 

				debug("Trying for length", true);
			    if (ctorTypes != null && cArgTypes.size() == ctorTypes.length) {
			    	debug("Got the length correct", true);
			    	clsCtor = ctor;
			    	break;
			    }
		    }
		}
		try {
			// This is for Debugging
			// if (debug && clsCtor == null) { throw new NullPointerException("Incorrect argument type or length in ClassBuilder constructor");}
			// if (debug && cArgs == null) { throw new NullPointerException("The constructor arguments object was null");}
			
			handler = clsCtor != null ? clsCtor.newInstance(this.cArgs) : cls.getClass().newInstance();
			debug("Class instantiated", true);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return handler;
	}
	
	
	/**
	 * Builds an object list of the arguments required by the method supplied
	 * @param rawMethod - The method to be invoked
	 * @param eventArgs - Special arguments not supplied by user input such as player or sender
	 * @param inputArgs - Arguments supplied by the user
	 * return par - The object list of arguments pre-populated for use with invoke 
	 */
	private List<Object> buildParameters(Method rawMethod, Map<String, Object> eventArgs, String[] inputArgs) {
		Method method = rawMethod;
		Map<String, Object> types = eventArgs;
		
	    Class<?>[] parameters = method.getParameterTypes();
	    List<Object> params = new ArrayList<>();
		
	    int i = 0;
	    for (Class<?> parameter : parameters) {
    		if (types.containsKey(parameter.getName())) {
	    		params.add(i, types.get(parameter.getName()));
	    		debug(i + " Passing: " + types.get(parameter.getName().toString()) + " as: " + parameter.getName(), true);
    			i++;
	    	}
	    }
	    
	    return params;	      
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
