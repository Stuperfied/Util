package au.com.mshcraft.modifyworld;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.command.ClipboardCommands;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.session.ClipboardHolder;


public class RegionPaster {
	public void paste(ClipboardHolder clipboard, Player player) throws WorldEditException {

		Plugin worldEditPlugin = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        WorldEdit worldEdit = (WorldEdit) worldEditPlugin;
        
	    EditSession es = new LocalSession().createEditSession(player);
	    LocalSession session = WorldEdit.getInstance().getSessionManager().get(player);
			
	    ClipboardCommands clipboardCommands = new ClipboardCommands(worldEdit);
	    clipboardCommands.paste(player, session, es, false, false, false);		
	}
}
