package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerKickListener implements Listener {

	private CTF plugin;
	
	public PlayerKickListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKick(PlayerKickEvent e) {
		e.setReason(ChatColor.AQUA + "Server Restarting. Reconnect.");
		if(CTF.AllPlayers.contains(e.getPlayer())){
			CTF.AllPlayers.remove(CTF.AllPlayers.indexOf(e.getPlayer()));
		}
		if(CTF.PlayerClasses.containsKey(e.getPlayer())){
			CTF.PlayerClasses.remove(e.getPlayer());
		}
		if(CTF.RedPlayers.contains(e.getPlayer())){
			CTF.RedPlayers.remove(CTF.RedPlayers.indexOf(e.getPlayer()));
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent e) {
		plugin.getServer().broadcastMessage("" + e.getEventName()); // Debug the floating too long kick
		if(CTF.AllPlayers.contains(e.getPlayer())){
			CTF.AllPlayers.remove(CTF.AllPlayers.indexOf(e.getPlayer()));
		}
		if(CTF.PlayerClasses.containsKey(e.getPlayer())){
			CTF.PlayerClasses.remove(e.getPlayer());
		}
		if(CTF.RedPlayers.contains(e.getPlayer())){
			CTF.RedPlayers.remove(CTF.RedPlayers.indexOf(e.getPlayer()));
			CTF.PlayerClasses.remove(e.getPlayer());
		}
		if(plugin.inProgress){
			if(CTF.PlayerClasses.size() < 1 || CTF.AllPlayers.size() < 1 || CTF.RedPlayers.size() < 1){
					plugin.getServer().broadcastMessage(ChatColor.GREEN + "There are not enough players to continue the match!  ");
					plugin.blueScore = 0;
					plugin.redScore = 0;
					plugin.roundOver = true;
					plugin.getServer().getScheduler().cancelAllTasks();
					plugin.startTimer(35);
				}
			}
	}
}
