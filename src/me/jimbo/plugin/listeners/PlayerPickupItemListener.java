package me.jimbo.plugin.listeners;

import java.util.HashMap;

import me.jimbo.plugin.CTF;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class PlayerPickupItemListener implements Listener {

	private HashMap<String, String> shotedplayer = new HashMap<String, String>();
	@SuppressWarnings("unused")
	private CTF plugin;
	public double hboost = 4.0D;
	public double vboost = 4.0D;
	
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
		Player p = event.getPlayer();
		if(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SPONGE){
			shoot(p);
		}
	}
	
	private void shoot(Player p){
		Block sponge = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
		Block underSponge = sponge.getRelative(BlockFace.DOWN);
		double h = 0.0D;
		double v = 0.0D;
		int max_p = 15;
		int max = 0;
		String dir = "up";
		Block db = underSponge;
		
		if ((sponge.getRelative(BlockFace.NORTH).getType() == Material.SPONGE) && 
			      (underSponge.getRelative(BlockFace.NORTH).getType() == Material.SPONGE)) {
			      dir = "up";
			    } else if ((sponge.getRelative(BlockFace.NORTH_EAST).getType() == Material.SPONGE) && 
			      (underSponge.getRelative(BlockFace.NORTH_EAST).getType() == Material.SPONGE)) {
			      dir = "up";
			    } else if ((sponge.getRelative(BlockFace.EAST).getType() == Material.SPONGE) && 
			      (underSponge.getRelative(BlockFace.EAST).getType() == Material.SPONGE)) {
			      dir = "up";
			    } else if ((sponge.getRelative(BlockFace.SOUTH_EAST).getType() == Material.SPONGE) && 
			      (underSponge.getRelative(BlockFace.SOUTH_EAST).getType() == Material.SPONGE)) {
			      dir = "up";
			    } else if ((sponge.getRelative(BlockFace.SOUTH).getType() == Material.SPONGE) && 
			      (underSponge.getRelative(BlockFace.SOUTH).getType() == Material.SPONGE)) {
			      dir = "up";
			    } else if ((sponge.getRelative(BlockFace.SOUTH_WEST).getType() == Material.SPONGE) && 
			      (underSponge.getRelative(BlockFace.SOUTH_WEST).getType() == Material.SPONGE)) {
			      dir = "up";
			    } else if ((sponge.getRelative(BlockFace.WEST).getType() == Material.SPONGE) && 
			      (underSponge.getRelative(BlockFace.WEST).getType() == Material.SPONGE)) {
			      dir = "up";
			    } else if ((sponge.getRelative(BlockFace.NORTH_WEST).getType() == Material.SPONGE) && 
			      (underSponge.getRelative(BlockFace.NORTH_WEST).getType() == Material.SPONGE)) {
			      dir = "up";
			    }
			    else if (underSponge.getRelative(BlockFace.NORTH).getType() == Material.SPONGE) {
			      dir = "NORTH";
			      db = underSponge.getRelative(BlockFace.NORTH);
			    } else if (underSponge.getRelative(BlockFace.EAST).getType() == Material.SPONGE) {
			      dir = "EAST";
			      db = underSponge.getRelative(BlockFace.EAST);
			    } else if (underSponge.getRelative(BlockFace.SOUTH).getType() == Material.SPONGE) {
			      dir = "SOUTH";
			      db = underSponge.getRelative(BlockFace.SOUTH);
			    } else if (underSponge.getRelative(BlockFace.WEST).getType() == Material.SPONGE) {
			      dir = "WEST";
			      db = underSponge.getRelative(BlockFace.WEST);
			    } else if (underSponge.getRelative(BlockFace.NORTH_EAST).getType() == Material.SPONGE) {
			      dir = "NORTH_EAST";
			      db = underSponge.getRelative(BlockFace.NORTH_EAST);
			    } else if (underSponge.getRelative(BlockFace.SOUTH_EAST).getType() == Material.SPONGE) {
			      dir = "SOUTH_EAST";
			      db = underSponge.getRelative(BlockFace.SOUTH_EAST);
			    } else if (underSponge.getRelative(BlockFace.SOUTH_WEST).getType() == Material.SPONGE) {
			      dir = "SOUTH_WEST";
			      db = underSponge.getRelative(BlockFace.SOUTH_WEST);
			    } else if (underSponge.getRelative(BlockFace.NORTH_WEST).getType() == Material.SPONGE) {
			      dir = "NORTH_WEST";
			      db = underSponge.getRelative(BlockFace.NORTH_WEST);
			    }
		if (dir.equalsIgnoreCase("up")) {
		      Block tmp = underSponge;
		      max = max_p;
		      while (max > 0) {
		        tmp = tmp.getRelative(BlockFace.DOWN);
		        if (tmp.getType() == Material.SPONGE) {
		          v += vboost;
		          max--;
		        } else if (max == max_p) {
		          v = vboost;
		          max = -9;
		        } else {
		          max = -99;
		        }
		      }
		      p.setVelocity(new Vector(0.0D, v, 0.0D));
		      this.shotedplayer.put(p.getName(), "up");
		    }
		else {
		      Block tmp = db;
		      max = max_p;
		      while (max > 0) {
		        tmp = tmp.getRelative(BlockFace.DOWN);
		        if (tmp.getType() == Material.SPONGE) {
		          h += hboost;
		          max--;
		        } else {
		          max = -99;
		        }
		      }

		      tmp = underSponge;
		      max = max_p;
		      while (max > 0) {
		        tmp = tmp.getRelative(BlockFace.DOWN);
		        if (tmp.getType() == Material.SPONGE) {
		          v += vboost;
		          max--;
		        } else if (max == max_p) {
		          v = vboost;
		          max = -9;
		        } else {
		          max = -99;
		        }
		      }
		      double x = 0.0D;
		      double z = 0.0D;

		      if ((h < 0.1D) && (hboost > 0.1D))
		        h = hboost / 2.0D;
		      else if (h < 0.1D) {
		        h = 2.0D;
		      }

		      if (dir.equalsIgnoreCase("West")) {
		        x = h;
		      }
		      else if (dir.equalsIgnoreCase("NORTH")) {
		        z = h;
		      }
		      else if (dir.equalsIgnoreCase("EAST")) {
		        x = -h;
		      }
		      else if (dir.equalsIgnoreCase("SOUTH")) {
		        z = -h;
		      }
		      p.setVelocity(new Vector(x, v, z).multiply(1.1D));
		      this.shotedplayer.put(p.getName(), "---");
		    }
		  }

		  @SuppressWarnings("unused")
		private boolean checkotherdirectionsforSponge(Block b, BlockFace bf)
		  {
		    if ((bf != BlockFace.NORTH) && 
		      (b.getRelative(BlockFace.NORTH).getType() == Material.SPONGE))
		      return true;
		    if ((bf != BlockFace.EAST) && 
		      (b.getRelative(BlockFace.EAST).getType() == Material.SPONGE))
		      return true;
		    if ((bf != BlockFace.SOUTH) && 
		      (b.getRelative(BlockFace.SOUTH).getType() == Material.SPONGE)) {
		      return true;
		    }

		    return (bf != BlockFace.WEST) && 
		      (b.getRelative(BlockFace.WEST).getType() == Material.SPONGE);
		  }
	}
