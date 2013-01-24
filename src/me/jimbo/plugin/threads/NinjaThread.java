package me.jimbo.plugin.threads;

import me.jimbo.plugin.CTF;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NinjaThread implements Runnable {
	
	private CTF plugin;

	public NinjaThread(CTF plugin){
		this.plugin = plugin;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	    for (Player p : this.plugin.getServer().getOnlinePlayers())
	    	if((p.getInventory().contains(331)) && (p.getItemInHand().getTypeId() == 331) && (p.hasPermission("ctf.class.ninja"))){
	    		p.getInventory().removeItem(new ItemStack[] { new ItemStack(331,1)});
	    		togglev(p, false);
	    	} else {
	    		togglev(p, true);
	    	}
	}
	
	 private void togglev(Player p, boolean visible)
	  {
		 for(Player players : Bukkit.getOnlinePlayers()){
			 if(!visible){
				 players.hidePlayer(p);
			 }else{
				 players.showPlayer(p);
			 }
		 }
	  }

}
