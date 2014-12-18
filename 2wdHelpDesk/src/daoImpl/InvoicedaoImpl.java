package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import beans.SaveTicketDetailsRequest;
import daoEntities.InvoiceTable;
import daoEntities.Tickets;

public class InvoicedaoImpl {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	private String connectionUrl = "jdbc:mysql://localhost:3306/machine_learning";
	private String connectionUser = "root";
	private String connectionPassword = "";

	public InvoicedaoImpl() {

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

	public boolean createinvoice() throws SQLException {

		saveInvoice(getHrsWorked(getTicketDetails(getCompanyDetails())),retrievesupportAgree());

		return true;
	}

	public List<String> getCompanyDetails() throws SQLException {
		List<String> companyIds = new ArrayList<String>();
		conn = DriverManager.getConnection(connectionUrl, connectionUser,
				connectionPassword);
		stmt = conn.createStatement();

		rs = stmt.executeQuery("select id from company");
		while (rs.next()) {
			companyIds.add(rs.getString(1));
		}
		return companyIds;
	}

	public Map<String, List<String>> getTicketDetails(List<String> companyId)
			throws SQLException {
		Map<String, List<String>> ticket = new HashMap<String, List<String>>();
		conn = DriverManager.getConnection(connectionUrl, connectionUser,
				connectionPassword);
		stmt = conn.createStatement();
		String company;
		for (int i = 0; i < companyId.size(); i++) {
			company = companyId.get(i);
			List<String> companyIds = new ArrayList<String>();
			rs = stmt.executeQuery("select id from tickets where company = "
					+ company);
			while (rs.next()) {
				companyIds.add(rs.getString(1));
			}
			ticket.put(company, companyIds);
		}
		return ticket;
	}

	public Map<String, Double> getHrsWorked(Map<String, List<String>> tickets)
			throws SQLException {
		Map<String, Double> amtValues = new HashMap<String, Double>();
	//	Map<String, List<String>> ticketHrs = new HashMap<String, List<String>>();
		//Map<String, List<String>> ticket = new HashMap<String, List<String>>();
		conn = DriverManager.getConnection(connectionUrl, connectionUser,
				connectionPassword);
		stmt = conn.createStatement();
		String company;
		Iterator<String> comp = tickets.keySet().iterator();

		while (comp.hasNext()) {
			double total = 0;
			company = comp.next();
			List<String> companyIds = tickets.get(company);
			for (int i = 0; i < companyIds.size(); i++) {
				rs = stmt
						.executeQuery("select timestampdiff(SECOND,supporteventcreated,supporteventended) from supportevent WHERE ticketid="
								+ company);
				while (rs.next()) {
					total += (double)rs.getInt(0);
				}
			}
			amtValues.put(company, total);
		}

		return amtValues;
	}
	
	
	
	
	public boolean saveInvoice(Map<String, Double> request,Map<String, Double> supportAgree)
			throws SQLException {
		conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		PreparedStatement ps;
		String query;
		query = " insert into invoicetable(companyID,invoiceCreated,invoiceFrom,invoiceTo,amount) values (?,current_timestamp,current_timestamp,current_timestamp,?)";
		ps = conn.prepareStatement(query);
		Iterator<String> comp = request.keySet().iterator();

		while (comp.hasNext()) {
			double total = 0;
			String val = comp.next();
			ps.setString(1, val);
			double tot = (request.get(val)*supportAgree.get(val));
			ps.setInt(2,(int)tot);
			ps.executeUpdate();
		}
		return true;
	}
	
	public Map<String, Double>  retrievesupportAgree() throws SQLException{
		Map<String, Double> supportAgree =  new HashMap<String, Double>();
		List<String> companyIds = new ArrayList<String>();
		conn = DriverManager.getConnection(connectionUrl, connectionUser,
				connectionPassword);
		stmt = conn.createStatement();

		rs = stmt.executeQuery("select companyID, perHourCharge from  supportAgreement");
		while (rs.next()) {
			supportAgree.put(rs.getString(1),(double) rs.getInt(2));
		}		
		return supportAgree;
	}
	
	public List<InvoiceTable> getInvoiceDetails() throws SQLException {
		List<InvoiceTable> invoice = new ArrayList<InvoiceTable>();
		conn = DriverManager.getConnection(connectionUrl, connectionUser,
				connectionPassword);
		stmt = conn.createStatement();
		 
		rs = stmt.executeQuery("select companyID,invoiceCreated,invoiceFrom,invoiceTo,amount from invoicetable");
		while (rs.next()) {
			InvoiceTable tab = new InvoiceTable();
			tab.setAmount((double)rs.getInt("amount"));
			tab.setInvoiceCreated(rs.getString("invoiceCreated"));
			tab.setCompanyID(rs.getString("companyID"));
			tab.setInvoiceFrom(rs.getString("invoiceFrom"));
			tab.setInvoiceTo(rs.getString("invoiceTo"));
			invoice.add(tab);
		}
		return invoice;
	}
	
	public static void main(String[] args) throws SQLException{
		
		InvoicedaoImpl dao = new InvoicedaoImpl();
		dao.createinvoice();
	}

}
