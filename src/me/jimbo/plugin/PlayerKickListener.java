package me.jimbo.plugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class PlayerKickListener implements Listener {

	@SuppressWarnings("unused")
	private CTF plugin;
	
	public PlayerKickListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKick(PlayerKickEvent e) {
		//e.setReason(ChatColor.AQUA + "Not Enough Players");
	}
}
