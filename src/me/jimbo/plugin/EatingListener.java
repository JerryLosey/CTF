package me.jimbo.plugin;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EatingListener implements Listener {

	
	@SuppressWarnings("unused")
	private CTF plugin;
	
	public EatingListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler (ignoreCancelled = true)
	public void onEat(PlayerInteractEvent e){
		if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK) ? 1 : 0) == 0) {
		      return;
		    }
		if (e.getItem().getType() != Material.COOKED_BEEF) {
			e.getPlayer().setHealth(e.getPlayer().getHealth() + 4);
			e.getItem().setType(Material.AIR);
		}
	}
}
