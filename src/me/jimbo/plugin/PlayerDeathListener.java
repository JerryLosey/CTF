package me.jimbo.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

	@SuppressWarnings("unused")
	private CTF plugin;
	
	public PlayerDeathListener (CTF plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDeath(PlayerDeathEvent e)
	{
		e.getDrops().clear();
		if(e.getEntity() instanceof Player) {
			Player player = e.getEntity();
			player.getPlayer().getInventory().clear();
			e.setDeathMessage(null);
		}
		// Do nothing
	}
}
