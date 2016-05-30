package au.com.mshcraft.modifyworld;

import org.bukkit.Bukkit;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.ClipboardHolder;


public class RegionPaster {
	public void paste(ClipboardHolder holder, EditSession editSession, Vector origin) throws WorldEditException {

		WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		WorldEdit w = worldEdit.getWorldEdit();
        
		Clipboard clipboard = holder.getClipboard();
		Extent sourceExtent = clipboard;
		Region region = clipboard.getRegion(); 
		Vector from = clipboard.getOrigin();
        
		Extent targetExtent = region.getWorld();
		Vector to = origin;
        
        ForwardExtentCopy copy = new ForwardExtentCopy(sourceExtent, region, from, targetExtent, to);
        Operations.completeLegacy(copy);	
	}
}
