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

package au.com.mshcraft.util.io.fileio;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import au.com.mshcraft.util.io.fileio.resolvers.FileResolverDictionary;
import au.com.mshcraft.util.io.fileio.resolvers.IResolverList;
import au.com.mshcraft.util.io.fileio.resolvers.ResolverList;
import au.com.mshcraft.util.io.fileio.types.BasicFileTypes;
import au.com.mshcraft.util.io.fileio.types.IFileTypes;

public class FileIo extends JavaPlugin {
	
	String ext,
	regex;
	
	IFileTypes fTypes = new BasicFileTypes();
	IResolverList resList = new ResolverList();
	FileResolverDictionary dictionary = new FileResolverDictionary(fTypes, resList);
	
	public FileIo() {
		this.regex = ".";
	}
	
	/**
	 * Load data from String "path/filename.filetype".
	 * @param relativepath - The path and filename, relative to the plugin base.
	 * @return FileData - Returns an object of the files data type or null.
	 */
	public Object load(String relativepath) {
		FileHandler handler = buildResolver(relativepath);
		return null;
	}
	
	/**
	 * Load data from file Object .
	 * @param fileobject - Load data using a file specifier.
	 * @return FileData - Returns an object of the files data type or null.
	 */
	public Object load(File fileobject) {
		String relativepath = fileobject.getName();
		FileHandler handler = buildResolver(relativepath);
		return null;
	}
	
	/**
	 * Save fileData to file with the same name as the fileData object. Creates the file if it does not exist.
	 * @param fileData - Object containing the file data.
	 * @return true - Returns true on completion.
	 */
	// public boolean save(Object fileData) { // This may not be possible.
		// return false;
	// }
	
	/**
	 * Save fileData to file at relativepath "path/filename.filetype". Creates the file if it does not exist.
	 * @param relativepath - The path and filename, relative to the plugin base.
	 * @param fileData - Object containing the file data.
	 * @return true - Returns true on completion.
	 */
	public boolean save(String relativepath, Object fileData) {
		FileHandler handler = buildResolver(relativepath);
		return false;
	}
	
	/**
	 * Save fileData to file at relativepath "path/filename.filetype". Creates the file if it does not exist.
	 * @param fileobject - Load data using a file specifier.
	 * @param fileData - Object containing the file data.
	 * @return true - Returns true on completion.
	 */
	public boolean save(File fileobject, Object fileData) {
		String relativepath = fileobject.getName();
		FileHandler handler = buildResolver(relativepath);
		return false;
	}
	
	public FileHandler buildResolver(String relativepath) {
		String[] parts = relativepath.split(regex);
		this.ext = parts[parts.length];
		
		return new FileHandler(this.dictionary.getResolver(this.ext));
	}
	

}
