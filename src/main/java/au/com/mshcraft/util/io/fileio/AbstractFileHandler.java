package au.com.mshcraft.util.io.fileio;

public abstract class AbstractFileHandler {
	
	/**
	 * Load data from String "path/filename.filetype".
	 * @param relativepath - The path and filename, relative to the plugin base.
	 * @return FileData - Returns an object of the files data type or null.
	 */
	public Object load(String relativepath) {
		return null;
	}
	
	/**
	 * Load data from file Object .
	 * @param fileobject - Load data using a file specifier.
	 * @return FileData - Returns an object of the files data type or null.
	 */
	public Object load(Object fileobject) {
		return null;
	}
	
	/**
	 * Save fileData to file with the same name as the fileData object. Creates the file if it does not exist.
	 * @param fileData - Object containing the file data.
	 * @return true - Returns true on completion.
	 */
	public boolean save(Object fileData) {
		return false;
	}
	
	/**
	 * Save fileData to file at relativepath "path/filename.filetype". Creates the file if it does not exist.
	 * @param relativepath - The path and filename, relative to the plugin base.
	 * @param fileData - Object containing the file data.
	 * @return true - Returns true on completion.
	 */
	public boolean save(String relativepath, Object fileData) {
		return false;
	}
	
	/**
	 * Save fileData to file at relativepath "path/filename.filetype". Creates the file if it does not exist.
	 * @param fileobject - Load data using a file specifier.
	 * @param fileData - Object containing the file data.
	 * @return true - Returns true on completion.
	 */
	public boolean save(Object fileobject, Object fileData) {
		return false;
	}
}
