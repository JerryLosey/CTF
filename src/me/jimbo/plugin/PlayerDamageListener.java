package me.jimbo.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.PlayerInventory;

public class PlayerDamageListener implements Listener{
	
	//private PlayerCommands pCommands;
	@SuppressWarnings("unused")
	private CTF plugin;
	
	public PlayerDamageListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDamage(EntityDamageEvent e) {
		if((e.getEntity() instanceof Player)){
			Player p = (Player)e.getEntity();
			if(e.getCause().equals(DamageCause.FALL)){
				p.sendMessage("Fall Damage");
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
