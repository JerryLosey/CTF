package me.jimbo.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

	private CTF plugin;
	
	public PlayerRespawnListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerRespawnEvent e)
	{
		Player player = e.getPlayer();
	
		if(CTF.BluePlayers.contains(player)){
			Location location = new Location(Bukkit.getWorlds().get(0), (double) plugin.getConfig().getDouble("Spawns.Blue.X"), (double) plugin.getConfig().getDouble("Spawns.Blue.Y"), (double) plugin.getConfig().getDouble("Spawns.Blue.Z"));
			e.setRespawnLocation(location);
		} else if (CTF.RedPlayers.contains(player)) {
			Location location = new Location(Bukkit.getWorlds().get(0), (double) plugin.getConfig().getDouble("Spawns.Red.X"), (double) plugin.getConfig().getDouble("Spawns.Red.Y"), (double) plugin.getConfig().getDouble("Spawns.Red.Z"));
			e.setRespawnLocation(location);
		} else {
			Bukkit.broadcastMessage("An Error Occured! Notify an admin to check the logs!");
			plugin.getLogger().warning("Error On PlayerRespawn! Player hasn't been assigned a team!");
		}
	}
}
