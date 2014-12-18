package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.mysql.jdbc.Driver;

public class driver 
{
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private int cmd = 0;
	
	private String connectionUrl = "jdbc:mysql://localhost:3306/test";
	private String connectionUser = "root";
	private String connectionPassword = "root";
	
	/*
	private String connectionUrl = "jdbc:mysql://athena.ecs.csus.edu:3306/sbits";
	private String connectionUser = "sbits";
	private String connectionPassword = "sbits_db";
	*/
	
	//Constructor
	public driver() 
	{
		
		try 
		{
			//	new com.mysql.jdbc.Driver();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdatabase?user=testuser&password=testpassword");
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
	}
	
	//simple method for executing updates and stuff... yeaah...
	public void command(String input)
	{
		try
		{
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			//System.out.println(input);
			cmd = stmt.executeUpdate(input);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//try { if (cmd != null) System.out.println("Failed."); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	//returns the SINGLE value from a column in a table.
	//if value does not exist, or there is an error, return NULL.
	public String retrieve_from_table(String TABLE, String VALUE, String COLUMN)
	{
		try
		{
			//new com.mysql.jdbc.Driver();
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			//System.out.println("TABLE " + TABLE + "\nVALUE " + VALUE + "\nCOLUMN " + COLUMN);
			rs = stmt.executeQuery("SELECT a." + COLUMN + " FROM " + TABLE + " a where " + COLUMN + "=" + "'" + VALUE + "'");
			//rs = stmt.executeQuery("select a.password from user a where password='test' and firstName='larry'");
			if(rs.next()) 
				return rs.getString(COLUMN);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return null;
	}
	
	//returns a SINGLE value from a column in a table.
	//Pass in table name, an array of values, and an array of columns
	//Both arrays must have the same length && both arrays should have an array size of at least n >= 1
	//the first index of COLUMN and VALUE is what will be returned if found in the table
	//Note: all indices of COLUMN should be valid table columns
	//if value does not exist, or there is an error, return NULL.
	public String retrieve_from_table(String TABLE, String[] VALUE, String[] COLUMN)
	{
		if(VALUE.length != COLUMN.length) return null; // both arrays must have the same length
		try
		{
			String combined = "";
			for( int i = 0; i < VALUE.length; i++)
			{
				if(i == 0)	
					combined = combined + " " + COLUMN[i] + "=" + "'" + VALUE[i] + "'";
				else
					combined = combined + " and " + COLUMN[i] + "=" + "'" + VALUE[i] + "'";
			}
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT a." + COLUMN[0] + " FROM " + TABLE + " a where " + combined);
			if(rs.next()) 
				return rs.getString(COLUMN[0]);
			else
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return null;
	}
		
	
	//return the number rows in a table as integer
	/*
	public int get_row_count(String TABLE)
	{
		try
		{
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			//System.out.println(input);
			rs = stmt.executeQuery("select count(*) from " + TABLE);
			return Integer.parseInt(rs.getString("count(*)"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return 0;
	}
	*/
}
