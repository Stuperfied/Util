/**
 * Util, a collection of useful utilities.
 * Copyright (C) Stuperfied <http://www.mshcraft.com.au>
 * Copyright (C) Util team and contributors
 * 
 * This file is part of au.com.mshcraft.Util
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with au.com.mshcraft.Util.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * This project has been created as a community store, so that code fragments are all in one place. This type of code storage,
 * allows projects greater flexibility. Please feel free to ask questions or make comments about the code. Also feel free 
 * to create pull requests for your own code, so that others might benefit. 
 */
 
package au.com.mshcraft.util.converters;

import org.bukkit.World;

import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.util.Location;

public class BukkitLocationConverter implements IBukkitLocationConverter {
	private static Location loc;
	
	public BukkitLocationConverter() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see au.com.mshcraft.util.IBukkitLocationConverter#toWorldEdit(org.bukkit.World, org.bukkit.Location)
	 */
	@Override
	public Location toWorldEdit(World world, org.bukkit.Location bukkitLocation) {
   	    int x = bukkitLocation.getBlockX();
   	    int y = bukkitLocation.getBlockY();
   	    int z = bukkitLocation.getBlockY();
   	    loc = new Location((Extent) world, x, y, z);
   	    return loc;
	}

}
