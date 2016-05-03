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
public interface IWorldEditLocationConverter extends TypeConverter {
	
	/**
	 * Converts from an org.sk89q.worldedit.util.Location to an org.bukkit.Location
     * @param world org.bukkit.World
     * @param worldEditLocation com.sk89q.worldedit.util.Location
     * @return loc
     */
	public Location toBukkit(World world, com.sk89q.worldedit.util.Location worldEditLocation);
	
}
