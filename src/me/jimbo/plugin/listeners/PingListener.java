package me.jimbo.plugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import me.jimbo.plugin.CTF;

public class PingListener implements Listener {
	
	
private CTF plugin;
	
	public PingListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	  public void onPing(ServerListPingEvent event) {
		if(plugin.roundOver){
		    event.setMotd(ChatColor.YELLOW + "!!" + ChatColor.RED + "Round is restarting" + ChatColor.YELLOW + "!!");
		}else if(plugin.inProgress){
			event.setMotd(ChatColor.RED + "Th" + ChatColor.GOLD + "e " + ChatColor.YELLOW + "Si" + ChatColor.GREEN + "le" + ChatColor.DARK_BLUE + "nt " + ChatColor.DARK_AQUA + "No" + ChatColor.DARK_PURPLE + "ob" + ChatColor.AQUA + "s" + ChatColor.RED + " CTF");
		}
		else{
			event.setMotd(ChatColor.GREEN + "Round Starting Soon!");
		}
	  }

}