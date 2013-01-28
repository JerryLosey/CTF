package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;
import me.jimbo.plugin.threads.ItemRemoveThread;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class PlayerClickEatListener implements Listener {

	
	private CTF plugin;
	
	public PlayerClickEatListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEat(PlayerInteractEvent e){
		if(plugin.inProgress){
			if((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getPlayer().hasPermission("ctf.class.soldier")) && (e.getPlayer().getItemInHand().getTypeId() == 267)){
				e.getPlayer().setVelocity(new Vector(0.0D, 0.9D, 0.0D));
			}else if((e.getAction() == Action.RIGHT_CLICK_BLOCK) && ((e.getPlayer().hasPermission("ctf.class.ninja") || e.getPlayer().hasPermission("ctf.class.firefly"))) && (e.getPlayer().getItemInHand().getTypeId() == 353)){
				this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new ItemRemoveThread(e.getPlayer(), new ItemStack(Material.SUGAR, 1)), 1L);
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1), true);
			}else if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
				try{
					if (e.getPlayer().getHealth() == e.getPlayer().getMaxHealth()) {
				        return;
				      }
					if (e.getItem().getTypeId() == 364) {
						this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new ItemRemoveThread(e.getPlayer(), new ItemStack(Material.COOKED_BEEF, 1)), 1L);
						e.getPlayer().setHealth(e.getPlayer().getHealth() + 8 <= 20 ? e.getPlayer().getHealth() + 8 : 20);
						e.getPlayer().setFoodLevel(20);
						}
					} catch (Exception ex) {
						//
					}
			}
		}else{
			e.setCancelled(true);
		}

	}
}
