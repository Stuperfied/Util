/**
 * 
 */
package au.com.mshcraft.util.converters;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * @author Stuperfied
 *
 */
public class WorldEditLocationConverter implements IWorldEditLocationConverter {
	private Location loc;
	
	/**
	 * 
	 */
	public WorldEditLocationConverter() {}

	/* (non-Javadoc)
	 * @see au.com.mshcraft.util.IWorldEditLocationConverter#toBukkit(org.bukkit.World, com.sk89q.worldedit.util.Location)
	 */
	@Override
	public Location toBukkit(World world, com.sk89q.worldedit.util.Location worldEditLocation) {
	    int x = worldEditLocation.getBlockX();
	    int y = worldEditLocation.getBlockY();
	    int z = worldEditLocation.getBlockY();
	    loc = new Location(world, x, y, z);
	    return loc;
	}

}
