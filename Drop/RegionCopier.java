package au.com.mshcraft.modifyworld;

import org.bukkit.Bukkit;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalWorld;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.session.ClipboardHolder;

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
	    ForwardExtentCopy copy = new ForwardExtentCopy(es, region, clipboard, region.getMinimumPoint());
	    Operations.completeLegacy(copy);
	    ClipboardHolder holder = new ClipboardHolder(clipboard, es.getWorld().getWorldData());
	    
	    
	    // clipboard.copy(player, session, es, region, false, null);
	    // ClipboardHolder holder = session.getClipboard();
	    
		return holder;
	}
	

}
