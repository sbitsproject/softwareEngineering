package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daoEntities.SupportEvent;
import daoEntities.Tickets;

public class SupportEventDao {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	private String connectionUrl = "jdbc:mysql://localhost:3306/machine_learning";
	private String connectionUser = "root";
	private String connectionPassword = "";

	public SupportEventDao() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	

	public List<SupportEvent> getsuppportEvent(String ticketId) throws SQLException {
		List<SupportEvent> support = new ArrayList<SupportEvent>();
		conn = DriverManager.getConnection(connectionUrl, connectionUser,
				connectionPassword);
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery("select supportEventID ,createdBy,supportEventCreated ,supportEventEnded,TicketID from supportevent where ticketId = "+ticketId);
		while (rs.next()) {
			SupportEvent sup = new SupportEvent();
			sup.setCreatedBy(rs.getString("createdBy"));
			sup.setSupportEventCreated(rs.getString("supportEventCreated"));
			sup.setSupportEventEnded(rs.getString("supportEventEnded"));
			sup.setSupportEventID(rs.getString("supportEventID"));
			sup.setTicketID(rs.getString("TicketID"));
			support.add(sup);
		}
		return support;
	}
	
	public boolean insertSupportEvent(SupportEvent support) throws SQLException{
		conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		PreparedStatement ps;
		String query;
		query = " insert into supportevent(createdBy,supportEventCreated ,supportEventEnded,TicketID) values (?,current_timestamp,null,?)";
		ps = conn.prepareStatement(query);
			ps.setString(1, support.getCreatedBy());
			ps.setString(2,support.getTicketID());
		ps.executeUpdate();
		return true;
	}
	
	public boolean updateSupportEvent(SupportEvent support) throws SQLException{
		conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		PreparedStatement ps;
		String query;
		query = " update supportevent set supportEventEnded = current_timestamp where supporteventid = ?";
		ps = conn.prepareStatement(query);
			ps.setString(1, support.getSupportEventID());
		
		ps.executeUpdate();
		return true;
	}
	
	
	
	}
