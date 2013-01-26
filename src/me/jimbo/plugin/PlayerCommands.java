package me.jimbo.plugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.kitteh.tag.TagAPI;

public class PlayerCommands extends JavaPlugin implements CommandExecutor {
	
	private CTF plugin;
	
	public boolean isHeavy = false;
	public boolean isSoldier = false;
	public boolean isArcher = false;
	public boolean isMedic = false;
	public boolean isPyro = false;
	public boolean isNinja = false;
	public boolean isChemist = false;
	public boolean isEngineer = false;
	public boolean isFirefly = false;
	public boolean isBerserker = false;
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
	ItemStack pixbow = new ItemStack(Material.BOW);
	ItemStack pixsword = new ItemStack(Material.DIAMOND_SWORD);
	ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE);

	
	public PlayerCommands (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(plugin.inProgress){
			if (sender instanceof Player){
				// Heavy class

				TagAPI.refreshPlayer((Player) sender);
				if((command.getName().equalsIgnoreCase("heavy")) && (((sender.isOp()) || (sender.hasPermission("ctf.class.heavy"))))){
					if(!(sender instanceof Player)){
						sender.sendMessage(ChatColor.RED + "In-Game Only!");
						return true;
					}
					Player target = (Player)sender;
					target.removePotionEffect(PotionEffectType.REGENERATION);
					PlayerInventory inventory = target.getInventory();
					int j = plugin.getTeam((Player) sender);
					if(!plugin.getDistanceToSpawn((Player) sender, j)){
						inventory.clear();
						inventory.setArmorContents(null);
						target.setHealth(0);
					}else if(plugin.getDistanceToSpawn((Player) sender, j)){
						inventory.clear();
						inventory.setArmorContents(null);
						if(inventory.getHelmet() == null){
							inventory.setHelmet(this.dhelmet);
						}
						if(inventory.getChestplate() == null){
							inventory.setChestplate(this.dchestplate);
						}
						if(inventory.getLeggings() == null){
							inventory.setLeggings(this.dleggings);
						}
						if(inventory.getBoots() == null){
							inventory.setBoots(this.dboots);
						}
							
						inventory.addItem(new ItemStack[] { this.dsword });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						
						this.isHeavy = true;
						if(CTF.PlayerClasses.containsKey(target)){
							CTF.PlayerClasses.put(target, "heavy");
						
						this.isSoldier = false;
				        this.isArcher = false;
				        this.isMedic = false;
				        this.isPyro = false;
				        this.isNinja = false;
				        this.isChemist = false;
					    this.isFirefly = false;
					    this.isBerserker = false;
	
				        sender.sendMessage(ChatColor.AQUA + "[CTF]" + ChatColor.GREEN + "You are now a Heavy");
				        return true;
						}
					}
					
				}
				// Soldier class
				if((command.getName().equalsIgnoreCase("soldier")) && (((sender.isOp()) || (sender.hasPermission("ctf.class.soldier"))))){
					if(!(sender instanceof Player)){
						sender.sendMessage(ChatColor.RED + "In-Game Only!");
						return true;
					}
					Player target = (Player)sender;
					target.removePotionEffect(PotionEffectType.REGENERATION);
					PlayerInventory inventory = target.getInventory();
					int j = plugin.getTeam((Player) sender);
					if(!plugin.getDistanceToSpawn((Player) sender, j)){
						inventory.clear();
						inventory.setArmorContents(null);
						target.setHealth(0);
					}else if(plugin.getDistanceToSpawn((Player) sender, j)){
						inventory.clear();
						inventory.setArmorContents(null);
						
						if(inventory.getHelmet() == null){
							inventory.setHelmet(this.ihelmet);
						}
						if(inventory.getChestplate() == null){
							inventory.setChestplate(this.ichestplate);
						}
						if(inventory.getLeggings() == null){
							inventory.setLeggings(this.ileggings);
						}
						if(inventory.getBoots() == null){
							inventory.setBoots(this.iboots);
						}
						inventory.addItem(new ItemStack[] { this.isword });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						inventory.addItem(new ItemStack[] { this.pearl });
						
						this.isSoldier = true;
						if(CTF.PlayerClasses.containsKey(target)){
							CTF.PlayerClasses.put(target, "soldier");
						}
						
						this.isHeavy = false;
				        this.isArcher = false;
				        this.isMedic = false;
				        this.isPyro = false;
				        this.isNinja = false;
				        this.isChemist = false;
					    this.isEngineer = false;
					    this.isFirefly = false;
					    this.isBerserker = false;
		
				        sender.sendMessage(ChatColor.AQUA + "[CTF]" + ChatColor.GREEN + "You are now a Soldier");
				        return true;
					}
				}
				// Firefly class
				if((command.getName().equalsIgnoreCase("firefly")) && (((sender.isOp()) || (sender.hasPermission("ctf.class.firefly"))))){
					if(!(sender instanceof Player)){
						sender.sendMessage(ChatColor.RED + "In-Game Only!");
						return true;
					}
					Player target = (Player)sender;
					target.removePotionEffect(PotionEffectType.REGENERATION);
					PlayerInventory inventory = target.getInventory();
					int j = plugin.getTeam((Player) sender);
					if(!plugin.getDistanceToSpawn((Player) sender, j)){
						inventory.clear();
						inventory.setArmorContents(null);
						target.setHealth(0);
					}else if(plugin.getDistanceToSpawn((Player) sender, j)){
						inventory.clear();
						inventory.setArmorContents(null);
						((Player) sender).setFlying(true);
						
						this.pixbow.addEnchantment(Enchantment.FIRE_ASPECT, 1);
						this.pixsword.addEnchantment(Enchantment.FIRE_ASPECT, 3);
						
						inventory.addItem(new ItemStack[] { this.pixbow });
						inventory.addItem(new ItemStack[] { this.pixsword });
						
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						
						inventory.addItem(new ItemStack[] { this.gapple });
						inventory.addItem(new ItemStack[] { this.gapple });
						inventory.addItem(new ItemStack[] { this.gapple });
						
						inventory.addItem(new ItemStack[] { this.sugar });
						inventory.addItem(new ItemStack[] { this.sugar });
						inventory.addItem(new ItemStack[] { this.sugar });
						
						this.isFirefly = true;
						if(CTF.PlayerClasses.containsKey(target)){
							CTF.PlayerClasses.put(target, "firefly");
						}
						
						this.isHeavy = false;
				        this.isArcher = false;
				        this.isMedic = false;
				        this.isPyro = false;
				        this.isNinja = false;
				        this.isChemist = false;
					    this.isEngineer = false;
					    this.isBerserker = false;
		
				        sender.sendMessage(ChatColor.AQUA + "[CTF]" + ChatColor.GREEN + "You are now a Firefly!");
				        return true;
					}
				}
				// Berserker class
				if((command.getName().equalsIgnoreCase("berserker")) && (((sender.isOp()) || (sender.hasPermission("ctf.class.berserker"))))){
					if(!(sender instanceof Player)){
						sender.sendMessage(ChatColor.RED + "In-Game Only!");
						return true;
					}
					Player target = (Player)sender;
					target.removePotionEffect(PotionEffectType.REGENERATION);
					PlayerInventory inventory = target.getInventory();
					int j = plugin.getTeam((Player) sender);
					if(!plugin.getDistanceToSpawn((Player) sender, j)){
						inventory.clear();
						inventory.setArmorContents(null);
						target.setHealth(0);
					}else if(plugin.getDistanceToSpawn((Player) sender, j)){
						inventory.clear();
						inventory.setArmorContents(null);
						((Player) sender).setFlying(true);
						this.dleggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
						this.dleggings.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
						inventory.setLeggings(dleggings);
						
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });
						inventory.addItem(new ItemStack[] { this.steak });

						this.isBerserker = true;
						if(CTF.PlayerClasses.containsKey(target)){
							CTF.PlayerClasses.put(target, "berserker");
						}
						
						this.isHeavy = false;
				        this.isArcher = false;
				        this.isMedic = false;
				        this.isPyro = false;
				        this.isNinja = false;
				        this.isChemist = false;
					    this.isEngineer = false;
		
				        sender.sendMessage(ChatColor.AQUA + "[CTF]" + ChatColor.GREEN + "You are now a Berserker!");
				        return true;
					}
				}
				// Archer class
				if((command.getName().equalsIgnoreCase("archer")) && (((sender.isOp()) || (sender.hasPermission("ctf.class.archer"))))){
					if(!(sender instanceof Player)){
						sender.sendMessage(ChatColor.RED + "In-Game Only!");
						return true;
					}
					Player target = (Player)sender;
					target.removePotionEffect(PotionEffectType.REGENERATION);
					PlayerInventory inv = target.getInventory();
					int j = plugin.getTeam((Player) sender);
					if(!plugin.getDistanceToSpawn((Player) sender, j)){
						inv.clear();
						inv.setArmorContents(null);
						target.setHealth(0);
					}else if(plugin.getDistanceToSpawn((Player) sender, j)){
						inv.clear();
						inv.setArmorContents(null);
						
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
				        this.isArcher = true;
						if(CTF.PlayerClasses.containsKey(target)){
							CTF.PlayerClasses.put(target, "archer");
						}
						
						this.isSoldier = false;
						this.isHeavy = false;
				        this.isMedic = false;
				        this.isPyro = false;
				        this.isNinja = false;
				        this.isChemist = false;
					    this.isEngineer = false;
					    this.isFirefly = false;
					    this.isBerserker = false;
		
				        sender.sendMessage(ChatColor.AQUA + "[CTF]" + ChatColor.GREEN + "You are now an Archer!");
				        return true;
					}
				}
				// Medic class
				if((command.getName().equalsIgnoreCase("medic")) && (((sender.isOp()) || (sender.hasPermission("ctf.class.medic"))))){
					if(!(sender instanceof Player)){
						sender.sendMessage(ChatColor.RED + "In-Game Only!");
						return true;
					}
					Player target = (Player)sender;
					PlayerInventory inv = target.getInventory();
					int j = plugin.getTeam((Player) sender);
					if(!plugin.getDistanceToSpawn((Player) sender, j)){
						inv.clear();
						inv.setArmorContents(null);
						target.setHealth(0);
					}else if(plugin.getDistanceToSpawn((Player) sender, j)){
						inv.clear();
						inv.setArmorContents(null);
						
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
							
					    this.isMedic = true;
						if(CTF.PlayerClasses.containsKey(target)){
							CTF.PlayerClasses.put(target, "medic");
						}
							
						this.isHeavy = false;
					    this.isArcher = false;
						this.isSoldier = false;
					    this.isPyro = false;
					    this.isNinja = false;
					    this.isChemist = false;
					    this.isEngineer = false;
					    this.isFirefly = false;
					    this.isBerserker = false;
		
					    sender.sendMessage(ChatColor.AQUA + "[CTF]" + ChatColor.GREEN + "You are now a Medic!");
					    return true;
					}
				}
				// Pyro class
				if((command.getName().equalsIgnoreCase("pyro")) && (((sender.isOp()) || (sender.hasPermission("ctf.class.pyro"))))){
					if(!(sender instanceof Player)){
						sender.sendMessage(ChatColor.RED + "In-Game Only!");
						return true;
					}
					Player target = (Player)sender;
					target.removePotionEffect(PotionEffectType.REGENERATION);
					PlayerInventory inv = target.getInventory();
					int j = plugin.getTeam((Player) sender);
					if(!plugin.getDistanceToSpawn((Player) sender, j)){
						inv.clear();
						inv.setArmorContents(null);
						target.setHealth(0);
					}else if(plugin.getDistanceToSpawn((Player) sender, j)){
						inv.clear();
						inv.setArmorContents(null);
						
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
		
					    this.isPyro = true;
					    if(CTF.PlayerClasses.containsKey(target)){
							CTF.PlayerClasses.put(target, "pyro");
						}
		
					    this.isHeavy = false;
					    this.isSoldier = false;
					    this.isArcher = false;
					    this.isMedic = false;
					    this.isNinja = false;
					    this.isChemist = false;
					    this.isEngineer = false;
					    this.isFirefly = false;
					    this.isBerserker = false;
		
					    sender.sendMessage(ChatColor.AQUA + "[CTF]" + ChatColor.GREEN + "You are now a Pyro");
					    return true;
					}
				}
				// Ninja class
				if((command.getName().equalsIgnoreCase("ninja")) && (((sender.isOp()) || (sender.hasPermission("ctf.class.ninja"))))){
					if(!(sender instanceof Player)){
						sender.sendMessage(ChatColor.RED + "In-Game Only!");
						return true;
					}
					Player target = (Player)sender;
					target.removePotionEffect(PotionEffectType.REGENERATION);
					PlayerInventory inv = target.getInventory();
					int j = plugin.getTeam((Player) sender);
					if(!plugin.getDistanceToSpawn((Player) sender, j)){
						inv.clear();
						inv.setArmorContents(null);
						target.setHealth(0);
					}else if(plugin.getDistanceToSpawn((Player) sender, j)){
						inv.clear();
						inv.setArmorContents(null);
		
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
		
					    this.isNinja = true;
					    if(CTF.PlayerClasses.containsKey(target)){
					    	CTF.PlayerClasses.put(target, "ninja");
					    	}
		
					    this.isHeavy = false;
					    this.isSoldier = false;
					    this.isArcher = false;
					    this.isMedic = false;
					    this.isPyro = false;
					    this.isChemist = false;
					    this.isEngineer = false;
					    this.isFirefly = false;
					    this.isBerserker = false;
		
					    sender.sendMessage(ChatColor.AQUA + "[CTF]" + ChatColor.GREEN + "You are now a Ninja");
					    return true;
					}
				}
				// Engineer class
				if((command.getName().equalsIgnoreCase("engineer")) && (((sender.isOp()) || (sender.hasPermission("ctf.class.engineer"))))){
					if(!(sender instanceof Player)){
						sender.sendMessage(ChatColor.RED + "In-Game Only!");
						return true;
					}
					Player target = (Player)sender;
					target.removePotionEffect(PotionEffectType.REGENERATION);
					PlayerInventory inv = target.getInventory();
					int j = plugin.getTeam((Player) sender);
					if(!plugin.getDistanceToSpawn((Player) sender, j)){
						inv.clear();
						inv.setArmorContents(null);
						target.setHealth(0);
					}else if(plugin.getDistanceToSpawn((Player) sender, j)){
						inv.clear();
						inv.setArmorContents(null);
						
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
		
					      this.isEngineer = true;
					      if(CTF.PlayerClasses.containsKey(target)){
					    	  CTF.PlayerClasses.put(target, "engineer");
							}
		
					      this.isNinja = false;
					      this.isHeavy = false;
					      this.isSoldier = false;
					      this.isArcher = false;
					      this.isMedic = false;
					      this.isPyro = false;
					      this.isChemist = false;
						  this.isFirefly = false;
						  this.isBerserker = false;
		
					      sender.sendMessage(ChatColor.AQUA + "[CTF]" + ChatColor.GREEN + "You are now an Engineer");
					      return true;
					}
				}
				
				if (command.getName().equalsIgnoreCase("ctf")) {
					if(args.length == 0){
						sender.sendMessage("Capture The Flag Commands");
						sender.sendMessage(ChatColor.AQUA + " /CTF class       -  show your class");
				        sender.sendMessage(ChatColor.AQUA + " /CTF team        -  show your team");
				        sender.sendMessage(ChatColor.AQUA + " /CTF classes     -  show available classes");
					}
					if (args.length > 0) {
						String cmd = args[0].toLowerCase();
						if(cmd.equalsIgnoreCase("classes")){
							sender.sendMessage(ChatColor.AQUA + " /soldier    -  Be a Soldier");
						    sender.sendMessage(ChatColor.AQUA + " /heavy      -  Be a Heavy");
						    sender.sendMessage(ChatColor.AQUA + " /archer     -  Be an Archer");
						    sender.sendMessage(ChatColor.AQUA + " /ninja      -  Be a Ninja");
						    sender.sendMessage(ChatColor.AQUA + " /medic      -  Be a Medic");	
						    sender.sendMessage(ChatColor.AQUA + " /firefly      -  Be a Firefly");
						    sender.sendMessage(ChatColor.AQUA + " /berserker      -  Be a Berserker");	
						}
						if (args.length > 1) {
							String cmd2 = args[1].toLowerCase();
							if(cmd.equals("set")) {
								if(cmd2.equals("staging")){
									Player player = (Player) sender;
								    Location location = player.getLocation();
									sender.sendMessage(ChatColor.GRAY + "------------------------------------");
									sender.sendMessage(ChatColor.GREEN + "Setting Staging Area");
									sender.sendMessage(ChatColor.GREEN + "" + plugin.getConfig().getDouble("Staging Area.X") + "," + plugin.getConfig().getDouble("Staging Area.Y") + "," + plugin.getConfig().getDouble("Staging Area.Z"));
								    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
								    //Do something
								    
								    plugin.getConfig().set("Staging Area.X", location.getX());
								    plugin.getConfig().set("Staging Area.Y", location.getY());
								    plugin.getConfig().set("Staging Area.Z", location.getZ());
								    plugin.saveConfig();
								}
								if(cmd2.equals("red"))
						    	{
									String cmd3 = args[2].toLowerCase();
									if(cmd3.equals("spawn"))
							    	{
										Player player = (Player) sender;
									    Location location = player.getLocation();
										sender.sendMessage(ChatColor.GRAY + "------------------------------------");
										sender.sendMessage(ChatColor.GREEN + "Setting Red Team Spawn!");
										sender.sendMessage(ChatColor.GREEN + "" + plugin.getConfig().getDouble("Spawns.Red.X") + "," + plugin.getConfig().getDouble("Spawns.Red.Y") + "," + plugin.getConfig().getDouble("Spawns.Red.Z"));
									    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
									    //Do something
									    
									    plugin.getConfig().set("Spawns.Red.X", location.getX());
									    plugin.getConfig().set("Spawns.Red.Y", location.getY());
									    plugin.getConfig().set("Spawns.Red.Z", location.getZ());
									    plugin.saveConfig();
							    	}
									if(cmd3.equals("flag"))
							    	{
										Player player = (Player) sender;
										Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
									    Location location = block.getLocation();
										sender.sendMessage(ChatColor.GRAY + "------------------------------------");
										sender.sendMessage(ChatColor.GREEN + "Setting Red Team Goal!");
										sender.sendMessage(ChatColor.GREEN + "" + block.getLocation());
									    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
									    //Do something
									    
									    plugin.getConfig().set("Goals.Red.X", location.getX());
									    plugin.getConfig().set("Goals.Red.Y", location.getY());
									    plugin.getConfig().set("Goals.Red.Z", location.getZ());
									    plugin.saveConfig();
							    	}
						    	}
								if(cmd2.equals("blue"))
						    	{
									String cmd3 = args[2].toLowerCase();
									if(cmd3.equals("spawn"))
							    	{
										Player player = (Player) sender;
									    Location location = player.getLocation();
										sender.sendMessage(ChatColor.GRAY + "------------------------------------");
										sender.sendMessage(ChatColor.GREEN + "Setting Blue Team Spawn!");
										sender.sendMessage(ChatColor.GREEN + "" + plugin.getConfig().getDouble("Spawns.Blue.X") + "," + plugin.getConfig().getDouble("Spawns.Blue.Y") + "," + plugin.getConfig().getDouble("Spawns.Blue.Z"));
									    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
									    //Do something
									    
									    plugin.getConfig().set("Spawns.Blue.X", location.getX());
									    plugin.getConfig().set("Spawns.Blue.Y", location.getY());
									    plugin.getConfig().set("Spawns.Blue.Z", location.getZ());
									    plugin.saveConfig();
							    	}
									if(cmd3.equals("flag"))
							    	{
										Player player = (Player) sender;
										Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
									    Location location = block.getLocation();
										sender.sendMessage(ChatColor.GRAY + "------------------------------------");
										sender.sendMessage(ChatColor.GREEN + "Setting Blue Team Goal!");
										sender.sendMessage(ChatColor.GREEN + "" + block.getLocation());
									    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
									    //Do something
									    plugin.getConfig().set("Goals.Blue.X", location.getX());
									    plugin.getConfig().set("Goals.Blue.Y", location.getY());
									    plugin.getConfig().set("Goals.Blue.Z", location.getZ());
									    plugin.saveConfig();
							    	}
						    	}
							}
							
						}
						if(cmd.equals("version"))
				    	{
							PluginDescriptionFile pdf = plugin.getDescription();
							sender.sendMessage(ChatColor.GRAY + "------------------------------------");
							sender.sendMessage(ChatColor.RED + "This server running " + ChatColor.GOLD + "CTF" + ChatColor.RED + " version: " + ChatColor.BLUE + pdf.getVersion());
							sender.sendMessage(ChatColor.GREEN + "Created by: " + ChatColor.YELLOW + "teh_jombi");
							sender.sendMessage(ChatColor.GREEN + "Authors: " + ChatColor.YELLOW + pdf.getAuthors());
						    sender.sendMessage(ChatColor.DARK_AQUA + "For a command list, and help, type /CTF:");
						    sender.sendMessage(ChatColor.GREEN + "http://www.silentnoobs.com/");
						    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
				    	}
						if(cmd.equals("test"))
				    	{
							sender.sendMessage(ChatColor.GRAY + "------------------------------------");
							sender.sendMessage(ChatColor.YELLOW + "Test Command!");
						    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
						    // Do something
						    plugin.inProgress = true;
						    int j = plugin.getTeam((Player) sender);
						    if(plugin.getDistanceToSpawn((Player) sender, j)){
						    	sender.sendMessage("You are within 5 blocks of your spawnpoint!");
						    }else{
						    	sender.sendMessage("You are outside of your spawnpoint!");
						    }
				    	}
						if(cmd.equals("reset") && sender.hasPermission("ctf.admin.reset"))
				    	{
							sender.sendMessage(ChatColor.GRAY + "------------------------------------");
							sender.sendMessage(ChatColor.YELLOW + "Reset Flags!");
						    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
						    // Do something
						    plugin.resetFlag(1);
						    plugin.resetFlag(2);
						    if(plugin.redFlagCarrier.getPlayer() == null){
							    plugin.resetInv(plugin.redFlagCarrier.getPlayer());
						    }
						    if(plugin.blueFlagCarrier.getPlayer() != null){
							    plugin.resetInv(plugin.blueFlagCarrier.getPlayer());
						    }
						    
				    	}
						if(cmd.equals("stop"))
				    	{
							sender.sendMessage(ChatColor.GRAY + "------------------------------------");
							sender.sendMessage(ChatColor.RED + "Stopping Round Early!");
						    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
						    //Do something
						    plugin.inProgress = false;
	
				    	}
						if(cmd.equals("team"))
				    	{
						    //Do something
							if(CTF.AllPlayers.contains(((Player) sender).getPlayer())){
								sender.sendMessage("You're on the " + ChatColor.BLUE + "Blue " + "Team!");
							} else if(CTF.RedPlayers.contains(((Player) sender).getPlayer())) {
								sender.sendMessage("You're on the " + ChatColor.RED + "Red " + "Team!");
							} else {
								sender.sendMessage("Your team has not been set yet!");
							}
	
				    	}
						if(cmd.equals("status"))
				    	{
							sender.sendMessage(ChatColor.GRAY + "------------------------------------");
							sender.sendMessage(ChatColor.RED + "Getting Round Status!");
						    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
						    //Do something
						    sender.sendMessage("" + plugin.inProgress);
						    //sender.sendMessage("" +CTF.AllPlayers.get(0));
				    	}
						if(cmd.equals("class"))
				    	{
							sender.sendMessage(ChatColor.GRAY + "------------------------------------");
							sender.sendMessage(ChatColor.RED + "Checking Class!");
							sender.sendMessage(ChatColor.GREEN + "You are a " + CTF.PlayerClasses.get(sender));
						    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
				    	}
					}
				}
			return false;
			} else {
				// Denies access to console users
				sender.sendMessage("Only in-game players can use this command!");
				return false;
			}
		}
		else{
			sender.sendMessage(ChatColor.RED + "Can't use commands until the round starts :)");
		}
		return false;
	}
	
	public boolean getClass(String playerClass){
		if(playerClass.equals("Engineer")){
			return this.isEngineer;
		}else if(playerClass.equals("Ninja")){
			return this.isNinja;
		}else if(playerClass.equals("Heavy")){
			return this.isHeavy;
		}else if(playerClass.equals("Medic")){
			return this.isMedic;
		}else if(playerClass.equals("Archer")){
			return this.isArcher;
		}else if(playerClass.equals("Soldier")){
			return this.isSoldier;
		}else if(playerClass.equals("Pyro")){
			return this.isPyro;
		}else if(playerClass.equals("Firefly")){
			return this.isFirefly;
		}else if(playerClass.equals("Beserker")){
			return this.isBerserker;
		}
		
		return false;
	}
	
}
