package me.jimbo.plugin.threads;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

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
		x++;

		int y = x/60; // # of minutes
		int z = x % 60; // # of seconds
		
		if(y==2){
			if(z==30){
				plugin.getServer().broadcastMessage(ChatColor.GREEN + "You have "+ ChatColor.GOLD + y + ":"+ z + ChatColor.GREEN+" minutes before the round ends!");
			}
		}else if(y==4){
			if(z==0){
				plugin.getServer().broadcastMessage(ChatColor.GREEN + "You have "+ ChatColor.GOLD + "1:00" + ChatColor.GREEN+" minute before the round ends!");
			}
		}else if(y == 5){
			plugin.getServer().broadcastMessage(ChatColor.GREEN + "Round is over!");
			if(z==1){
				plugin.getServer().broadcastMessage(ChatColor.GREEN + "Server is restarting.  Please rejoin!");
			}
			if(z==4){
				if(plugin.redScore>plugin.blueScore){ //Red > Blue
					for(Player player : Bukkit.getOnlinePlayers()){
						if(CTF.RedPlayers.contains(player)){
							CTF.addWin.put(player, 1);
						}else if(CTF.AllPlayers.contains(player)){
							CTF.addLoss.put(player, 1);
						}
					}
				}else if(plugin.blueScore>plugin.redScore){
					for(Player player : Bukkit.getOnlinePlayers()){
						if(CTF.AllPlayers.contains(player)){
							CTF.addWin.put(player, 1);
						}else if(CTF.RedPlayers.contains(player)){
							CTF.addLoss.put(player, 1);
						}
					}
				}
				plugin.pushSQL();
				plugin.onRestart();
			}
		}
		
		// Cake listener

	}
	
	public int getX(){
		return x;
		
	}
}
