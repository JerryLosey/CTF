package me.jimbo.plugin.util;

import java.sql.SQLException;

import couk.Adamki11s.SQL.SyncSQL;

import me.jimbo.plugin.CTF;

public class DatabaseLogging {
	
	private CTF plugin;
	
	public DatabaseLogging (CTF plugin) {
		this.plugin = plugin;
	}
	
	public void Send(){
		plugin.getServer().broadcastMessage("1");
		SyncSQL sql = new SyncSQL("li.silentnoobs.com", "ctf", "myuser", "mypass");
		plugin.getServer().broadcastMessage("2");
		sql.initialise();
		plugin.getServer().broadcastMessage("3");
		try {
			plugin.getServer().broadcastMessage("4");
			sql.standardQuery("UPDATE ctf.stats SET deaths = 55 WHERE playername='teh_jombi';");
			plugin.getServer().broadcastMessage("5");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// ### addKills ### //
	// onPlayerDeathEvent
	// Player player = event.getKiller();
	// CTF.addKills.put(player, CTF.addKills.get(Player)+1);
	
	// ### addDeaths ### //
	// onPlayerDeathEvent
	// Player player = event.getEntity();
	// CTF.addDeaths.put(player, CTF.addKills.get(Player)+1);
	
	// ### addWin ### //
	// onRoundEnd 
	// if(BlueTeam == winner)
	// {
	//	CTF.addWin.put(BlueTeam.getPlayer(), 1); // Would be much different.  Need to loop through the ArrayList of the team.
	// }
	
	// ### addLoss ### //
	// onRoundEnd 
	// if(BlueTeam != winner)
	// {
	//	CTF.addLoss.put(BlueTeam.getPlayer(), 1); // Would be much different.  Need to loop through the ArrayList of the team.
	// }
	
	// ### timePlayed ### //
	// onPlayerQuitEvent 
	// Player player = event.getPlayer();
	// int time = MainTimer.getTime();
	// CTF.timePlayed.put(player, time); // Would be simply adding the totaly # of seconds played.  This is fine for the database, just remember to parse when retrieving.
	
}
