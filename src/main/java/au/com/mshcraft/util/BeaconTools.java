// ref: https://github.com/SocialCraft/PrettyScaryLib/blob/master/src/com/stirante/PrettyScaryLib/BeaconHelper.java#L59
// This code is disabled as it is outdated and requires craftbukkit.
// It has been kept as a reference

package au.com.mshcraft.util;
/*
import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;

import net.minecraft.server.v1_9_R2.BlockPosition;
import net.minecraft.server.v1_9_R2.TileEntityBeacon;
*/
public class BeaconTools {
	
	/**
	 * Checks if is applicable.
	 * 
	 * @param block
	 *            the block
	 * @return true, if is applicable
	 */
	
	
	/*
	public boolean isApplicable(Block block) {
		switch (block.getType()) {
			case BEACON:
				return true;
			default:
				return false;
		}
	}
	*/
	
	
	/**
	 * Checks if beacon is active.
	 * 
	 * @param block
	 *            beacon
	 * @return true if active
	 */
	
	
	/*
	public boolean isActive(Block block) {
		if (!isApplicable(block)) {
			System.out.println("Invalid Block");
			return false;
		}
		Location loc = block.getLocation();
		BlockPosition pos = new BlockPosition(loc.getBlock().getX(), loc.getBlock().getY(), loc.getBlock().getZ());

		TileEntityBeacon beacon = (TileEntityBeacon) ((CraftWorld) block
				.getWorld()).getHandle().getTileEntity(pos);
		Field active = null;
		Field[] fields = null;
		try {
			
			fields = TileEntityBeacon.class.getDeclaredFields();
			int i = 0;
			
			
			for (Field field : fields) {
				System.out.println("field " + i + "Name: " + field.getName() + "Type: " + field.getType());
				i++;
			}
			active = TileEntityBeacon.class.getDeclaredField("k");
			active.setAccessible(true);
			int value = (Integer) active.get(beacon);
			System.out.println("field: " + active.getName() + " = " + value);
			return true; //(Boolean) active.get(beacon);
		}
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		catch (SecurityException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	*/
}


