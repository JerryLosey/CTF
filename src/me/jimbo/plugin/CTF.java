package me.jimbo.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import me.jimbo.plugin.listeners.ArmorRemovalListener;
import me.jimbo.plugin.listeners.BlockBreakListener;
import me.jimbo.plugin.listeners.BlockPlaceListener;
import me.jimbo.plugin.listeners.EatingListener;
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
	public final BlockPlaceListener BlockPlaceListener = new BlockPlaceListener(this);
	public final PlayerDropItemListener PlayerDropItemListener = new PlayerDropItemListener(this);
	public final PlayerDamageListener PlayerDamageListener = new PlayerDamageListener(this);
	public final InventoryOpenListener InventoryOpenListener = new InventoryOpenListener(this);
	public final ArmorRemovalListener ArmorRemovalListener = new ArmorRemovalListener(this);
	public final EatingListener EatingListener = new EatingListener(this);
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
	ItemStack flintandsteel = new ItemStack(Material.FLINT_AND_STEEL);
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
	
	public boolean inProgress;
	public boolean roundOver = false;
	
	public Player redFlagCarrier;
	public Player blueFlagCarrier;
	
	private int MTID;

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
		getCommand("heavy").setExecutor(new PlayerCommands(this));
		getCommand("soldier").setExecutor(new PlayerCommands(this));
		getCommand("archer").setExecutor(new PlayerCommands(this));
		getCommand("medic").setExecutor(new PlayerCommands(this));
		getCommand("pyro").setExecutor(new PlayerCommands(this));
		getCommand("ninja").setExecutor(new PlayerCommands(this));
		getCommand("engineer").setExecutor(new PlayerCommands(this));
		
		String pluginFolder = getDataFolder().getAbsolutePath();
        new File(pluginFolder).mkdirs();
        
        //createTable();
        
        
        if(!pluginFolder.contains("config.yml"))
        {
        	getConfig().options().copyDefaults(true);
        	getConfig();
        	saveDefaultConfig();
        }
        
		startTimer(37);
	}
	
	public void onDisable() {
		getServer().getScheduler().cancelTask(this.MTID);
		this.MTID = 0;
		blueScore = 0;
		redScore = 0;
		roundOver = true;
		resetFlag(1);
		resetFlag(2);
	}
	
	@SuppressWarnings("deprecation")
	public void startTimer(int timer) {
		inProgress = false;
		final int t = timer;
		this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
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
						startTimer(35);
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
		this.MTID = getServer().getScheduler().scheduleSyncRepeatingTask(this, new MainTimer(this), 60L, 20L);
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

		redFlagCarrier = null;
		blueFlagCarrier = null;
		String classed = PlayerClasses.get(player);
		PlayerInventory inv = player.getInventory();
		inv.clear();
		inv.setArmorContents(null);
		if(classed.equals("engineer")){
			player.removePotionEffect(PotionEffectType.REGENERATION);
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
		if(classed.equals("ninja")){
			player.removePotionEffect(PotionEffectType.REGENERATION);
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

		    inv.addItem(new ItemStack[] { this.sugar });
		    inv.addItem(new ItemStack[] { this.sugar });
		    inv.addItem(new ItemStack[] { this.sugar });
		    inv.addItem(new ItemStack[] { this.sugar });

		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		}
		if(classed.equals("pyro")){
			player.removePotionEffect(PotionEffectType.REGENERATION);
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

		    inv.addItem(new ItemStack[] { this.flintandsteel });

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
		if(classed.equals("medic")){
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 12000, 1), true);
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

		    inv.addItem(new ItemStack[] { this.regeneration });

		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		    inv.addItem(new ItemStack[] { this.steak });
		}
		if(classed.equals("heavy")){
			player.removePotionEffect(PotionEffectType.REGENERATION);
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
		if(classed.equals("archer")){
			player.removePotionEffect(PotionEffectType.REGENERATION);
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
		if(classed.equals("soldier")){
			player.removePotionEffect(PotionEffectType.REGENERATION);
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
