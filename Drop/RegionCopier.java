package au.com.mshcraft.modifyworld;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.LocalWorld;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.command.ClipboardCommands;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.ClipboardHolder;

public class RegionCopier {

	public ClipboardHolder copyRegion(Region region) throws WorldEditException {

		Plugin worldEditPlugin = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        WorldEdit worldEdit = (WorldEdit) worldEditPlugin;
        
	    EditSession es = new EditSession((LocalWorld) region.getWorld(), Integer.MAX_VALUE);
		
		OfflinePlayer offlinePlayer = null;
		Player player = (Player) offlinePlayer;
		LocalSession session = new LocalSession();
	    ClipboardCommands clipboard = new ClipboardCommands(worldEdit);
	    clipboard.copy(player, session, es, region, false, null);
	    ClipboardHolder holder = session.getClipboard();
		return holder;
	}
	

}
