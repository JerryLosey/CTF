package me.jimbo.plugin;

import java.util.List;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
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

		}
		if(item.getDurability() == 11){
			
		}
	}
	
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		List<Entity> entity = event.getPlayer().getNearbyEntities(15, 15, 15);
		for(Entity i : entity){
			if(i instanceof Player){
				Location location = player.getLocation();
				((Player) i).playEffect(location, Effect.SMOKE, Material.WOOL.getId());
			}
		}
	}
}
