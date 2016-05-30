package au.com.mshcraft.modifyworld;

import org.bukkit.Bukkit;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;


public class RegionCopier {

	/**
	 * 
	 * @param region - The CuboidRegion to be copied
	 * @param origin - The place the player would be standing when executing the //copy command
	 * @return holder - Returns a ClipboardHolder which contains the clipboard. Session not required.
	 * @throws WorldEditException
	 */
	public ClipboardHolder copyRegion(CuboidRegion region, Vector origin) throws WorldEditException {

		
		WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		worldEdit.getWorldEdit();
        
		World world = region.getWorld(); 
		EditSession es = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1);
	    
        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
        clipboard.setOrigin(origin);
    
        
        Extent sourceExtent = region.getWorld();
        Extent destinationExtent = clipboard;
        Vector from = origin;
        Vector to = region.getMinimumPoint();
        
	    ForwardExtentCopy copy = new ForwardExtentCopy(sourceExtent, region, from, destinationExtent, to);
	    Operations.completeLegacy(copy);
	    
	    
	    ClipboardHolder holder = new ClipboardHolder(clipboard, es.getWorld().getWorldData());

		return holder;
	}
}
