package me.jimbo.plugin.listeners;

import me.jimbo.plugin.CTF;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerPickupItemListener implements Listener {

	@SuppressWarnings("unused")
	private CTF plugin;
	
	public PlayerPickupItemListener (CTF plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("unused")
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e)
	{
		Player player = e.getPlayer();
		//ItemStack item = new ItemStack(e.getItem().getItemStack().getType(), 1);
		Item drop = e.getItem();
		ItemStack item = drop.getItemStack();
		Inventory pInv = player.getInventory();
		
		
		//14 is red, 11 is blue
		if(item.getDurability() == 14){

			if(!player.isOp()){
			e.setCancelled(true);
			}
		}
		if(item.getDurability() == 11){

			if(!player.isOp()){
			e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		
	}
}
