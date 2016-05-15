package au.com.mshcraft.util.io.fileio.types;

public class BasicFileTypes extends AbstractFileTypes {
	
	private String[] fileTypes = {"txt", "yml", "dat"};
	
	@Override
	public String[] getFileTypes() {
		return this.fileTypes;
	}
}
