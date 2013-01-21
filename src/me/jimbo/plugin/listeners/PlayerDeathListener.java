package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

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
			player.getPlayer().getInventory().setArmorContents(null);
			e.setDeathMessage(null);
			if(CTF.RedPlayers.contains(player)){
				if(plugin.blueFlagCarrier == player){
					plugin.getServer().broadcastMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.WHITE + " dropped the " + ChatColor.BLUE + "blue " + ChatColor.WHITE + "flag!");
					plugin.resetFlag(2);
				}
			}else if (CTF.AllPlayers.contains(player)) {
				if(plugin.redFlagCarrier == player){
					plugin.getServer().broadcastMessage(ChatColor.BLUE + player.getDisplayName() + ChatColor.WHITE + " dropped the " + ChatColor.DARK_RED + "red " + ChatColor.WHITE + "flag!");
					plugin.getServer().broadcastMessage("The " + ChatColor.DARK_RED + "red" + ChatColor.WHITE + " flag was reset!");
					plugin.resetFlag(1);
				}
			}
		}
		// Do nothing
	}
}
