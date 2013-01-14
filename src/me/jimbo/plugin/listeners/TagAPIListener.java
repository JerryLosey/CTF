package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

public class TagAPIListener implements Listener {

	@SuppressWarnings("unused")
	private CTF plugin;
	
	public TagAPIListener (CTF plugin) {
		this.plugin = plugin;
	}
	
    @EventHandler
    public void onNameTag(PlayerReceiveNameTagEvent event) {
    	if(CTF.AllPlayers.contains(event.getPlayer())){
    		//Blue Team
    		event.setTag(ChatColor.DARK_RED + event.getNamedPlayer().getDisplayName());
    	}else if(CTF.RedPlayers.contains(event.getPlayer())){
    		//Red Team
    		event.setTag(ChatColor.DARK_BLUE + event.getNamedPlayer().getDisplayName());
    	}
    }
}
