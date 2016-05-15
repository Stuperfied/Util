package au.com.mshcraft.util.io.fileio.resolvers;

import java.util.HashMap;
import java.util.List;

import org.bukkit.World;


public class ResolverMethod implements FileResolver {
	
	public FileResolver getFactory() {
		FileResolver[] resolver = { 
				new RiftCreator(world),
				new FitOut2(areas) 
		}; 
		FileResolver resolution;
	    resolution = resolver[choice];
	    return resolution;
	    
	    
	    
	public FileResolver qualify(String relativepath) {
		return null;
		
	}
	
	public FileResolver qualify(Object fileobject) {
		if (fileobject.getClass().getName().equalsIgnoreCase(anotherString)) 
		return null;
	}
}
