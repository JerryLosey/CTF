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
		x++;
	}
}
