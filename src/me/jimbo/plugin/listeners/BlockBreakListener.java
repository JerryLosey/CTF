package me.jimbo.plugin.listeners;


import me.jimbo.plugin.CTF;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {

	private CTF plugin;
	
	public BlockBreakListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	public Block getBlock(BlockEvent e) {
		//Block block = getBlock(e);
		return e.getBlock();
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		if(plugin.inProgress){
			Block block = e.getBlock();
			Player player = e.getPlayer();
			int redX = (int) plugin.getConfig().getDouble("Goals.Red.X");
			int redY = (int) plugin.getConfig().getDouble("Goals.Red.Y");
			int redZ = (int) plugin.getConfig().getDouble("Goals.Red.Z");
	
			int blueX = (int) plugin.getConfig().getDouble("Goals.Blue.X");
			int blueY = (int) plugin.getConfig().getDouble("Goals.Blue.Y");
			int blueZ = (int) plugin.getConfig().getDouble("Goals.Blue.Z");
			//brokenBlock.getData() == 11 is blue
			//brokenBlock.getData() == 14 is red
			if(plugin.inProgress){
				if(CTF.RedPlayers.contains(player)){
					if(block.getLocation().getBlockX() == blueX){
						if(block.getLocation().getBlockY() == blueY){
							if(block.getLocation().getBlockZ() == blueZ){
								if(block.getData() == 11){
									ItemStack flag = block.getDrops().toArray(new ItemStack[]{new ItemStack(35, 11)})[0];
									Inventory inv = player.getInventory();
									inv.clear();
									player.getInventory().addItem(flag);
									player.getInventory().setHelmet(flag);
									block.setType(Material.AIR);
									plugin.getServer().broadcastMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.WHITE + " has the " + ChatColor.BLUE + "blue" + ChatColor.WHITE + " flag!");
									plugin.blueFlagCarrier = player;
								}
								else if(block.getData() == 14) {
									e.setCancelled(true);
								}
							}else{
								e.setCancelled(true);
							}
						}else{
							e.setCancelled(true);
						}
						
					}else{
						e.setCancelled(true);
					}
				}else if (CTF.AllPlayers.contains(player)){ //Blue team
					if(block.getLocation().getBlockX() == redX){
						if(block.getLocation().getBlockY() == redY){
							if(block.getLocation().getBlockZ() == redZ){
								if(block.getData() == 14){
									ItemStack flag = block.getDrops().toArray(new ItemStack[]{new ItemStack(35, 14)})[0];
									Inventory inv = player.getInventory();
									inv.clear();
									player.getInventory().addItem(flag);
									player.getInventory().setHelmet(flag);
									block.setType(Material.AIR);
									plugin.getServer().broadcastMessage(ChatColor.BLUE + player.getDisplayName() + ChatColor.WHITE + " has the " + ChatColor.DARK_RED + "red" + ChatColor.WHITE + " flag!");
									plugin.redFlagCarrier = player;
								}
								else if(block.getData() != 14) {
									e.setCancelled(true);
								}
							}
							else{
								e.setCancelled(true);
							}
						}else{
							e.setCancelled(true);
						}
						
					}else{
						e.setCancelled(true);
					}
					
				}
			}else{
				e.setCancelled(true);
			}
		}else{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onLeavesDecay(LeavesDecayEvent e)
	{
		e.setCancelled(true);
	}
}
