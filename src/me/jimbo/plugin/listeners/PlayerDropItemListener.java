package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {

	@SuppressWarnings("unused")
	private CTF plugin;
	
	public PlayerDropItemListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("unused")
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e)
	{
		Player player = e.getPlayer();
		e.setCancelled(true);
	}
		
}
