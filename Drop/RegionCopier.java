package au.com.mshcraft.modifyworld;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalWorld;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.schematic.SchematicFormat;
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
        
	    EditSession es = new EditSession((LocalWorld) region.getWorld(), Integer.MAX_VALUE);

		// LocalSession session = new LocalSession();
        // LocalPlayer localPlayer = worldEdit.wrapPlayer((org.bukkit.entity.Player) player);
        // LocalSession session = w.getSession(localPlayer);
        // LocalSession session = WorldEdit.getInstance().getSessionManager().get(player);
	    
	    
	    
        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
        clipboard.setOrigin(origin);
    
        // clipboard.setOrigin(session.getPlacementPosition(player));
      
        
        Extent destinationExtent = clipboard;   
        Extent sourceExtent = es;
        Vector to = region.getMinimumPoint();
        
	    ForwardExtentCopy copy = new ForwardExtentCopy(sourceExtent, region, destinationExtent, to);
	    Operations.completeLegacy(copy);
	    
	    
	    ClipboardHolder holder = new ClipboardHolder(clipboard, es.getWorld().getWorldData());
	    System.out.println("5");
	    
	    // clipboard.copy(player, session, es, region, false, null);
	    // ClipboardHolder holder = session.getClipboard();
	    
		return holder;
	}
	

}
