package me.jimbo.plugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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
		Block block = e.getBlock();
		Block placedAgainst = e.getBlockAgainst();
		placedAgainst.getRelative(BlockFace.UP);
		int redX = (int) plugin.getConfig().getDouble("Diamond Blocks.Red.X");
		int redY = (int) plugin.getConfig().getDouble("Diamond Blocks.Red.Y");
		//int redZ = (int) plugin.getConfig().getDouble("Diamond Blocks.Red.Z");
		
		//Location redLocation = new Location(Bukkit.getWorlds().get(0), redX, redY, redZ);
		
		if(CTF.AllPlayers.contains(player)){
			if((int)(placedAgainst.getLocation().getY()+1) == redY){
				if((int)(placedAgainst.getLocation().getX()) == redX){
					if(block.getType() == Material.WOOL ){
						if(placedAgainst.getType() == Material.DIAMOND_BLOCK && block.getData() == 11){
							plugin.getServer().broadcastMessage(ChatColor.RED + "Red" + ChatColor.GOLD + " team has scored a point!");
							plugin.setScore(1);
							plugin.getServer().broadcastMessage("Current Score: " + ChatColor.RED + plugin.getScore(1) + " : " + ChatColor.BLUE + plugin.getScore(2));
						}
					}
				}
			}
		}else if (CTF.AllPlayers.contains(player)){
			
		} else {
			if(!player.isOp()){
				e.setCancelled(true);
			}
		}
	}
}
