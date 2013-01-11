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
		Block block = e.getBlock();
		Player player = e.getPlayer();
		//brokenBlock.getData() == 11 is blue
		//brokenBlock.getData() == 14 is red
		if(plugin.canAttack){
			if(CTF.AllPlayers.contains(player)){
				if(block.getData() == 14){
					ItemStack flag = block.getDrops().toArray(new ItemStack[]{new ItemStack(35, 14)})[0];
					Inventory inv = player.getInventory();
					inv.clear();
					player.getInventory().addItem(flag);
					player.getInventory().setHelmet(flag);
					block.setType(Material.AIR);
					plugin.getServer().broadcastMessage(ChatColor.BLUE + player.getDisplayName() + ChatColor.WHITE + " has the " + ChatColor.DARK_RED + "red" + ChatColor.WHITE + " flag!");
				}
				else if(block.getData() != 14) {
					e.setCancelled(true);
				}
			}
			if(CTF.RedPlayers.contains(player)){
				if(block.getData() == 11){
					ItemStack flag = block.getDrops().toArray(new ItemStack[]{new ItemStack(35, 11)})[0];
					Inventory inv = player.getInventory();
					inv.clear();
					player.getInventory().addItem(flag);
					player.getInventory().setHelmet(flag);
					block.setType(Material.AIR);
					plugin.getServer().broadcastMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.WHITE + " has the " + ChatColor.BLUE + "blue" + ChatColor.WHITE + " flag!");
				}
				else if(block.getData() == 14) {
					e.setCancelled(true);
				}
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
