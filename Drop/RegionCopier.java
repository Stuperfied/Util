package au.com.mshcraft.modifyworld;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.Bukkit;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
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
	    System.out.println("5");
	    
	    
	    
	   // Stop reading here, this just throws it out as a schematic for troubleshooting.
	    String schematicName = "testSchematic1.scm";		
	    String folder;
		File file;
		
		String separator = File.separator;
		folder = separator + "schematics" + separator;	
		Path sysDir = Paths.get(folder);
		
		file = new File(sysDir.toString(), schematicName);
	    File dir = new File(sysDir.toString());
	    
	    if (!dir.exists())
	        dir.mkdirs();
	       
	    if(!file.exists()) {
	    	System.out.println("File does not exist, creating it.");
	    	try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println("File created");
	    }
	    
        Vector min = holder.getClipboard().getMinimumPoint();
        Vector max = holder.getClipboard().getMaximumPoint();
        
        es.enableQueue();
        CuboidClipboard clip = new CuboidClipboard(max.subtract(min).add(new Vector(1, 1, 1)), min);
        clip.copy(es);
        try {
			SchematicFormat.MCEDIT.save(clip, file);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        es.flushQueue();
	    

	    
		return holder;
	}
	

}


// old stuff I tried.

// EditSession es = new EditSession((LocalWorld) region.getWorld(), Integer.MAX_VALUE);
// LocalSession session = new LocalSession();
// LocalPlayer localPlayer = worldEdit.wrapPlayer((org.bukkit.entity.Player) player);
// LocalSession session = w.getSession(localPlayer);
// LocalSession session = WorldEdit.getInstance().getSessionManager().get(player);
// clipboard.setOrigin(session.getPlacementPosition(player)); 
// clipboard.copy(player, session, es, region, false, null);
// ClipboardHolder holder = session.getClipboard();
