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

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;


public abstract class ClassFactory<e, p> implements IFactory {

	public Object getClassObject() {
		return null;
	}
	
	public Object getClassObjectForName(String cName) {
		return null;
	}
	public Object getClassObjectForName(String cName, Object[] constructorArgs) {
		return null;
	}
	
	public boolean setDefaultClassName(String cName) {
		return false;
	}
	public boolean setDefaultClassName(String cName, Object[] constructorArgs) {
		return false;
	}
	public List<p> getMethodParameters(Method method, Map<e, p> types) {
		return null;
	}
	public List<p> getMethodParameters(Method rawMethod, Map<e, p> eventArgs, String[] inputArgs) {
		return null;
	}

}
