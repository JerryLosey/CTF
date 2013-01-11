package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class InventoryOpenListener implements Listener {

	@SuppressWarnings("unused")
	private CTF plugin;
	
	public InventoryOpenListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryOpen(InventoryOpenEvent e)
	{
		e.setCancelled(true);
		// Do nothing
	}
}
