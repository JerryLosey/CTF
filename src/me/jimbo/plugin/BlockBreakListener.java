package me.jimbo.plugin;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {

	@SuppressWarnings("unused")
	private CTF plugin;
	public UUID hoverItem;
	
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
		if((CTF.RedPlayers.contains(player) && block.getData() == 11) || (CTF.AllPlayers.contains(player) && block.getData() == 14))
		{
			ItemStack flag = (ItemStack) block.getDrops();
			Item hover = player.getWorld().dropItem(player.getLocation(), flag);
			hoverItem = hover.getUniqueId();
			player.setPassenger(hover);
			player.getInventory().addItem(flag);
			block.setType(Material.AIR);
		}
	}
	
	@EventHandler
	public void onLeavesDecay(LeavesDecayEvent e)
	{
		e.setCancelled(true);
	}
}
