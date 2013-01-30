package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.kitteh.tag.TagAPI;

public class PlayerJoinListener implements Listener {

	private CTF plugin;
	
	public PlayerJoinListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		//Handle Permissions
		
		CTF.addDeaths.put(e.getPlayer(), 0);
		CTF.addKills.put(e.getPlayer(), 0);
		CTF.addWin.put(e.getPlayer(), 0);
		CTF.addLoss.put(e.getPlayer(), 0);
		CTF.addCapture.put(e.getPlayer(), 0);
		CTF.timePlayed.put(e.getPlayer(), System.currentTimeMillis()/1000);
		
		int redsize = CTF.RedPlayers.size();
		int bluesize = CTF.AllPlayers.size();
		CTF.PlayerClasses.put(e.getPlayer(), "soldier");
		if (!CTF.AllPlayers.contains(e.getPlayer()) && !CTF.RedPlayers.contains(e.getPlayer())){
			if(redsize > bluesize){
				CTF.AllPlayers.add(e.getPlayer());
				TagAPI.refreshPlayer((Player) e.getPlayer());
				Location loc = new Location(Bukkit.getWorld("world"), plugin.getConfig().getDouble("Spawns.Blue.X"), plugin.getConfig().getDouble("Spawns.Blue.Y"), plugin.getConfig().getDouble("Spawns.Blue.Z"));
				e.getPlayer().teleport(loc);
			}else if(bluesize > redsize){
				CTF.RedPlayers.add(e.getPlayer());
				TagAPI.refreshPlayer((Player) e.getPlayer());
				Location loc = new Location(Bukkit.getWorld("world"), plugin.getConfig().getDouble("Spawns.Red.X"), plugin.getConfig().getDouble("Spawns.Red.Y"), plugin.getConfig().getDouble("Spawns.Red.Z"));
				e.getPlayer().teleport(loc);
				plugin.resetInv(e.getPlayer());
			} else {
				CTF.AllPlayers.add(e.getPlayer());
			}
			CTF.PlayerClasses.put(e.getPlayer(), "soldier");
		}
		
		if(!plugin.inProgress){
			Player player = e.getPlayer();
			Location location = player.getLocation();
			double x = plugin.getConfig().getDouble("Staging Area.X");
			double y = plugin.getConfig().getDouble("Staging Area.Y");
			double z = plugin.getConfig().getDouble("Staging Area.Z");
			
			location.setX(x);
			location.setY(y);
			location.setZ(z);
			player.teleport(location);
			player.getInventory().clear();
			plugin.resetInv(player);
			player.setHealth(20);
			player.setFoodLevel(20);
			for(PotionEffect effect : player.getActivePotionEffects()){
				player.removePotionEffect(effect.getType());
			}
			CTF.PlayerClasses.put(player, "soldier");
		}
		
	}

}
