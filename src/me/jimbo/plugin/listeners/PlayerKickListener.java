package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
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
		Player player = e.getPlayer();

		CTF.timePlayed.put(player, ((System.currentTimeMillis()/1000) - CTF.timePlayed.get(player)));
		
		e.setReason(ChatColor.AQUA + "Server Restarting. Reconnect.");
		if(CTF.AllPlayers.contains(player)){
			CTF.AllPlayers.remove(CTF.AllPlayers.indexOf(player));
		}
		if(CTF.PlayerClasses.containsKey(player)){
			CTF.PlayerClasses.remove(player);
		}
		if(CTF.RedPlayers.contains(player)){
			CTF.RedPlayers.remove(CTF.RedPlayers.indexOf(player));
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		
		CTF.timePlayed.put(player, ((System.currentTimeMillis()/1000) - CTF.timePlayed.get(player)));
		
		if(CTF.AllPlayers.contains(player)){
			CTF.AllPlayers.remove(CTF.AllPlayers.indexOf(player));
		}
		if(CTF.PlayerClasses.containsKey(player)){
			CTF.PlayerClasses.remove(player);
		}
		if(CTF.RedPlayers.contains(player)){
			CTF.RedPlayers.remove(CTF.RedPlayers.indexOf(player));
			CTF.PlayerClasses.remove(player);
		}
		if(plugin.inProgress){
			if((CTF.PlayerClasses.size() < 1 || CTF.AllPlayers.size() < 1 || CTF.RedPlayers.size() < 1) && plugin.inProgress){
					plugin.getServer().broadcastMessage(ChatColor.GREEN + "There are not enough players to continue the match!  ");
					plugin.inProgress = false;
					plugin.onRestart();
				}
			}
	}
}
