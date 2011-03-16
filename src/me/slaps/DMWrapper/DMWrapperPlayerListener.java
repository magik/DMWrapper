package me.slaps.DMWrapper;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

public class DMWrapperPlayerListener extends PlayerListener {
	
	DMWrapper parent;
	
	protected HashMap<String, Boolean> inShopMap = new HashMap<String, Boolean>();

	public DMWrapperPlayerListener(DMWrapper plug) {
		parent = plug;
		parent.getServer().getPluginManager().registerEvent(Event.Type.PLAYER_MOVE, this, Priority.Monitor, parent);
	}
	
	@Override
	public void onPlayerMove(PlayerMoveEvent event) {
        if ( !parent.locMgr.shopLocationsEnabled ) return;
	    
	    String playerName = event.getPlayer().getName();
	    
	    if (!inShopMap.containsKey(playerName)) inShopMap.put(playerName,false);
	    
		Location to = event.getTo();
		Location blockTo = new Location(to.getWorld(), to.getX(), to.getY(), to.getZ() );
		
		Boolean inShop = inShopMap.get(playerName);
		
		if ( parent.locMgr.inShopLoc(blockTo) ) {
		    if ( !inShop ) {
		        event.getPlayer().sendMessage("Entered the shopping area.");
		        inShopMap.put(playerName, true);
		    }
		} else {
		    if ( inShop ) {
                event.getPlayer().sendMessage("Leaving the shopping area.");
                inShopMap.put(playerName, false);
		    }
		}
		    
		
	}	
}