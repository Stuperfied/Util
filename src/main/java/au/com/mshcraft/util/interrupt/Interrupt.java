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

package au.com.mshcraft.util.interrupt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;




/**
 * 
 * @author Stuperfied
 *
 */

public class Interrupt extends AbstractInterrupt {
	/**
	 * 
	 */
	DispatchEvent dispatchEvent;
	InterruptEvent interruptEvent;
	
	private Method callingMethod;
	private Method receivingMethod;
	private Object receiverObject;
	
	private Registry registry;
	private InterruptEventRegister register;
	
	/**
	 * Constructor
	 * @param registry - The registry
	 * @param register - The register event
	 */
	public Interrupt(InterruptEventRegister register, Registry registry) {
		this.register = register;
		this.registry = registry;
	}
    
	public void request(Method callerMethod, String receiverName, String receiverMethod) {
		this.callingMethod = callerMethod;
		Class<?> receiverClass = null;

		try {
			receiverClass = Class.forName(receiverName);
			receiverObject = receiverClass.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
			
		/* returns the array of Method objects representing the public 
        methods of this class */
        Method m[] = receiverObject.getClass().getDeclaredMethods();
        for(int i = 0; i < m.length; i++) {
        	System.out.println(m[i]);
           if (m[i].getName().equalsIgnoreCase(receiverMethod) ) {
        	   System.out.println("Got the receiver Method, its " + m[i]);
        	   this.receivingMethod = m[i];
           }
        }
				
		interruptEvent = new InterruptEvent(callingMethod, receiverObject, receivingMethod);
		fireInterruptEvent();	
	}
	
	
	public Object process(Object... args) {
		Object result = null;
		try {
			result = receivingMethod.invoke(receiverObject.getClass().newInstance(), args);
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try {
			dispatchEvent = new DispatchEvent(callingMethod, receiverObject.getClass().newInstance(), receivingMethod, args);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fireDispatchEvent();
		
	    return result;

	}
	
	
	private void fireInterruptEvent() {
        // Notify everybody that may be interested.
        for (InterruptEventHandler h : register.getList())
            h.interruptEvent(interruptEvent);
	}
	
	private void fireDispatchEvent() {
        // Notify everybody that may be interested.
        for (InterruptEventHandler h : register.getList())
            h.dispatchEvent(dispatchEvent);
	}

		
}
