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
			try {
				clipboard = copier.copyRegion(region);
			} catch (WorldEditException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RegionPaster paster = new RegionPaster();
			try {
				paster.paste(clipboard, (com.sk89q.worldedit.entity.Player) player);
			} catch (WorldEditException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}