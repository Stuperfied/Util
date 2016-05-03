/**
 * 
 */
package au.com.mshcraft.util.converters;

import org.bukkit.World;

import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.util.Location;

/**
 * @author Sari
 *
 */
public class BukkitLocationConverter implements IBukkitLocationConverter {
	private static Location loc;
	
	/**
	 * 
	 */
	public BukkitLocationConverter() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see au.com.mshcraft.util.IBukkitLocationConverter#toWorldEdit(org.bukkit.World, org.bukkit.Location)
	 */
	@Override
	public Location toWorldEdit(World world, org.bukkit.Location bukkitLocation) {
   	    int x = bukkitLocation.getBlockX();
   	    int y = bukkitLocation.getBlockY();
   	    int z = bukkitLocation.getBlockY();
   	    loc = new Location((Extent) world, x, y, z);
   	    return loc;
	}

}
