package me.jimbo.plugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.PlayerInventory;

public class PlayerDeathListener implements Listener {

	@SuppressWarnings("unused")
	private CTF plugin;
	
	public PlayerDeathListener (CTF plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDeath(PlayerDeathEvent e)
	{
		e.getDrops().clear();
		if(e.getEntity() instanceof Player) {
			Player player = e.getEntity();
			player.getPlayer().getInventory().clear();
			if(player.getKiller() instanceof Player){
				Player killer = player.getKiller();
				Location killerLoc = killer.getLocation();
				Location victimLoc = player.getLocation();
				
				if(killerLoc.distance(victimLoc) > 20){
					player.sendMessage(ChatColor.YELLOW + "You Headshotted " + ChatColor.BLACK + player.getDisplayName() + ChatColor.YELLOW + "!");
					//victim.sendMessage(ChatColor.YELLOW + "You were headshotted by " + ChatColor.BLACK + player.getDisplayName() + ChatColor.YELLOW + "!");
				}
				
				killer.sendMessage(ChatColor.YELLOW + "You Headshotted " + ChatColor.BLACK + player.getDisplayName() + ChatColor.YELLOW + "!");
				player.sendMessage("");
			}
		}
		// Do nothing
	}
}
