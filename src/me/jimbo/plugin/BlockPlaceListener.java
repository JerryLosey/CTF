package me.jimbo.plugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
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
		Player player = e.getPlayer();
		e.getBlock();
		Block placedAgainst = e.getBlockAgainst();
		int redX = (int) plugin.getConfig().getDouble("Goals.Red.X");
		int redY = (int) plugin.getConfig().getDouble("Goals.Red.Y");
		int redZ = (int) plugin.getConfig().getDouble("Goals.Red.Z");

		int blueX = (int) plugin.getConfig().getDouble("Goals.Blue.X");
		int blueY = (int) plugin.getConfig().getDouble("Goals.Blue.Y");
		int blueZ = (int) plugin.getConfig().getDouble("Goals.Blue.Z");
		
		//new Location(Bukkit.getWorlds().get(0), redX, redY, redZ);
		
		if(CTF.RedPlayers.contains(player)){
			if(placedAgainst.getLocation().getBlockX() == redX){
				if(placedAgainst.getLocation().getBlockY() == redY){
					if(placedAgainst.getLocation().getBlockZ() == redZ){
						plugin.getServer().broadcastMessage(ChatColor.RED + "Red" + ChatColor.GOLD + " team has scored a point!");
						plugin.setScore(1);
						plugin.getServer().broadcastMessage("Current Score: " + ChatColor.RED + plugin.getScore(1) + " : " + ChatColor.BLUE + plugin.getScore(2));
						e.getBlock().setType(Material.AIR);
						plugin.resetInv(player);
						Location loc = new Location(player.getWorld(), (double)redX,(double)redY,(double)redZ);
						Block b = loc.getBlock();
						b.setTypeId(35);
						b.setData((byte) 11);
					}
				}
				
			}
		}else if (CTF.AllPlayers.contains(player)){ //Blue team
			if(placedAgainst.getLocation().getBlockX() == blueX){
				if(placedAgainst.getLocation().getBlockY() == blueY){
					if(placedAgainst.getLocation().getBlockZ() == blueZ){
						plugin.getServer().broadcastMessage(ChatColor.BLUE + "Blue" + ChatColor.GOLD + " team has scored a point!");
						plugin.setScore(2);
						plugin.getServer().broadcastMessage("Current Score: " + ChatColor.RED + plugin.getScore(1) + " : " + ChatColor.BLUE + plugin.getScore(2));
						e.getBlock().setType(Material.AIR);
						plugin.resetInv(player);
						Location loc = new Location(player.getWorld(), (double)redX,(double)redY,(double)redZ);
						Block b = loc.getBlock();
						b.setTypeId(35);
						b.setData((byte) 14);
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
