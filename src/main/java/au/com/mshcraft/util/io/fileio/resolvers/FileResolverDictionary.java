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

package au.com.mshcraft.util.io.fileio.resolvers;

import java.io.BufferedReader;
import java.io.FileReader;

import au.com.mshcraft.util.io.fileio.types.IFileTypes;

public class FileResolverDictionary implements IFileResolver {
	
	private String path,
	ext;
	
	private FileReader fr;
	private BufferedReader textReader;

	private String[] fileTypes;
	IFileResolver resolver;
	
	private IFileResolver[] resolvers;
	
	public FileResolverDictionary(IFileTypes fileTypes, IResolverList resolverList) {
		this.fileTypes = fileTypes.getFileTypes();
		this.resolvers = resolverList.getResolvers();
		
	}
	
	public IFileResolver getResolver(String ext) {
		
		this.ext = ext;
		for (int i=0; i<this.fileTypes.length; i++) {
			this.resolver = this.fileTypes[i].equalsIgnoreCase(ext) ? this.resolvers[i] : null;
			if (this.resolver != null) break;
		}
		return this.resolver;
	}
	
}
