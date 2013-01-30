package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;
import me.jimbo.plugin.threads.BlockResetThread;
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
			Block block = e.getBlockPlaced();
			
			if((player.hasPermission("ctf.class.medic")) && (e.getBlockPlaced().getTypeId() == 30)){
				this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new BlockResetThread(e.getBlock()), 300L);
			}
			
			int redX = (int) plugin.getConfig().getDouble("Goals.Red.X");
			int redY = (int) plugin.getConfig().getDouble("Goals.Red.Y");
			int redZ = (int) plugin.getConfig().getDouble("Goals.Red.Z");
	
			int blueX = (int) plugin.getConfig().getDouble("Goals.Blue.X");
			int blueY = (int) plugin.getConfig().getDouble("Goals.Blue.Y");
			int blueZ = (int) plugin.getConfig().getDouble("Goals.Blue.Z");
			
			if(CTF.RedPlayers.contains(player) && (block.getTypeId() == 35)){
				if(placedAgainst.getLocation().getBlockX() != redX){
					e.setCancelled(true);
				}else if(placedAgainst.getLocation().getBlockX() == redX){
					if(placedAgainst.getLocation().getBlockY() != redY){
						e.setCancelled(true);
					} else if(placedAgainst.getLocation().getBlockY() == redY){
						if(placedAgainst.getLocation().getBlockZ() != redZ){
							e.setCancelled(true);
						}else if(placedAgainst.getLocation().getBlockZ() == redZ){
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
							CTF.addCapture.put(player, CTF.addCapture.get(player)+1);
							
						}
					}
					
				}
			}else if (CTF.AllPlayers.contains(player) && (block.getTypeId() == 35)){ //Blue team
				if(placedAgainst.getLocation().getBlockX() != blueX){
					e.setCancelled(true);
				}else if(placedAgainst.getLocation().getBlockX() == blueX){
					if(placedAgainst.getLocation().getBlockY() != blueY){
						e.setCancelled(true);
					} else if(placedAgainst.getLocation().getBlockY() == blueY){
						if(placedAgainst.getLocation().getBlockZ() != blueZ){
							e.setCancelled(true);
						} else if(placedAgainst.getLocation().getBlockZ() == blueZ){
							plugin.getServer().broadcastMessage(ChatColor.BLUE + "Blue" + ChatColor.GOLD + " team has scored a point!");
							plugin.setScore(2);
							plugin.getServer().broadcastMessage("Current Score: " + ChatColor.RED + plugin.getScore(1) + " : " + ChatColor.BLUE + plugin.getScore(2));
							e.getBlock().setType(Material.AIR);
							plugin.resetInv(player);
							plugin.resetFlag(1);
							if(player == plugin.blueFlagCarrier){
								plugin.blueFlagCarrier = null;
							}
							CTF.addCapture.put(player, CTF.addCapture.get(player)+1);
						}
					}
					
				}
				
			} else {
				if(block.getTypeId() != 30){
					e.setCancelled(true);
				}
			}
		}
	}
}
