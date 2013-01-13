package me.jimbo.plugin.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.command.ConsoleCommandSender;

import me.jimbo.plugin.CTF;

public class Executor extends Thread {

	private SQLConnector connector;
	private CTF plugin;
	private ResultSet result = null;
	
	private final String SERVER = "Config.server";
	private final String TABLE = "Config.table";
	private final String DATABASE = "Config.database";
	private final String USER = "Config.user";
	private final String PASSWORD = "Config.password";
	private final String COLUMN = "Config.column_name";
	private final String PORT = "Config.port";
	private final String SINGLEUSE = "Config.singleuse";
	private String server;
	private String user;
	private String db;
	private String pass;
	private String table;
	private ConsoleCommandSender op;
	private int run;
	
	public Executor(CTF plugin){
		this.plugin = plugin;
		this.plugin.reloadConfig();
		this.run = 0;
		this.connector = new SQLConnector();
		this.op = this.plugin.getServer().getConsoleSender();
	}
	
	public void run(){
		
		this.plugin.reloadConfig();
		this.db = this.plugin.getConfig().getString("Config.database");
	    this.server = this.plugin.getConfig().getString("Config.server");
	    this.user = this.plugin.getConfig().getString("Config.user");
	    this.pass = this.plugin.getConfig().getString("Config.password");
	    this.table = this.plugin.getConfig().getString("Config.table");
	    
	    System.out.println("[MySQL] Running a task! Execution number: ");
	    this.connector.init(this.server, this.user, this.pass, this.db, this.plugin.getConfig().getInt("Config.port"));

	    this.connector.connect();
	    try
	    {
	      if ((this.result = this.connector.query("SELECT * FROM " + this.table + ";")) == null)
	      {
	        System.err
	          .println("[MySQL] FEHLER IN DER MYSQL-KLASSE!");
	        return;
	      }
	    }catch(SQLException e){
	    	System.err.println("[MySQL] Query Failed");
	        e.printStackTrace();
	    }
	}
}
