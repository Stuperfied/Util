// This is a code fragment!

	private void setupBox(CuboidSelection s, Player player) {
		if (config.contains("Box.Template")) {
			com.sk89q.worldedit.Vector pos1, pos2;
			Vector vec1, vec2;
			
			vec1 = config.getVector("Box.Template.pos1");
			vec2 = config.getVector("Box.Template.pos2");
			String worldName = config.getString("Box.Template.world");
			com.sk89q.worldedit.world.World world = BukkitUtil.getLocalWorld(Bukkit.getWorld(worldName));			
			
			pos1 = BukkitUtil.toVector(vec1);
			pos2 = BukkitUtil.toVector(vec2);
			
			CuboidRegion region = new CuboidRegion(world, pos2, pos2);
						
			RegionCopier copier = new RegionCopier();
			ClipboardHolder clipboard = null;
			LocalPlayer localPlayer = worldEdit.wrapPlayer((org.bukkit.entity.Player) player);
			LocalSession session = WorldEdit.getInstance().getSessionManager().get(localPlayer);
			com.sk89q.worldedit.Vector origin = null;
			try {
				origin = session.getPlacementPosition(localPlayer);
				clipboard = copier.copyRegion(region, origin);
				EditSession es = new EditSession((LocalWorld) localPlayer.getWorld(), Integer.MAX_VALUE);
				RegionPaster paster = new RegionPaster();
				paster.paste(clipboard, es, origin);
			} catch (IncompleteRegionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (WorldEditException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
