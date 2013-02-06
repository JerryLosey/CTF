package me.jimbo.plugin;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.logging.Logger;

import me.jimbo.plugin.listeners.ArmorRemovalListener;
import me.jimbo.plugin.listeners.BlockBreakListener;
import me.jimbo.plugin.listeners.BlockPlaceListener;
import me.jimbo.plugin.listeners.PlayerClickListener;
import me.jimbo.plugin.listeners.InventoryOpenListener;
import me.jimbo.plugin.listeners.PlayerClickListener;
import me.jimbo.plugin.listeners.PlayerDamageListener;
import me.jimbo.plugin.listeners.PlayerDeathListener;
import me.jimbo.plugin.listeners.PlayerDropItemListener;
import me.jimbo.plugin.listeners.PlayerJoinListener;
import me.jimbo.plugin.listeners.PlayerKickListener;
import me.jimbo.plugin.listeners.PlayerPickupItemListener;
import me.jimbo.plugin.listeners.PlayerRespawnListener;
import me.jimbo.plugin.listeners.PingListener;
import me.jimbo.plugin.listeners.TagAPIListener;
import me.jimbo.plugin.listeners.WeatherChange;
import me.jimbo.plugin.threads.MainTimer;
import me.jimbo.plugin.threads.NinjaThread;
import me.jimbo.plugin.threads.PotionEffectTimer;
import me.jimbo.plugin.threads.SnowballTimer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import couk.Adamki11s.SQL.SyncSQL;

@SuppressWarnings("unused")
public class CTF extends JavaPlugin {
	
	public final static ArrayList<Player> RedPlayers = new ArrayList<Player>();
	public final static ArrayList<Player> AllPlayers = new ArrayList<Player>();
	
	public final static HashMap<Player, String> PlayerClasses = new HashMap<Player,String>();

	public final static HashMap<Player,Integer> addKills = new HashMap<Player,Integer>();
	public final static HashMap<Player,Integer> addDeaths = new HashMap<Player,Integer>();
	public final static HashMap<Player,Integer> addWin = new HashMap<Player,Integer>();
	public final static HashMap<Player,Integer> addLoss = new HashMap<Player,Integer>();
	public final static HashMap<Player,Long> timePlayed = new HashMap<Player,Long>();
	public final static HashMap<Player,Integer> addCapture = new HashMap<Player,Integer>();

	public final BlockBreakListener BlockBreakListener = new BlockBreakListener(this);
	public final PlayerDeathListener PlayerDeathListener = new PlayerDeathListener(this);
	public final PlayerRespawnListener PlayerRespawnListener = new PlayerRespawnListener(this);
	public final PlayerPickupItemListener PlayerPickupItemListener = new PlayerPickupItemListener(this);
	public final PlayerJoinListener PlayerJoinListener = new PlayerJoinListener(this);
	public final PlayerKickListener PlayerKickListener = new PlayerKickListener(this);
	public final BlockPlaceListener BlockPlaceListener = new BlockPlaceListener(this);
	public final PlayerDropItemListener PlayerDropItemListener = new PlayerDropItemListener(this);
	public final PlayerDamageListener PlayerDamageListener = new PlayerDamageListener(this);
	public final InventoryOpenListener InventoryOpenListener = new InventoryOpenListener(this);
	public final ArmorRemovalListener ArmorRemovalListener = new ArmorRemovalListener(this);
	public final PlayerClickListener EatingListener = new PlayerClickListener(this);
	public final PlayerClickListener PlayerClickListener = new PlayerClickListener(this);
	public final PingListener PingListener = new PingListener(this);
	public final TagAPIListener TagAPIListener = new TagAPIListener(this);
	public final WeatherChange WeatherChange = new WeatherChange(this);
	
	ItemStack dhelmet = new ItemStack(Material.DIAMOND_HELMET);
	ItemStack dchestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
	ItemStack dleggings = new ItemStack(Material.DIAMOND_LEGGINGS);
	ItemStack dboots = new ItemStack(Material.DIAMOND_BOOTS);
	ItemStack dsword = new ItemStack(Material.DIAMOND_SWORD);
	ItemStack steak = new ItemStack(Material.COOKED_BEEF);
	ItemStack ihelmet = new ItemStack(Material.IRON_HELMET);
	ItemStack ichestplate = new ItemStack(Material.IRON_CHESTPLATE);
	ItemStack ileggings = new ItemStack(Material.IRON_LEGGINGS);
	ItemStack iboots = new ItemStack(Material.IRON_BOOTS);
	ItemStack isword = new ItemStack(Material.IRON_SWORD);
	ItemStack pearl = new ItemStack(Material.ENDER_PEARL);
	ItemStack ghelmet = new ItemStack(Material.GOLD_HELMET);
	ItemStack gchestplate = new ItemStack(Material.GOLD_CHESTPLATE);
	ItemStack gleggings = new ItemStack(Material.GOLD_LEGGINGS);
	ItemStack gboots = new ItemStack(Material.GOLD_BOOTS);
	ItemStack gsword = new ItemStack(Material.GOLD_SWORD);
	ItemStack web = new ItemStack(Material.WEB);
	ItemStack regeneration = new ItemStack(Material.POTION);
	ItemStack lhelmet = new ItemStack(Material.LEATHER_HELMET);
	ItemStack lchestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
	ItemStack lleggings = new ItemStack(Material.LEATHER_LEGGINGS);
	ItemStack lboots = new ItemStack(Material.LEATHER_BOOTS);
	ItemStack ssword = new ItemStack(Material.STONE_SWORD);
	ItemStack daxe = new ItemStack(Material.DIAMOND_AXE);
	ItemStack bow = new ItemStack(Material.BOW);
	ItemStack fsteel = new ItemStack(Material.FLINT_AND_STEEL);
	ItemStack arrow = new ItemStack(Material.ARROW);
	ItemStack chelmet = new ItemStack(Material.CHAINMAIL_HELMET);
	ItemStack cchestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
	ItemStack cleggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
	ItemStack cboots = new ItemStack(Material.CHAINMAIL_BOOTS);
	ItemStack ebow = new ItemStack(Material.BOW);
	ItemStack redstone = new ItemStack(Material.REDSTONE);
	ItemStack egg = new ItemStack(Material.EGG);
	ItemStack egsword = new ItemStack(Material.GOLD_SWORD);
	ItemStack spawnzombie = new ItemStack(Material.MOB_SPAWNER);
	ItemStack spawner = new ItemStack(52);
	ItemStack sugar = new ItemStack(Material.SUGAR);
	ItemStack coal = new ItemStack(Material.COAL);
	ItemStack wsword = new ItemStack(Material.WOOD_SWORD);
	ItemStack pixbow = new ItemStack(Material.BOW);
	ItemStack pixsword = new ItemStack(Material.DIAMOND_SWORD);
	ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE);
	ItemStack damagePot = new ItemStack(Material.POTION, 4, (short) 16428);
	ItemStack snowball = new ItemStack(Material.SNOW_BALL);
	ItemStack stick = new ItemStack(Material.STICK);
	
	FileConfiguration config;
	public int redX;
	public int redY;
	public int redZ;
	public int blueX;
	public int blueY;
	public int blueZ;
	public int kills;
	public int timer = 35;
	
	public int redScore;
	public int blueScore;
	
	public boolean inProgress = false;
	public boolean roundOver = false;
	
	public Player redFlagCarrier;
	public Player blueFlagCarrier;
	
	private int MTID;
	private int NINJ;
	private int EFFE;
	private int SNOW;

	Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(BlockBreakListener, this);
		getServer().getPluginManager().registerEvents(PlayerDeathListener, this);
		getServer().getPluginManager().registerEvents(PlayerRespawnListener, this);
		getServer().getPluginManager().registerEvents(PlayerPickupItemListener, this);
		getServer().getPluginManager().registerEvents(PlayerJoinListener, this);
		getServer().getPluginManager().registerEvents(PlayerKickListener, this);			
		getServer().getPluginManager().registerEvents(BlockPlaceListener, this);			
		getServer().getPluginManager().registerEvents(PlayerDropItemListener, this);	
		getServer().getPluginManager().registerEvents(PlayerDamageListener, this);	
		getServer().getPluginManager().registerEvents(InventoryOpenListener, this);
		getServer().getPluginManager().registerEvents(ArmorRemovalListener, this);
		getServer().getPluginManager().registerEvents(EatingListener, this);
		getServer().getPluginManager().registerEvents(PlayerClickListener, this);
		getServer().getPluginManager().registerEvents(PingListener, this);
		getServer().getPluginManager().registerEvents(TagAPIListener, this);
		getServer().getPluginManager().registerEvents(WeatherChange, this);
		

		inProgress = false;
		redScore = 0;
		blueScore = 0;
		getCommand("CTF").setExecutor(new PlayerCommands(this));
		getCommand("info").setExecutor(new PlayerCommands(this));
		getCommand("class").setExecutor(new PlayerCommands(this));
		getCommand("heavy").setExecutor(new PlayerCommands(this));
		getCommand("soldier").setExecutor(new PlayerCommands(this));
		getCommand("archer").setExecutor(new PlayerCommands(this));
		getCommand("medic").setExecutor(new PlayerCommands(this));
		getCommand("pyro").setExecutor(new PlayerCommands(this));
		getCommand("ninja").setExecutor(new PlayerCommands(this));
		getCommand("engineer").setExecutor(new PlayerCommands(this));
		getCommand("firefly").setExecutor(new PlayerCommands(this));
		getCommand("berserker").setExecutor(new PlayerCommands(this));
		getCommand("martyr").setExecutor(new PlayerCommands(this));
		getCommand("gunner").setExecutor(new PlayerCommands(this));
		
		String pluginFolder = getDataFolder().getAbsolutePath();
        new File(pluginFolder).mkdirs();
        
        //createTable();
        
        
        if(!pluginFolder.contains("config.yml"))
        {
        	getConfig().options().copyDefaults(true);
        	getConfig();
        	saveDefaultConfig();
        }
        
		startTimer(17);
	}
	public void onRestart(){
		addKills.clear();
		addDeaths.clear();
		addWin.clear();
		addLoss.clear();
		timePlayed.clear();
		inProgress = false;
		AllPlayers.clear();
		RedPlayers.clear();
		getServer().getScheduler().cancelTask(this.MTID);
		this.MTID = 0;
		getServer().getScheduler().cancelTask(this.NINJ);
		this.NINJ = 0;
		getServer().getScheduler().cancelTask(this.EFFE);
		this.EFFE = 0;
		getServer().getScheduler().cancelTask(this.SNOW);
		this.SNOW = 0;
		getServer().getScheduler().cancelAllTasks();
		blueScore = 0;
		redScore = 0;
		roundOver = true;
		resetFlag(1);
		resetFlag(2);
		for(Player p : getServer().getOnlinePlayers()){
			p.kickPlayer(ChatColor.YELLOW + "Round is restarting!  Rejoin in a few seconds :)");
		}
		startTimer(17);
	}
	
	public void onDisable() {
		addKills.clear();
		addDeaths.clear();
		addWin.clear();
		addLoss.clear();
		timePlayed.clear();
		getServer().getScheduler().cancelTask(this.MTID);
		this.MTID = 0;
		getServer().getScheduler().cancelTask(this.NINJ);
		this.NINJ = 0;
		getServer().getScheduler().cancelTask(this.EFFE);
		this.EFFE = 0;
		getServer().getScheduler().cancelTask(this.SNOW);
		this.SNOW = 0;
		blueScore = 0;
		redScore = 0;
		roundOver = true;
		resetFlag(1);
		resetFlag(2);
	}
	
	public void startTimer(int timer) {
		inProgress = false;
		final int t = timer;
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			int z = t;
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
					getServer().broadcastMessage(ChatColor.GRAY + "/soldier, /archer, /heavy");
					getServer().broadcastMessage(ChatColor.BLUE + "Contributor classes available at:" + ChatColor.BOLD + " www.silentnoobs.com");
					getServer().broadcastMessage("Round Starts in " + z + " seconds.");
					getServer().broadcastMessage(ChatColor.GREEN +"##################################################");
					getServer().broadcastMessage("");
					getServer().broadcastMessage("");
					z = z - 1;
					if(z==0 &&((AllPlayers.size() + RedPlayers.size()) <= getConfig().getInt("Minimum Players"))){
						getServer().getScheduler().cancelAllTasks();
						roundOver = true;
						onRestart();
					}else if(z == t-5){
						roundOver = false;
					}else if(z == -1){
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
						getServer().broadcastMessage(ChatColor.GRAY + "/soldier, /archer, /heavy");
						getServer().broadcastMessage(ChatColor.BLUE + "Contributor classes available at:" + ChatColor.BOLD + " www.silentnoobs.com");
						getServer().broadcastMessage("Round Has Started!");
						getServer().broadcastMessage(ChatColor.RED +"##################################################");
						getServer().broadcastMessage("");
						getServer().broadcastMessage("");
						inProgress = true;
						splitTeams();
						registerTimers();
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
	
	public boolean getDistanceToSpawn(Player player, int team){
		Location loc = player.getLocation();
		if(team == 1){
			Location spawn = new Location(Bukkit.getWorlds().get(0), (double) getConfig().getDouble("Spawns.Red.X"), (double) getConfig().getDouble("Spawns.Red.Y"), (double) getConfig().getDouble("Spawns.Red.Z"));
			if(loc.distanceSquared(spawn) <= 25){
				return true;
			}else{
				return false;
			}
		}else if(team == 2){
			Location spawn = new Location(Bukkit.getWorlds().get(0), (double) getConfig().getDouble("Spawns.Blue.X"), (double) getConfig().getDouble("Spawns.Blue.Y"), (double) getConfig().getDouble("Spawns.Blue.Z"));
			if(loc.distanceSquared(spawn) <= 25){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	public int getTeam(Player player){
		// 1 is red
		// 2 is blue
		if(RedPlayers.contains(player)){
			int x = 1;
			return x;
		}else if(AllPlayers.contains(player)){
			int x = 2;
			return x;
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
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			if(CTF.AllPlayers.contains(p)){
				Location location = new Location(Bukkit.getWorlds().get(0), (double) getConfig().getDouble("Spawns.Blue.X"), (double) getConfig().getDouble("Spawns.Blue.Y"), (double) getConfig().getDouble("Spawns.Blue.Z"));
				p.teleport(location);
				resetInv(p);
			} else if (CTF.RedPlayers.contains(p)) {
				Location location = new Location(Bukkit.getWorlds().get(0), (double) getConfig().getDouble("Spawns.Red.X"), (double) getConfig().getDouble("Spawns.Red.Y"), (double) getConfig().getDouble("Spawns.Red.Z"));
				p.teleport(location);
				resetInv(p);
			} else {
				Bukkit.broadcastMessage("An Error Occured! Notify an admin to check the logs!");
				getLogger().warning("Error On PlayerRespawn! Player hasn't been assigned a team!");
			}
		}
	}
	
	public void registerTimers(){
		this.MTID = getServer().getScheduler().scheduleSyncRepeatingTask(this, new MainTimer(this), 20L, 20L);
		getServer().broadcastMessage(ChatColor.GREEN + "You have "+ ChatColor.GOLD + "5 "+ ChatColor.GREEN+"minutes before the round ends!");
		this.NINJ = getServer().getScheduler().scheduleSyncRepeatingTask(this, new NinjaThread(this), 60L, 20L);
		this.EFFE = getServer().getScheduler().scheduleSyncRepeatingTask(this, new PotionEffectTimer(this), 60L, 20L);
		//this.SNOW = getServer().getScheduler().scheduleSyncRepeatingTask(this, new SnowballTimer(this), 20L, 4L);
	}
	
	public void pushSQL(){
		SyncSQL sql = new SyncSQL("li.silentnoobs.com", "ctf", "mcuser", "l33th@ck34");
		sql.initialise();
		try {
			for(Entry<Player,Integer> entry : addDeaths.entrySet()){
				Player player = entry.getKey();
				Integer deaths = entry.getValue();
				sql.standardQuery("INSERT INTO ctf.stats (playername, kills, deaths, captures, wins, losses, timeplayed) values (\""+player.getDisplayName().toString()+"\", 0, "+deaths+", 0, 0, 0, 0) ON DUPLICATE KEY UPDATE deaths = deaths + "+deaths+";");
			}
			for(Entry<Player,Integer> entry : addKills.entrySet()){
				Player player = entry.getKey();
				Integer kills = entry.getValue();
				sql.standardQuery("INSERT INTO ctf.stats (playername, kills, deaths, captures, wins, losses, timeplayed) values (\""+player.getDisplayName().toString()+"\", "+kills+", 0, 0, 0, 0, 0) ON DUPLICATE KEY UPDATE kills = kills + "+kills+";");
			}
			for(Entry<Player,Integer> entry : addWin.entrySet()){
				Player player = entry.getKey();
				Integer win = entry.getValue();
				sql.standardQuery("INSERT INTO ctf.stats (playername, kills, deaths, captures, wins, losses, timeplayed) values (\""+player.getDisplayName().toString()+"\", 0, 0, "+win+", 0, 0, 0) ON DUPLICATE KEY UPDATE wins = losses + "+win+";");
			}
			for(Entry<Player,Integer> entry : addLoss.entrySet()){
				Player player = entry.getKey();
				Integer loss = entry.getValue();
				sql.standardQuery("INSERT INTO ctf.stats (playername, kills, deaths, captures, wins, losses, timeplayed) values (\""+player.getDisplayName().toString()+"\", 0, 0, 0, 0, "+loss+", 0) ON DUPLICATE KEY UPDATE losses = losses + "+loss+";");
			}
			for(Entry<Player,Integer> entry : addCapture.entrySet()){
				Player player = entry.getKey();
				Integer capture = entry.getValue();
				sql.standardQuery("INSERT INTO ctf.stats (playername, kills, deaths, captures, wins, losses, timeplayed) values (\""+player.getDisplayName().toString()+"\", 0, 0, "+capture+", 0, 0, 0) ON DUPLICATE KEY UPDATE captures = captures + "+capture+";");
			}
			for(Entry<Player,Long> entry : timePlayed.entrySet()){
				Player player = entry.getKey();
				Long time = entry.getValue();
				sql.standardQuery("INSERT INTO ctf.stats (playername, kills, deaths, captures, wins, losses, timeplayed) values (\""+player.getDisplayName().toString()+"\", 0, 0, 0, 0, 0, " + timePlayed+ ") ON DUPLICATE KEY UPDATE timeplayed = timeplayed + "+timePlayed+";");
			}
			//Timeplayed
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void resetFlag(int flag){
		// 1 is red
		// 2 is blue
		
		if(flag == 1){
			redFlagCarrier = null;
			int redX = (int) getConfig().getDouble("Goals.Red.X");
			int redY = (int) getConfig().getDouble("Goals.Red.Y");
			int redZ = (int) getConfig().getDouble("Goals.Red.Z");
			Location loc = new Location(Bukkit.getWorlds().get(0), (double)redX,(double)redY,(double)redZ);
			Block b = loc.getBlock();
			b.setTypeId(35);
			b.setData((byte) 14);
		} else if(flag == 2){
			blueFlagCarrier = null;
			int blueX = (int) getConfig().getDouble("Goals.Blue.X");
			int blueY = (int) getConfig().getDouble("Goals.Blue.Y");
			int blueZ = (int) getConfig().getDouble("Goals.Blue.Z");
			Location loc = new Location(Bukkit.getWorlds().get(0), (double)blueX,(double)blueY,(double)blueZ);
			Block b = loc.getBlock();
			b.setTypeId(35);
			b.setData((byte) 11);
		}else{
			log.warning("Invalid flag on resetFlag()");
		}
	}
	
	public void resetInv(Player player){

		String classed = PlayerClasses.get(player).toString();
		PlayerInventory inv = player.getInventory();
		inv.clear();
		inv.setArmorContents(null);
		if(classed.equalsIgnoreCase("martyr")){
			for(PotionEffect effect : player.getActivePotionEffects()){
				player.removePotionEffect(effect.getType());
			}
		      inv.addItem(new ItemStack[] { this.fsteel });
		      inv.addItem(new ItemStack[] { this.damagePot });
		      inv.addItem(new ItemStack[] { this.damagePot });
		      inv.addItem(new ItemStack[] { this.damagePot });
		      inv.addItem(new ItemStack[] { this.damagePot });
		      inv.addItem(new ItemStack[] { this.damagePot });

		}
		if(classed.equalsIgnoreCase("gunner")){
			for(PotionEffect effect : player.getActivePotionEffects()){
				player.removePotionEffect(effect.getType());
			}
			this.ihelmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			this.lchestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			this.lleggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
			this.iboots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			inv.setHelmet(this.ihelmet);
			inv.setChestplate(this.lchestplate);
			inv.setLeggings(this.lleggings);
			inv.setBoots(this.iboots);
			this.ssword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		      inv.addItem(new ItemStack[] { this.ssword });
		      inv.addItem(new ItemStack[] { this.stick });
		      for(int h=0; h<200; h++){
			      inv.addItem(new ItemStack[] { this.snowball });
		      }

		}
		if(classed.equalsIgnoreCase("engineer")){
			for(PotionEffect effect : player.getActivePotionEffects()){
				player.removePotionEffect(effect.getType());
			}
			if (inv.getHelmet() == null) {
		        inv.setHelmet(this.lhelmet);
		      }
		      if (inv.getChestplate() == null) {
		        inv.setChestplate(this.gchestplate);
		      }
		      if (inv.getLeggings() == null) {
		        inv.setLeggings(this.gleggings);
		      }
		      if (inv.getBoots() == null) {
		        inv.setBoots(this.lboots);
		      }
		      inv.addItem(new ItemStack[] { this.wsword });

		      inv.addItem(new ItemStack[] { this.steak });
		      inv.addItem(new ItemStack[] { this.steak });
		      inv.addItem(new ItemStack[] { this.steak });
		      inv.addItem(new ItemStack[] { this.steak });

		      inv.addItem(new ItemStack[] { this.coal });
		      inv.addItem(new ItemStack[] { this.coal });
		      inv.addItem(new ItemStack[] { this.coal });
		      inv.addItem(new ItemStack[] { this.coal });
		      inv.addItem(new ItemStack[] { this.coal });
		      inv.addItem(new ItemStack[] { this.coal });
		      inv.addItem(new ItemStack[] { this.coal });
		}
		if(classed.equalsIgnoreCase("firefly")){
			for(PotionEffect effect : player.getActivePotionEffects()){
				player.removePotionEffect(effect.getType());
			}
			this.pixbow.addEnchantment(Enchantment.ARROW_FIRE, 1);
			this.pixbow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
			this.pixsword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
			
			inv.addItem(new ItemStack[] { this.pixbow });
			inv.addItem(new ItemStack[] { this.pixsword });
			
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			
			inv.addItem(new ItemStack[] { this.gapple });
			inv.addItem(new ItemStack[] { this.gapple });
			inv.addItem(new ItemStack[] { this.gapple });
			
			inv.addItem(new ItemStack[] { this.sugar });
			inv.addItem(new ItemStack[] { this.sugar });
			inv.addItem(new ItemStack[] { this.sugar });

			inv.addItem(new ItemStack[] { this.arrow });
		}
		if(classed.equalsIgnoreCase("berserker")){
			for(PotionEffect effect : player.getActivePotionEffects()){
				player.removePotionEffect(effect.getType());
			}
			this.dleggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
			this.dleggings.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
			inv.setLeggings(dleggings);
			
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });

		}
		if(classed.equalsIgnoreCase("ninja")){
			for(PotionEffect effect : player.getActivePotionEffects()){
				player.removePotionEffect(effect.getType());
			}
		    this.egsword.addEnchantment(Enchantment.DURABILITY, 3);
		    this.egsword.addEnchantment(Enchantment.DAMAGE_ALL, 3);
			inv.addItem(new ItemStack[] { this.egsword });

		    inv.addItem(new ItemStack[] { this.egg });
		    inv.addItem(new ItemStack[] { this.egg });
		    inv.addItem(new ItemStack[] { this.egg });
		    inv.addItem(new ItemStack[] { this.egg });
		    inv.addItem(new ItemStack[] { this.egg });
		    inv.addItem(new ItemStack[] { this.egg });
		    inv.addItem(new ItemStack[] { this.egg });
		    inv.addItem(new ItemStack[] { this.egg });
		    inv.addItem(new ItemStack[] { this.egg });
		    inv.addItem(new ItemStack[] { this.egg });

		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });
		    inv.addItem(new ItemStack[] { this.pearl });

		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });

		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });

		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });
		    inv.addItem(new ItemStack[] { this.redstone });

		    inv.addItem(new ItemStack[] { this.sugar });
		    inv.addItem(new ItemStack[] { this.sugar });
		    inv.addItem(new ItemStack[] { this.sugar });
		    inv.addItem(new ItemStack[] { this.sugar });
		    
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		}
		if(classed.equalsIgnoreCase("pyro")){
			for(PotionEffect effect : player.getActivePotionEffects()){
				player.removePotionEffect(effect.getType());
			}
			if(inv.getHelmet() == null){
				inv.setHelmet(this.ihelmet);
			}
			if(inv.getChestplate() == null){
				inv.setChestplate(this.ichestplate);
			}
			if(inv.getLeggings() == null){
				inv.setLeggings(this.ileggings);
			}
			if(inv.getBoots() == null){
				inv.setBoots(this.iboots);
			}
		    inv.addItem(new ItemStack[] { this.daxe });

		    inv.addItem(new ItemStack[] { this.fsteel });

		    inv.addItem(new ItemStack[] { this.bow });

		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });
		    inv.addItem(new ItemStack[] { this.arrow });

		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		}
		if(classed.equalsIgnoreCase("medic")){
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, 1), true);
			if(inv.getHelmet() == null){
				inv.setHelmet(this.ihelmet);
			}
			if(inv.getChestplate() == null){
				inv.setChestplate(this.ichestplate);
			}
			if(inv.getLeggings() == null){
				inv.setLeggings(this.ileggings);
			}
			if(inv.getBoots() == null){
				inv.setBoots(this.iboots);
			}
			inv.addItem(new ItemStack[] { this.gsword });
			
		    inv.addItem(new ItemStack[] { this.web });
		    inv.addItem(new ItemStack[] { this.web });
		    inv.addItem(new ItemStack[] { this.web });
		    inv.addItem(new ItemStack[] { this.web });
		    inv.addItem(new ItemStack[] { this.web });
		    inv.addItem(new ItemStack[] { this.web });
		    inv.addItem(new ItemStack[] { this.web });
		    inv.addItem(new ItemStack[] { this.web });
		    inv.addItem(new ItemStack[] { this.web });
		    inv.addItem(new ItemStack[] { this.web });

		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		}
		if(classed.equalsIgnoreCase("heavy")){
			for(PotionEffect effect : player.getActivePotionEffects()){
				player.removePotionEffect(effect.getType());
			}
			if(inv.getHelmet() == null){
				inv.setHelmet(this.dhelmet);
			}
			if(inv.getChestplate() == null){
				inv.setChestplate(this.dchestplate);
			}
			if(inv.getLeggings() == null){
				inv.setLeggings(this.dleggings);
			}
			if(inv.getBoots() == null){
				inv.setBoots(this.dboots);
				
				inv.addItem(new ItemStack[] { this.dsword });
				inv.addItem(new ItemStack[] { this.steak });
				inv.addItem(new ItemStack[] { this.steak });
				inv.addItem(new ItemStack[] { this.steak });
			}
		}
		if(classed.equalsIgnoreCase("archer")){
			for(PotionEffect effect : player.getActivePotionEffects()){
				player.removePotionEffect(effect.getType());
			}
			if(inv.getHelmet() == null){
				inv.setHelmet(this.chelmet);
			}
			if(inv.getChestplate() == null){
				inv.setChestplate(this.cchestplate);
			}
			if(inv.getLeggings() == null){
				inv.setLeggings(this.cleggings);
			}
			if(inv.getBoots() == null){
				inv.setBoots(this.cboots);
			}
			this.ebow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
			inv.addItem(new ItemStack[] { this.ebow });

	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });

	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });

	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });

	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });

	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });

	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });

	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });

	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });
	        inv.addItem(new ItemStack[] { this.arrow });

	        inv.addItem(new ItemStack[] { this.ssword });

	        inv.addItem(new ItemStack[] { this.steak });
	        inv.addItem(new ItemStack[] { this.steak });
	        inv.addItem(new ItemStack[] { this.steak });
	        inv.addItem(new ItemStack[] { this.steak });
		}
		if(classed.equalsIgnoreCase("soldier")){
			for(PotionEffect effect : player.getActivePotionEffects()){
				player.removePotionEffect(effect.getType());
			}
			if(inv.getHelmet() == null){
				inv.setHelmet(this.ihelmet);
			}
			if(inv.getChestplate() == null){
				inv.setChestplate(this.ichestplate);
			}
			if(inv.getLeggings() == null){
				inv.setLeggings(this.ileggings);
			}
			if(inv.getBoots() == null){
				inv.setBoots(this.iboots);
			}
			inv.addItem(new ItemStack[] { this.isword });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.steak });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
			inv.addItem(new ItemStack[] { this.pearl });
		}
	}
	
}
