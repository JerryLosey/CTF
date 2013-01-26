package me.jimbo.plugin.threads;

import org.bukkit.ChatColor;

import me.jimbo.plugin.CTF;

public class MainTimer implements Runnable {

	private CTF plugin;
	public int x = 0;
	
	public MainTimer(CTF pl){
		this.plugin = pl;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// This is where we do whatever during the timer
		//plugin.getServer().broadcastMessage("Test!");
		x++;

		int y = x/60; // # of minutes
		int z = x % 60; // # of seconds
		
		if(y==2){
			if(z==30){
				plugin.getServer().broadcastMessage(ChatColor.GREEN + "You have "+ ChatColor.GOLD + y + ":"+ z + ChatColor.GREEN+" minutes before the round ends!");
			}
		}else if(y==4){
			if(z==0){
				plugin.getServer().broadcastMessage(ChatColor.GREEN + "You have "+ ChatColor.GOLD + "1:00" + ChatColor.GREEN+" minutes before the round ends!");
			}
		}
		
		// This is assuming a 5 minute round length
//		if(y == 4){
//			plugin.getServer().broadcastMessage("Round ends in 1 minute!");
//			if(z == 30){
//				plugin.getServer().broadcastMessage("Round ends in 30 seconds!");
//			}else if(z == 45){
//				plugin.getServer().broadcastMessage("Round ends in 15 seconds!");
//			}else if(z == 55){
//				plugin.getServer().broadcastMessage("Round ends in 5 seconds!");
//			}
//		}
	}
}
