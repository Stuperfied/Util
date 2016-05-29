package au.com.mshcraft.modifyworld;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalPlayer;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.LocalWorld;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.command.ClipboardCommands;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.session.SessionManager;

public class RegionCopier {

	public ClipboardHolder copyRegion(CuboidRegion region) throws WorldEditException {

		WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		WorldEdit w = worldEdit.getWorldEdit();
        
	    EditSession es = new EditSession((LocalWorld) region.getWorld(), Integer.MAX_VALUE);
		
		OfflinePlayer offlinePlayer = null;
		Player player = (Player) offlinePlayer;
		
		// LocalSession session = new LocalSession();
        // LocalPlayer localPlayer = worldEdit.wrapPlayer((org.bukkit.entity.Player) player);
        // LocalSession session = w.getSession(localPlayer);
        LocalSession session = WorldEdit.getInstance().getSessionManager().get(player);
	    
	    ClipboardCommands clipboard = new ClipboardCommands(w);
	    clipboard.copy(player, session, es, region, false, null);
	    ClipboardHolder holder = session.getClipboard();
		return holder;
	}
	

}

