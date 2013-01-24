package me.jimbo.plugin.threads;

import me.jimbo.plugin.CTF;

public class MainTimer implements Runnable {

	@SuppressWarnings("unused")
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
//		x++;
//		plugin.getServer().broadcastMessage("" + x);
//		double y = x/60; // # of minutes
//		double z = x % 60; // # of seconds
//		//plugin.getServer().broadcastMessage("" + x + "/" + y + "/" + z);
//		// This is assuming a 5 minute round length
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
