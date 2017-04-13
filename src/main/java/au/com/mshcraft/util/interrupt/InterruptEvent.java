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

import java.lang.reflect.Method;
import java.util.EventObject;

public class InterruptEvent extends EventObject {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object receiver;
	private Method receiverMethod;
	private Object args;
	  
	  public InterruptEvent(Object source, Object receiver, Method receiverMethod) {
	    super(source);
	    this.receiver = receiver;
	    this.receiverMethod = receiverMethod;
	  }
	  
	  /** return the event source 
	 * @return source - The event source */
	  public Object getSource() {
	    return source;
	  }
	  
	  /** return the event receiver 
	 * @return receiver - The event receiver */
	  public Object getReciever() {
		return receiver;
	  }
	  
	  /** return the event receiver 
	 * @return receiverMethod - The method being execued */
	  public Object getRecieverMethod() {
		return receiverMethod;
	  }
	  
	  /** return the event arguments 
	 * @return args The arguments being passed to the method */
	  public Object getArgs() {
		return args;
	  }

}

