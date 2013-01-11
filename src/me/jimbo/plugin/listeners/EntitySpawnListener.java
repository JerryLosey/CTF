package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class EntitySpawnListener implements Listener {

	@SuppressWarnings("unused")
	private CTF plugin;
	
	public EntitySpawnListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		e.setCancelled(true);
	}
}
