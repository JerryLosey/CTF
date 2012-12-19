package me.jimbo.plugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
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

public class PlayerCommands extends JavaPlugin implements CommandExecutor {
	
	private CTF plugin;
	
	public boolean isHeavy = false;
	public boolean isSoldier = false;
	public boolean isArcher = false;
	public boolean isMedic = false;
	public boolean isPyro = false;
	public boolean isNinja = false;
	public boolean isChemist = false;
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
	ItemStack egg = new ItemStack(Material.EGG);

	
	public PlayerCommands (CTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player){
			// Heavy class
			if((command.getName().equalsIgnoreCase("heavy")) && ((sender.isOp()) || (sender.hasPermission("ctf.class.heavy")))){
				if(!(sender instanceof Player)){
					sender.sendMessage(ChatColor.RED + "In-Game Only!");
					return true;
				}
				Player target = (Player)sender;
				PlayerInventory inventory = target.getInventory();
				inventory.clear();
				inventory.setArmorContents(null);
				target.setHealth(0);
				
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
					
					inventory.addItem(new ItemStack[] { this.dsword });
					inventory.addItem(new ItemStack[] { this.steak });
					inventory.addItem(new ItemStack[] { this.steak });
					inventory.addItem(new ItemStack[] { this.steak });
					
					this.isHeavy = true;
					
					this.isSoldier = false;
			        this.isArcher = false;
			        this.isMedic = false;
			        this.isPyro = false;
			        this.isNinja = false;
			        this.isChemist = false;
			        
			        return true;
				}
			}
			// Soldier class
			if((command.getName().equalsIgnoreCase("soldier")) && ((sender.isOp()) || (sender.hasPermission("ctf.class.soldier")))){
				if(!(sender instanceof Player)){
					sender.sendMessage(ChatColor.RED + "In-Game Only!");
					return true;
				}
				Player target = (Player)sender;
				PlayerInventory inventory = target.getInventory();
				inventory.clear();
				inventory.setArmorContents(null);
				target.setHealth(0);
				
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
					
					this.isHeavy = false;
			        this.isArcher = false;
			        this.isMedic = false;
			        this.isPyro = false;
			        this.isNinja = false;
			        this.isChemist = false;
			        
			        return true;
				}
			}
			// Archer class
			if((command.getName().equalsIgnoreCase("archer")) && ((sender.isOp()) || (sender.hasPermission("ctf.class.archer")))){
				if(!(sender instanceof Player)){
					sender.sendMessage(ChatColor.RED + "In-Game Only!");
					return true;
				}
				Player target = (Player)sender;
				PlayerInventory inv = target.getInventory();
				inv.clear();
				inv.setArmorContents(null);
				target.setHealth(0);
				
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
					
					this.isSoldier = false;
					this.isHeavy = false;
			        this.isMedic = false;
			        this.isPyro = false;
			        this.isNinja = false;
			        this.isChemist = false;
			        
			        return true;
				}
			}
			
			if (command.getName().equalsIgnoreCase("ctf")) {
				if (args.length > 0) {
					String cmd = args[0].toLowerCase();
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
								if(cmd3.equals("goal"))
						    	{
									Player player = (Player) sender;
								    Location location = player.getLocation();
									sender.sendMessage(ChatColor.GRAY + "------------------------------------");
									sender.sendMessage(ChatColor.GREEN + "Setting Red Team Goal!");
									sender.sendMessage(ChatColor.GREEN + "Make sure you are standing on top of the diamond block!");
									sender.sendMessage(ChatColor.GREEN + "" + plugin.getConfig().getDouble("Diamond Blocks.Red.X") + "," + plugin.getConfig().getDouble("Diamond Blocks.Red.Y") + "," + plugin.getConfig().getDouble("Diamond Blocks.Red.Z"));
								    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
								    //Do something
								    
								    plugin.getConfig().set("Diamond Blocks.Red.X", location.getX());
								    plugin.getConfig().set("Diamond Blocks.Red.Y", location.getY());
								    plugin.getConfig().set("Diamond Blocks.Red.Z", location.getZ());
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
								if(cmd3.equals("goal"))
						    	{
									Player player = (Player) sender;
								    Location location = player.getLocation();
									sender.sendMessage(ChatColor.GRAY + "------------------------------------");
									sender.sendMessage(ChatColor.GREEN + "Setting Blue Team Goal!");
									sender.sendMessage(ChatColor.GREEN + "Make sure you are standing on top of the diamond block!");
									sender.sendMessage(ChatColor.GREEN + "" + plugin.getConfig().getDouble("Diamond Blocks.Blue.X") + "," + plugin.getConfig().getDouble("Diamond Blocks.Blue.Y") + "," + plugin.getConfig().getDouble("Diamond Blocks.Blue.Z"));
								    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
								    //Do something
								    
								    plugin.getConfig().set("Diamond Blocks.Blue.X", location.getX());
								    plugin.getConfig().set("Diamond Blocks.Blue.Y", location.getY());
								    plugin.getConfig().set("Diamond Blocks.Blue.Z", location.getZ());
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
						sender.sendMessage(ChatColor.GREEN + "Authors: " + ChatColor.YELLOW + "teh_jombi");
					    sender.sendMessage(ChatColor.DARK_AQUA + "For a command list, and help, see the wiki:");
					    sender.sendMessage(ChatColor.GREEN + "http://www.silentnoobs.com/");
					    sender.sendMessage(ChatColor.AQUA + "Visit the BukkitDev page at:");
					    sender.sendMessage(ChatColor.BLUE + "http://dev.bukkit.org/server-mods/CTF");
					    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
			    	}
					if(cmd.equals("start"))
			    	{
						sender.sendMessage(ChatColor.GRAY + "------------------------------------");
						sender.sendMessage(ChatColor.YELLOW + "Starting Round!");
					    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
					    // Do something
					    plugin.inProgress = true;
					    
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
							sender.sendMessage("You're on the Blue Team!");
						} else if(CTF.RedPlayers.contains(((Player) sender).getPlayer())) {
							sender.sendMessage("You're on the Red Team!");
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
					if(cmd.equals("join"))
			    	{
						sender.sendMessage(ChatColor.GRAY + "------------------------------------");
						sender.sendMessage(ChatColor.RED + "Joined The Game!");
					    sender.sendMessage(ChatColor.GRAY + "------------------------------------");
					    //Do something
					    if (!CTF.AllPlayers.contains(((Player) sender).getPlayer())) {
					    	CTF.AllPlayers.add(((Player) sender).getPlayer());
					    	sender.sendMessage("" + CTF.AllPlayers.size());
					    }
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
	
	
}
