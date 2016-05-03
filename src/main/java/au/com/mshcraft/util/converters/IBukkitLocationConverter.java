/**
 * 
 */
package au.com.mshcraft.util.converters;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * @author Sari
 *
 */
public interface IBukkitLocationConverter extends TypeConverter {
	
    /**
     * Converts from an org.bukkit.Location to an org.sk89q.worldedit.util.Location
     * @param world org.bukkit.World
     * @param bukkitLocation org.bukkit.Location
     * @return loc
     */
    public com.sk89q.worldedit.util.Location toWorldEdit(World world, Location bukkitLocation);
    
}
