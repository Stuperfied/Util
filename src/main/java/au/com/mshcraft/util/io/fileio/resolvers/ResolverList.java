package au.com.mshcraft.util.io.fileio.resolvers;

public class ResolverList extends AbstractResolverList {

	private IFileResolver[] resolvers = { 
			new TextResolver(),
			new YAMLResolver(),
			new BinaryResolver()
	}; 
	
	@Override
	public IFileResolver[] getResolvers() {
		return this.resolvers;
	}
	
}
