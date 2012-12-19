package me.jimbo.plugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.LeavesDecayEvent;

public class BlockBreakListener implements Listener {

	@SuppressWarnings("unused")
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
		Block brokenBlock = getBlock(e);
		Player player = e.getPlayer();
		//brokenBlock.getData() == 11 is blue
		//brokenBlock.getData() == 14 is red
		
		if (brokenBlock.getTypeId() != 35 && brokenBlock.getData() != 14 && brokenBlock.getData() != 11 && !player.isOp()) {
			
			e.setCancelled(true);
			brokenBlock.setType(Material.AIR);
		}
	}
	
	@EventHandler
	public void onLeavesDecay(LeavesDecayEvent e)
	{
		e.setCancelled(true);
	}
}
