package me.jimbo.plugin.threads;

import org.bukkit.entity.Player;

import me.jimbo.plugin.CTF;

public class PotionEffectTimer implements Runnable {
	
	private CTF plugin;

	public PotionEffectTimer(CTF plugin){
		this.plugin = plugin;
	}

	@SuppressWarnings("unused")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (Player p : this.plugin.getServer().getOnlinePlayers()){
			// Do something
		}
		
	}

}
