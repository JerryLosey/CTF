package me.jimbo.plugin;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.PlayerInventory;

public class PlayerDamageListener implements Listener{
	
	private CTF plugin;
	
	public PlayerDamageListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDamage(EntityDamageEvent e) {
		if(plugin.canAttack){
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
		}else{
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDamageEnt(EntityDamageByEntityEvent e) {
		if(plugin.canAttack){
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
			if(e.getDamager().getType() == EntityType.ARROW){
				//plugin.getServer().broadcastMessage("Entity = Arrow");
				Arrow a = (Arrow)e.getDamager();
				if(a.getShooter() instanceof Player){
					//plugin.getServer().broadcastMessage("Shooter = Player");
					if(e.getEntity() instanceof Player){
						//plugin.getServer().broadcastMessage("Player = Player");
						Player killer = (Player)a.getShooter();
						Player player = (Player)e.getEntity();
						if(killer.getLocation().distance(player.getLocation()) > 20){
							player.sendMessage("Headshot!");
							killer.sendMessage("Headshot!");
							e.setDamage(20);
						}
					}
				}
			}
		}else {
			e.setCancelled(true);
		}
	}
	
}
