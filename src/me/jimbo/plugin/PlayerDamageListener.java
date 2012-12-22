package me.jimbo.plugin;

import org.bukkit.ChatColor;
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
				Arrow a = (Arrow)e.getDamager();
				if(a.getShooter() instanceof Player){
					if(e.getEntity() instanceof Player){
						Player killer = (Player)a.getShooter();
						Player player = (Player)e.getEntity();
						if(killer.getLocation().distance(player.getLocation()) > 20){
							if(CTF.AllPlayers.contains(killer)){ //killer is on blue team
								if(CTF.AllPlayers.contains(player)){ //player is on blue team as well!
									e.setCancelled(true);
								}else if(CTF.RedPlayers.contains(player)){
									player.sendMessage(ChatColor.GOLD + "You were headshotted by " + ChatColor.BLUE + killer.getDisplayName() + ChatColor.GOLD + "!");
									killer.sendMessage(ChatColor.GOLD + "You headshotted " + ChatColor.RED + player.getDisplayName() + ChatColor.GOLD + "!");
									e.setDamage(20);
								}
							}else if(CTF.RedPlayers.contains(killer)){
								if(CTF.RedPlayers.contains(player)){
									e.setCancelled(true); // Can't kill teammates
								} else if(CTF.AllPlayers.contains(player)){
									player.sendMessage(ChatColor.GOLD + "You were headshotted by " + ChatColor.RED + killer.getDisplayName() + ChatColor.GOLD + "!");
									killer.sendMessage(ChatColor.GOLD + "You headshotted " + ChatColor.BLUE + player.getDisplayName() + ChatColor.GOLD + "!");
									e.setDamage(20);
								}
								
							}
							
						}
					}
				}
			}
		}else {
			e.setCancelled(true);
		}
	}
	
}
