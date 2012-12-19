package me.jimbo.plugin;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerKickListener implements Listener {

	@SuppressWarnings("unused")
	private CTF plugin;
	
	public PlayerKickListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKick(PlayerKickEvent e) {
		e.setReason(ChatColor.AQUA + "Server Restarting. Reconnect.");
		CTF.AllPlayers.remove(CTF.AllPlayers.indexOf(e.getPlayer()));
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent e) {
		CTF.AllPlayers.remove(CTF.AllPlayers.indexOf(e.getPlayer()));
	}
}
