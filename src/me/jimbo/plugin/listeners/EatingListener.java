package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;
import me.jimbo.plugin.threads.ItemRemoveThread;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EatingListener implements Listener {

	
	private CTF plugin;
	
	public EatingListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEat(PlayerInteractEvent e){
		if (e.getAction() == Action.RIGHT_CLICK_AIR) {
			try{
				if (e.getPlayer().getHealth() == e.getPlayer().getMaxHealth()) {
			        return;
			      }
				if (e.getItem().getTypeId() == 364) {
					this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new ItemRemoveThread(e.getPlayer(), new ItemStack(Material.COOKED_BEEF, 1)), 1L);
					e.getPlayer().setHealth(e.getPlayer().getHealth() + 8 <= 20 ? e.getPlayer().getHealth() + 8 : 20);
					}
				} catch (Exception ex) {
					//
				}
		}

	}
}