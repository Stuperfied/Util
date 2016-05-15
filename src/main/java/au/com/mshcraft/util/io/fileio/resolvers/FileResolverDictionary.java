package au.com.mshcraft.util.io.fileio.resolvers;

import java.io.BufferedReader;
import java.io.FileReader;

import au.com.mshcraft.util.io.fileio.types.IFileTypes;

public class FileResolverDictionary {
	
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
