package me.jimbo.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.PlayerInventory;

public class PlayerDamageListener implements Listener{
	
	@SuppressWarnings("unused")
	private CTF plugin;
	
	public PlayerDamageListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDamage(EntityDamageEvent e) {
		if((e.getEntity() instanceof Player)){
			Player p = (Player)e.getEntity();
			if(CTF.PlayerClasses.containsKey(p)){
				if(CTF.PlayerClasses.get(p).equals("soldier")){
					if(e.getCause().equals(DamageCause.FALL)){
						e.setCancelled(true);
					}
				}
			}
			if(p.getHealth() - e.getDamage() < 1){
				PlayerInventory inv = p.getInventory();
				inv.clear();
				inv.setArmorContents(null);
				p.setHealth(0);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDamageEnt(EntityDamageByEntityEvent e) {
		if((e.getEntity() instanceof Player)){
			Player p = (Player)e.getEntity();
			if(CTF.PlayerClasses.containsKey(p)){
				if(CTF.PlayerClasses.get(p).equals("soldier")){
					if(e.getCause().equals(DamageCause.FALL)){
						e.setCancelled(true);
					}
				}
			}
			if(p.getHealth() - e.getDamage() < 1){
				PlayerInventory inv = p.getInventory();
				inv.clear();
				inv.setArmorContents(null);
				p.setHealth(0);
			}

		}
	}
	
}
