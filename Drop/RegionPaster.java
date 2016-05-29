package au.com.mshcraft.modifyworld;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.command.ClipboardCommands;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.ClipboardHolder;


public class RegionPaster {
	public void paste(ClipboardHolder holder, EditSession editSession, Vector origin) throws WorldEditException {

		WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		WorldEdit w = worldEdit.getWorldEdit();
        
        Clipboard clipboard = holder.getClipboard();
        Region region = clipboard.getRegion();

        
        ForwardExtentCopy copy = new ForwardExtentCopy(clipboard, region, clipboard.getOrigin(), editSession.getWorld(), origin);

        Operations.completeLegacy(copy);
        
        // atOrigin ? clipboard.getOrigin() : 
        //Vector to = origin;
        //Operation operation = holder
                //.createPaste(editSession, editSession.getWorld().getWorldData())
                //.to(to)
                //.ignoreAirBlocks(false)
                //.build();
        //Operations.completeLegacy(operation);
		
		
		
		
	    // EditSession es = new LocalSession().createEditSession(player);
	    // LocalSession session = WorldEdit.getInstance().getSessionManager().get(player);
			
	    // ClipboardCommands clipboardCommands = new ClipboardCommands(w);
	    // clipboardCommands.paste(player, session, es, false, false, false);		
	}
}
