package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
//import org.bukkit.potion.PotionEffect;
//import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerDeathListener implements Listener {

	private CTF plugin;
	
	public PlayerDeathListener (CTF plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDeath(PlayerDeathEvent e)
	{
		e.getDrops().clear();
		if(e.getEntity() instanceof Player) {
			Player player = e.getEntity();
			Player killer = e.getEntity().getKiller();
			
			CTF.addDeaths.put(player, CTF.addDeaths.get(player)+1);
			if(killer instanceof Player){
				CTF.addKills.put(killer, CTF.addKills.get(killer)+1);
			}
			
			player.getPlayer().getInventory().clear();
			player.getPlayer().getInventory().setArmorContents(null);
			e.setDeathMessage(null);
			
			// Beserker perk on kill
			if((killer instanceof Player) && CTF.PlayerClasses.get(player).equalsIgnoreCase("berserker")){
				killer.sendMessage(ChatColor.DARK_PURPLE + "Roid Rage");
			    killer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 420, 0));
			    killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 420, 0));
			}
			if(CTF.PlayerClasses.get(player).equalsIgnoreCase("martyr")){
				Location loc = player.getLocation();
				plugin.getServer().getWorld("world").createExplosion(loc, 2.0F, false);// Add another ",false" to the end when updating to 1.4.7+
			}
			if(CTF.RedPlayers.contains(player)){
				if(plugin.blueFlagCarrier == player){
					plugin.getServer().broadcastMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.WHITE + " dropped the " + ChatColor.BLUE + "blue " + ChatColor.WHITE + "flag!");
					plugin.getServer().broadcastMessage("The " + ChatColor.DARK_BLUE + "blue" + ChatColor.WHITE + " flag was reset!");
					plugin.resetFlag(2);
				}
			}else if (CTF.AllPlayers.contains(player)) {
				if(plugin.redFlagCarrier == player){
					plugin.getServer().broadcastMessage(ChatColor.BLUE + player.getDisplayName() + ChatColor.WHITE + " dropped the " + ChatColor.DARK_RED + "red " + ChatColor.WHITE + "flag!");
					plugin.getServer().broadcastMessage("The " + ChatColor.DARK_RED + "red" + ChatColor.WHITE + " flag was reset!");
					plugin.resetFlag(1);
				}
			}
		}
		// Do nothing
	}
}
