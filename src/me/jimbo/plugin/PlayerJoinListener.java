package me.jimbo.plugin;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinListener implements Listener {

	private CTF plugin;
	
	public PlayerJoinListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (!CTF.AllPlayers.contains(e.getPlayer())){
			CTF.AllPlayers.add(e.getPlayer());
		}
		Player player = e.getPlayer();
		Location location = player.getLocation();
		double x = plugin.getConfig().getDouble("Staging Area.X");
		double y = plugin.getConfig().getDouble("Staging Area.Y");
		double z = plugin.getConfig().getDouble("Staging Area.Z");
		
		location.setX(x);
		location.setY(y);
		location.setZ(z);
		player.teleport(location);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent e) {
		CTF.AllPlayers.remove(CTF.AllPlayers.indexOf(e.getPlayer()));
	}
}
