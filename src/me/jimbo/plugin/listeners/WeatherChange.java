package me.jimbo.plugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import me.jimbo.plugin.CTF;

public class WeatherChange implements Listener {

	@SuppressWarnings("unused")
	private CTF plugin;
	
	public WeatherChange (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event){
		event.setCancelled(true);
	}
}
