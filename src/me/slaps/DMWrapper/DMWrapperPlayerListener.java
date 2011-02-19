package me.slaps.DMWrapper;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

public class DMWrapperPlayerListener extends PlayerListener {
	
	DMWrapper parent;

	public DMWrapperPlayerListener(DMWrapper plug) {
		parent = plug;
	}
	
	@Override
	public void onPlayerMove(PlayerMoveEvent event) {
		if ( !parent.locMgr.shopLocationsEnabled ) return;
		
		Location from = event.getFrom();
		Location to = event.getTo();
		Location blockFrom = new Location(from.getWorld(), from.getBlockX(), from.getBlockY(), from.getBlockZ() );
		Location blockTo = new Location(to.getWorld(), to.getBlockX(), to.getBlockY(), to.getBlockZ() );
		
		if ( blockFrom.equals(blockTo) ) return;

		if ( parent.locMgr.inShopLoc(blockFrom) ) {
			if ( !parent.locMgr.inShopLoc(blockTo) ) {
				event.getPlayer().sendMessage("Leaving the shopping area.");
			}
		} else {
			if ( parent.locMgr.inShopLoc(blockTo) ) {
				event.getPlayer().sendMessage("Entered the shopping area.");
			}
		}
		
	}	
}