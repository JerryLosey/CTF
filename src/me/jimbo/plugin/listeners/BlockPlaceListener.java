package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

	private CTF plugin;
	
	public BlockPlaceListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e)
	{
		if(plugin.inProgress){
			Player player = e.getPlayer();
			Block placedAgainst = e.getBlockAgainst();
			int redX = (int) plugin.getConfig().getDouble("Goals.Red.X");
			int redY = (int) plugin.getConfig().getDouble("Goals.Red.Y");
			int redZ = (int) plugin.getConfig().getDouble("Goals.Red.Z");
	
			int blueX = (int) plugin.getConfig().getDouble("Goals.Blue.X");
			int blueY = (int) plugin.getConfig().getDouble("Goals.Blue.Y");
			int blueZ = (int) plugin.getConfig().getDouble("Goals.Blue.Z");
			
			if(CTF.RedPlayers.contains(player)){
				if(placedAgainst.getLocation().getBlockX() == redX){
					if(placedAgainst.getLocation().getBlockY() == redY-1){
						e.setCancelled(true);
					} else if(placedAgainst.getLocation().getBlockY() == redY){
						if(placedAgainst.getLocation().getBlockZ() == redZ){
							plugin.getServer().broadcastMessage(ChatColor.RED + "Red" + ChatColor.GOLD + " team has scored a point!");
							plugin.setScore(1);
							plugin.getServer().broadcastMessage("Current Score: " + ChatColor.RED + plugin.getScore(1) + " : " + ChatColor.BLUE + plugin.getScore(2));
							e.getBlock().setType(Material.AIR);
							plugin.resetInv(player);
							plugin.resetFlag(2);
							if(player == plugin.redFlagCarrier){
								//plugin.getServer().broadcastMessage("The " + ChatColor.BLUE + "blue" + ChatColor.WHITE + " flag was reset!");
								plugin.redFlagCarrier = null;
							}
							
						}
					}
					
				}
			}else if (CTF.AllPlayers.contains(player)){ //Blue team
				if(placedAgainst.getLocation().getBlockX() == blueX){
					if(placedAgainst.getLocation().getBlockY() == blueY-1){
						e.setCancelled(true);
					} else if(placedAgainst.getLocation().getBlockY() == blueY){
						if(placedAgainst.getLocation().getBlockZ() == blueZ){
							plugin.getServer().broadcastMessage(ChatColor.BLUE + "Blue" + ChatColor.GOLD + " team has scored a point!");
							plugin.setScore(2);
							plugin.getServer().broadcastMessage("Current Score: " + ChatColor.RED + plugin.getScore(1) + " : " + ChatColor.BLUE + plugin.getScore(2));
							e.getBlock().setType(Material.AIR);
							plugin.resetInv(player);
							plugin.resetFlag(1);
							if(player == plugin.blueFlagCarrier){
								//plugin.getServer().broadcastMessage("The " + ChatColor.BLUE + "blue" + ChatColor.WHITE + " flag was reset!");
								plugin.blueFlagCarrier = null;
							}
							
						}
					}
					
				}
				
			} else {
				if(!player.isOp()){
					e.setCancelled(true);
				}
			}
		}
	}
}
