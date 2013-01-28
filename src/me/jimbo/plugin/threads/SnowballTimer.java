package me.jimbo.plugin.threads;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;

import me.jimbo.plugin.CTF;

public class SnowballTimer implements Runnable {

	private CTF plugin;
	
	public SnowballTimer(CTF plugin){
		this.plugin = plugin;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ItemStack snow = new ItemStack(Material.SNOW_BALL, 1);
		for (Player p : this.plugin.getServer().getOnlinePlayers()){
			if(CTF.PlayerClasses.get(p.getPlayer()).equalsIgnoreCase("gunner")){
				if(p.getInventory().getItemInHand().getTypeId() == 280){
					if(p.getInventory().contains(snow)){
						Snowball snowball =  p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
						snowball.setShooter(p);
						snowball.setVelocity(p.getLocation().getDirection().multiply(1.5));
						p.getInventory().removeItem(snow);		
					}
				}
			}
		}
	}

}
