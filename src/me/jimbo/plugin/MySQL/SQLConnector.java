package me.jimbo.plugin.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class SQLConnector {
	
	private boolean isInitialized;
	private boolean isConnected;
	private boolean isFailed;
	private String server;
	private String user;
	private String password;
	private Connection con;
	private Statement stmt;
	
	public SQLConnector(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException exception){
			exception.printStackTrace();
		}
		
		this.isInitialized = false;
		this.isConnected = false;
		this.isFailed = false;
	}
	
	public SQLConnector(String server, String user, String password, String database, int port){
		this.isInitialized = true;
		this.isConnected = false;
		
		this.user = user;
		this.password = password;
		
		if(!server.startsWith("jdbc:mysql://")){
			server = "jdbc:mysql://" + server + ":" + port + "/" + database;
		}
		
		this.server = server;
		try{
			this.con = DriverManager.getConnection(server, user, password);
		} catch (SQLException exception){
			exception.printStackTrace();
			if(exception.getMessage().contains("denied")){
				System.err.println("[MySQL] Cannot Connect! Wrong User/Password Combination!");
				this.isFailed = true;
				return;
			}
			return;
		}
		
		try{
			this.stmt = this.con.createStatement();
		} catch(SQLException exception){
			exception.printStackTrace();
			System.err.println("[MySQL] An Unknown Error Occured!");
			this.isFailed = true;
			return;
		}
		this.isFailed = false;
		this.isConnected = true;
	}
	
	public boolean connect(){
		if((this.isConnected) || (this.isFailed) || (!this.isInitialized)){
			return false;
		}
		try{
			this.con = DriverManager.getConnection(this.server, this.user, this.password);
			this.isConnected = true;
		} catch (Exception exception){
			exception.printStackTrace();
			if(exception.getMessage().contains("denied")){
				System.err.println("[MySQL] Cannot Connect! Wrong User/Password Combination!");
		        this.isFailed = true;
		        return false;
			}
			if(exception.getMessage().contains("refused")){
				System.err.println("[MySQL] Cannot Connect! Server Not Available!");
		        this.isFailed = true;
		        return false;
			}
			
			return false;
		}
		return true;
	}
	public boolean disconnect()
	  {
	    if ((!this.isConnected) || (this.isFailed) || (!this.isInitialized))
	    {
	      return false;
	    }

	    try
	    {
	      this.con.close();
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	      System.err.println("[MySQL] Cannot Close Connection!");
	      e.printStackTrace();
	      return false;
	    }
	    this.con = null;

	    return true;
	  }
	
	public void init(String server, String user, String password, String database, int port)
	  {
	    this.isInitialized = true;
	    this.isConnected = false;

	    this.user = user;
	    this.password = password;

	    if (!server.startsWith("jdbc:mysql://"))
	    {
	      server = "jdbc:mysql://" + server + ":" + port + "/" + database;
	    }

	    this.server = server;
	  }

	  public ResultSet query(String sql_query) throws SQLException
	  {
	    if ((!this.isInitialized) || (this.isFailed) || (!this.isConnected))
	    {
	      System.err.println("[MySQL] No Connection Found!");
	      System.err.println("[MySQL] Fail: " + this.isFailed);
	      System.err.println("[MySQL] Connection: " + this.isConnected);
	      System.err.println("[MySQL] Init: " + this.isInitialized);
	      return null;
	    }

	    try
	    {
	      this.stmt = this.con.createStatement();
	    }
	    catch (SQLException e1)
	    {
	      e1.printStackTrace();
	    }

	    try
	    {
	      ResultSet localResultSet = this.stmt.executeQuery(sql_query);
	      return localResultSet;
	    }
	    catch (SQLException e)
	    {
	      System.err.println("[MySQL] Cannot Execute Query! ERROR:");
	      e.printStackTrace();
	      return null;
	    }
	    finally
	    {
	      this.con.close();
	    }
	  }
}
