package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;

public class ArmorRemovalListener implements Listener {

	
	@SuppressWarnings("unused")
	private CTF plugin;
	
	public ArmorRemovalListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler (ignoreCancelled = true)
	public void onArmorRemoval(InventoryClickEvent e){
		if(e.getSlotType().equals(SlotType.ARMOR)&& !e.getCurrentItem().getType().equals(Material.AIR)){
			e.setCancelled(true);
		}
		
	}
}
