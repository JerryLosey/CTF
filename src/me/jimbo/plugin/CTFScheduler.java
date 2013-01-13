package me.jimbo.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CTFScheduler {

	private CTF plugin;
	
	public CTFScheduler (CTF plugin) {
		this.plugin = plugin;
	}
	
	public void startTimer() {
		final int timer = 35;
		final int task = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				plugin.getServer().broadcastMessage("");
				plugin.getServer().broadcastMessage("");
				plugin.getServer().broadcastMessage("");
				plugin.getServer().broadcastMessage("");
				plugin.getServer().broadcastMessage("");
				plugin.getServer().broadcastMessage("");
				plugin.getServer().broadcastMessage("");
				plugin.getServer().broadcastMessage("");
				plugin.getServer().broadcastMessage("");
				plugin.getServer().broadcastMessage("");
				plugin.getServer().broadcastMessage("");
					plugin.getServer().broadcastMessage(ChatColor.GREEN +"##################################################");
					plugin.getServer().broadcastMessage("Basic classes available for all players:");
					plugin.getServer().broadcastMessage(ChatColor.GRAY + "/soldier, /archer, /heavy");
					plugin.getServer().broadcastMessage(ChatColor.BLUE + "Contributor classes available at:" + ChatColor.BOLD + " www.silentnoobs.com");
					plugin.getServer().broadcastMessage("Round Starts in " + timer + " seconds.");
					plugin.getServer().broadcastMessage(ChatColor.GREEN +"##################################################");
					plugin.getServer().broadcastMessage("");
					plugin.getServer().broadcastMessage("");
					timer = timer - 1;
					if(timer == 0 && (CTF.AllPlayers.size() < plugin.getConfig().getInt("Minimum Players"))){
						plugin.getServer().getScheduler().cancelAllTasks();
						plugin.getServer().getScheduler().cancelTask(task);
						for(Player p : Bukkit.getServer().getOnlinePlayers()){
							if(!p.isOp()){
								p.kickPlayer(ChatColor.RED + "Not Enough Players! Restarting Server.");
							}
						}
						startTimer();
					}else if(timer == -1){
						//Round Starts!
						plugin.getServer().getScheduler().cancelAllTasks();
						plugin.inProgress = true;
						plugin.canAttack = true;
						plugin.splitTeams();
					}
			}
		}, 0L, 20L);
	}

}
