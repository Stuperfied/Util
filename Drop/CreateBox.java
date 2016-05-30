// This is a code fragment!
// Example of using RegionCopier and RegionPaster

	private void setupBox(CuboidSelection s, Player player) {
		if (config.contains("Box.Template")) {

			// In out example, the player can alter the region being copied in real time
			// So instead of detecting and saving a schematic for
			// every block update, we load the region vectors from config.
			com.sk89q.worldedit.Vector pos1, pos2;
			Vector vec1, vec2;
			
			vec1 = config.getVector("Box.Template.pos1");
			vec2 = config.getVector("Box.Template.pos2");
			String worldName = config.getString("Box.Template.world");

			com.sk89q.worldedit.world.World world = BukkitUtil.getLocalWorld(Bukkit.getWorld(worldName));						
			pos1 = BukkitUtil.toVector(vec1);
			pos2 = BukkitUtil.toVector(vec2);
			CuboidRegion region = new CuboidRegion(world, pos1, pos2);
						
			
			// Create an instance of the copy and paste code
			RegionCopier copier = new RegionCopier();
			RegionPaster paster = new RegionPaster();
			ClipboardHolder clipboard = null;

			// Our example has a player standing there typing /command create so lets take advantage
			LocalPlayer localPlayer = worldEdit.wrapPlayer((org.bukkit.entity.Player) player);
			LocalSession session = WorldEdit.getInstance().getSessionManager().get(localPlayer);
			EditSession es = new EditSession((LocalWorld) localPlayer.getWorld(), Integer.MAX_VALUE);
						
			// Now copy and paste in one go
			com.sk89q.worldedit.Vector playerOrigin = null;
			origin = region.getMinimumPoint();
			try {
				System.out.println("Origin = " + origin.toString());
				clipboard = copier.copyRegion(region, origin);
				
				playerOrigin = session.getPlacementPosition(localPlayer);							
				paster.paste(clipboard, es, playerOrigin);
			
			} catch (IncompleteRegionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (WorldEditException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
