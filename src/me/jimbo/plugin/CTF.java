package me.jimbo.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CTF extends JavaPlugin {
	
	public final static ArrayList<Player> RedPlayers = new ArrayList<Player>();
	public final static ArrayList<Player> AllPlayers = new ArrayList<Player>();
	
	public final static HashMap<Player, String> PlayerClasses = new HashMap<Player,String>();

	public final BlockBreakListener BlockBreakListener = new BlockBreakListener(this);
	public final PlayerDeathListener PlayerDeathListener = new PlayerDeathListener(this);
	public final PlayerRespawnListener PlayerRespawnListener = new PlayerRespawnListener(this);
	public final PlayerPickupItemListener PlayerPickupItemListener = new PlayerPickupItemListener(this);
	public final PlayerJoinListener PlayerJoinListener = new PlayerJoinListener(this);
	public final PlayerKickListener PlayerKickListener = new PlayerKickListener(this);
	public final EntitySpawnListener EntitySpawnListener = new EntitySpawnListener(this);
	public final BlockPlaceListener BlockPlaceListener = new BlockPlaceListener(this);
	public final PlayerDropItemListener PlayerDropItemListener = new PlayerDropItemListener(this);
	public final PlayerDamageListener PlayerDamageListener = new PlayerDamageListener(this);

	FileConfiguration config;
	public int redX;
	public int redY;
	public int redZ;
	public int blueX;
	public int blueY;
	public int blueZ;
	public int kills;
	
	public int redScore;
	public int blueScore;
	
	public int timer;
	
	public boolean inProgress;
	public boolean canAttack = false;

	Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(BlockBreakListener, this);
		getServer().getPluginManager().registerEvents(PlayerDeathListener, this);
		getServer().getPluginManager().registerEvents(PlayerRespawnListener, this);
		getServer().getPluginManager().registerEvents(PlayerPickupItemListener, this);
		getServer().getPluginManager().registerEvents(PlayerJoinListener, this);
		getServer().getPluginManager().registerEvents(PlayerKickListener, this);		
		getServer().getPluginManager().registerEvents(EntitySpawnListener, this);			
		getServer().getPluginManager().registerEvents(BlockPlaceListener, this);			
		getServer().getPluginManager().registerEvents(PlayerDropItemListener, this);	
		getServer().getPluginManager().registerEvents(PlayerDamageListener, this);

		inProgress = false;
		canAttack = false;
		getCommand("CTF").setExecutor(new PlayerCommands(this));
		getCommand("heavy").setExecutor(new PlayerCommands(this));
		getCommand("soldier").setExecutor(new PlayerCommands(this));
		getCommand("archer").setExecutor(new PlayerCommands(this));
		getCommand("medic").setExecutor(new PlayerCommands(this));
		getCommand("pyro").setExecutor(new PlayerCommands(this));
		getCommand("ninja").setExecutor(new PlayerCommands(this));
		getCommand("engineer").setExecutor(new PlayerCommands(this));
		
		String pluginFolder = getDataFolder().getAbsolutePath();
        new File(pluginFolder).mkdirs();
        
       
        
        if(!pluginFolder.contains("config.yml"))
        {
        	getConfig().options().copyDefaults(true);
        	getConfig();
        	saveDefaultConfig();
        }
        
		startTimer();
	}
	
	public void onDisable() {
		
	}
	
	@SuppressWarnings("deprecation")
	public void startTimer() {
		timer = 35;
		this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
					getServer().broadcastMessage("");
					getServer().broadcastMessage("");
					getServer().broadcastMessage("");
					getServer().broadcastMessage("");
					getServer().broadcastMessage("");
					getServer().broadcastMessage("");
					getServer().broadcastMessage("");
					getServer().broadcastMessage("");
					getServer().broadcastMessage("");
					getServer().broadcastMessage("");
					getServer().broadcastMessage("");
					getServer().broadcastMessage(ChatColor.GREEN +"##################################################");
					getServer().broadcastMessage("Basic classes available for all players:");
					getServer().broadcastMessage(ChatColor.GRAY + "/knight, /archer, /wizard");
					getServer().broadcastMessage(ChatColor.BLUE + "Contributor classes available at:" + ChatColor.BOLD + " www.silentnoobs.com");
					getServer().broadcastMessage("Round Starts in " + timer + " seconds.");
					getServer().broadcastMessage(ChatColor.GREEN +"##################################################");
					getServer().broadcastMessage("");
					getServer().broadcastMessage("");
					timer = timer - 1;
					if(timer == 0 && (AllPlayers.size() < getConfig().getInt("Minimum Players"))){
						getServer().getScheduler().cancelAllTasks();
						for(Player p : Bukkit.getServer().getOnlinePlayers()){
							if(!p.isOp()){
								p.kickPlayer(ChatColor.RED + "Not Enough Players! Restarting Server.");
							}
						}
						startTimer();
					}else if(timer == -1){
						getServer().getScheduler().cancelAllTasks();
						getServer().broadcastMessage("");
						getServer().broadcastMessage("");
						getServer().broadcastMessage("");
						getServer().broadcastMessage("");
						getServer().broadcastMessage("");
						getServer().broadcastMessage("");
						getServer().broadcastMessage("");
						getServer().broadcastMessage("");
						getServer().broadcastMessage("");
						getServer().broadcastMessage("");
						getServer().broadcastMessage("");
						getServer().broadcastMessage(ChatColor.RED +"##################################################");
						getServer().broadcastMessage("Basic classes available for all players:");
						getServer().broadcastMessage(ChatColor.GRAY + "/knight, /archer, /wizard");
						getServer().broadcastMessage(ChatColor.BLUE + "Contributor classes available at:" + ChatColor.BOLD + " www.silentnoobs.com");
						getServer().broadcastMessage("Round Has Started!");
						getServer().broadcastMessage(ChatColor.RED +"##################################################");
						getServer().broadcastMessage("");
						getServer().broadcastMessage("");
						inProgress = true;
						canAttack = true;
						splitTeams();
					}
			}
		}, 0L, 20L);
	}
	
	public void setScore(int team){
		// 1 is red
		// 2 is blue
		
		if(team == 1){
			redScore++;
		} else if(team == 2){
			blueScore++;
		}
	}
	
	public int getScore(int team){
		if(team ==1){
			return redScore;
		} else if(team == 2){
			return blueScore;
		}
		return 0;
	}
	
	public void splitTeams(){
		Random r = new Random();
		int moving = AllPlayers.size()/2;
		for(int i=0;i<moving;i++){
			int x = r.nextInt(AllPlayers.size());
			Player player = AllPlayers.get(x);
			RedPlayers.add(player);
			AllPlayers.remove(player);
		}
	}
	
}
