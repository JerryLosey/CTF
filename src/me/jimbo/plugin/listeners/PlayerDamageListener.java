package me.jimbo.plugin.listeners;

import java.util.List;

import me.jimbo.plugin.CTF;
import me.jimbo.plugin.threads.ItemRemoveThread;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerDamageListener implements Listener{
	
	private CTF plugin;
	
	public PlayerDamageListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	// Ninja class flash bombs
	@EventHandler
	public void onHit(ProjectileHitEvent event){
		if(plugin.inProgress){
			if (event.getEntity() instanceof Egg){
				Entity e = event.getEntity();
				Location loc = event.getEntity().getLocation();
				World world = event.getEntity().getWorld();
			    world.createExplosion(loc, 0.0F, false);
			    List<Entity> entities = e.getNearbyEntities(4.0D, 4.0D, 4.0D);
	
			      for (Entity entity : entities)
			      {
			        if (entity instanceof Player) {
			        	if(entity != event.getEntity()){
					          ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1), true);
					          ((Player)entity).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1), true);
			        	}
			        }
			      }
			}
		}
	}
	// Soldier class no-fall damage
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDamage(EntityDamageEvent e) {
		if(plugin.inProgress){
			if((e.getEntity() instanceof Player)){
				Player p = (Player)e.getEntity();
				if(CTF.PlayerClasses.containsKey(p)){
					if(CTF.PlayerClasses.get(p).equals("soldier")){
						if(e.getCause().equals(DamageCause.FALL)){
							e.setCancelled(true);
						}
					}
				}
				if(p.hasPermission("ctf.perk.autoeat")){
					if(p.getHealth() < 12){
						Inventory i = p.getInventory();
						for(ItemStack item : i.getContents()){
							if((item != null) && item.getType().equals(Material.COOKED_BEEF)){
								this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new ItemRemoveThread(p, new ItemStack(Material.COOKED_BEEF, 1)), 1L);
								p.setHealth(p.getHealth() + 8 <= 20 ? p.getHealth() + 8 : 20);
							}
						}
					}
				}
				if(p.getHealth() - e.getDamage() < 1){
					PlayerInventory inv = p.getInventory();
					inv.clear();
					inv.setArmorContents(null);
					p.setHealth(0);
					if((p == plugin.redFlagCarrier) && (CTF.RedPlayers.contains(p))){
						plugin.resetFlag(2);
						plugin.redFlagCarrier = null;
						plugin.getServer().broadcastMessage(ChatColor.DARK_RED + p.getDisplayName() + ChatColor.WHITE + " dropped the " + ChatColor.BLUE + "blue " + ChatColor.WHITE + "flag!");
						plugin.getServer().broadcastMessage("The " + ChatColor.BLUE + "blue" + ChatColor.WHITE + " flag was reset!");
					}else if ((p == plugin.blueFlagCarrier) && (CTF.AllPlayers.contains(p))) {
						plugin.resetFlag(1);
						plugin.blueFlagCarrier = null;
						plugin.getServer().broadcastMessage(ChatColor.BLUE + p.getDisplayName() + ChatColor.WHITE + " dropped the " + ChatColor.DARK_RED + "red " + ChatColor.WHITE + "flag!");
						plugin.getServer().broadcastMessage("The " + ChatColor.DARK_RED + "red" + ChatColor.WHITE + " flag was reset!");
					}
				}
			}
		}else{
			e.setCancelled(true);
		}
	}
	// Medic class heal teammates
	// Soldier class no-fall damage
	// Archer class insta-kill arrows
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDamageEnt(EntityDamageByEntityEvent e) {
		if(plugin.inProgress){
			if((e.getDamager() instanceof Player) && (e.getEntity() instanceof Player)){
				if(CTF.PlayerClasses.get(e.getDamager()).equals("medic")){
					Player medic = (Player)e.getDamager();
					Player hp = (Player)e.getEntity();
					if(CTF.RedPlayers.contains(medic) && CTF.RedPlayers.contains(hp)){
						//Both on Red Team
						hp.setHealth(20);
						plugin.resetInv(hp);
					}
					if(CTF.AllPlayers.contains(medic) && CTF.AllPlayers.contains(hp)){
						//Both on Blue Team
						hp.setHealth(20);
						plugin.resetInv(hp);
					}
				}
			}
			if((e.getDamager() instanceof Player) && (e.getEntity() instanceof Player)){
				Player damager = (Player) e.getDamager();
				Player player = (Player) e.getEntity();
				if(CTF.AllPlayers.contains(damager) && CTF.AllPlayers.contains(player)){
					e.setCancelled(true);
				}else if(CTF.RedPlayers.contains(damager) && CTF.RedPlayers.contains(player)){
					e.setCancelled(true);
				}
				
			}
			if((e.getEntity() instanceof Player)){
				Player p = (Player)e.getEntity();
				if(CTF.PlayerClasses.containsKey(p)){
					if(CTF.PlayerClasses.get(p).equals("soldier")){
						if(e.getCause().equals(DamageCause.FALL)){
							e.setCancelled(true);
						}
					}
				}
				if(p.hasPermission("ctf.perk.autoeat")){
					if(p.getHealth() < 12){
						Inventory i = p.getInventory();
						for(ItemStack item : i.getContents()){
							if((item != null) && item.getType().equals(Material.COOKED_BEEF)){
								this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new ItemRemoveThread(p, new ItemStack(Material.COOKED_BEEF, 1)), 1L);
								p.setHealth(p.getHealth() + 8 <= 20 ? p.getHealth() + 8 : 20);
							}
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
						if(killer.getLocation().distanceSquared(player.getLocation()) >= 400){
							if(CTF.AllPlayers.contains(killer)){ //killer is on blue team
								if(CTF.AllPlayers.contains(player)){ //player is on blue team as well!
									e.setCancelled(true);
								}else if(CTF.RedPlayers.contains(player)){
									player.sendMessage(ChatColor.GOLD + "You were headshotted by " + ChatColor.BLUE + killer.getDisplayName() + ChatColor.GOLD + "!");
									killer.sendMessage(ChatColor.GOLD + "You headshotted " + ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GOLD + "!");
									e.setDamage(20);
								}
							}else if(CTF.RedPlayers.contains(killer)){
								if(CTF.RedPlayers.contains(player)){
									e.setCancelled(true); // Can't kill teammates
								} else if(CTF.AllPlayers.contains(player)){
									player.sendMessage(ChatColor.GOLD + "You were headshotted by " + ChatColor.DARK_RED + killer.getDisplayName() + ChatColor.GOLD + "!");
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
