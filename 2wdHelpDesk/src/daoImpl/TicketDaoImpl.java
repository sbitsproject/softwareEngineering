package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.GetTicketDetailsRequest;
import beans.GetTicketDetailsResponse;
import beans.SaveTicketDetailsRequest;
import beans.SaveTicketDetailsResponse;
import daoEntities.Company;
import daoEntities.Devices;
import daoEntities.Softwares;
import daoEntities.TicketAssignment;
import daoEntities.Tickets;

public class TicketDaoImpl {
	
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	private String connectionUrl = "jdbc:mysql://localhost:3306/machine_learning";
	private String connectionUser = "root";
	private String connectionPassword = "";
	
	public TicketDaoImpl() 
	{
		
		try 
		{
			//	new com.mysql.jdbc.Driver();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdatabase?user=testuser&password=testpassword");
			
					} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
	}
	
	
	public GetTicketDetailsResponse retrieveAllDetails(GetTicketDetailsRequest request) throws SQLException{
		GetTicketDetailsResponse response = new GetTicketDetailsResponse();
		Tickets ticket = new Tickets();
		Devices device = new Devices();
		Softwares software = new Softwares();
		TicketAssignment assign = new TicketAssignment();
		Company company = new Company();
		conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		stmt = conn.createStatement();

		rs = stmt.executeQuery("select * from tickets");
		while(rs.next()){
			ticket = new Tickets();
			ticket.setCompany(rs.getString("company"));
			ticket.setDescription(rs.getString("description"));
			ticket.setDeviceIds(rs.getString("deviceIds"));
			ticket.setId(rs.getString("id"));
			ticket.setPriority(rs.getString("priority"));
			ticket.setTicketCreated(Timestamp.valueOf(rs.getString("ticketCreated")));
		//	ticket.setTicketEnded(Timestamp.valueOf(rs.getString("TicketEnded")));
			ticket.setSoftwareIds(rs.getString("softwareIds"));
			ticket.setTitle(rs.getString("title"));
			response.getTicketList().add(ticket);
		}
		
		rs = stmt.executeQuery("select * from devices");
		while(rs.next()){
			device = new Devices();
			device.setDevicetype(rs.getString("deviceType"));
			device.setId(rs.getString("id"));
			device.setManufacture(rs.getString("manufacture"));
			device.setModel(rs.getString("model"));
			device.setPurchaseDate(rs.getString("purchaseDate"));
			device.setSerialNumber(rs.getString("serialNumber"));
			device.setWarranty(rs.getString("warranty"));
			response.getDeviceList().add(device);
		}

		rs = stmt.executeQuery("select * from softwares");
		while(rs.next()){
			software = new Softwares();
			software.setId(rs.getString("id"));
			software.setManufacture(rs.getString("manufacture"));
			software.setPurchaseDate(rs.getString("purchaseDate"));
			software.setSerialNumber(rs.getString("serialNumber"));
			software.setVersion(rs.getString("version"));
			software.setWarranty(rs.getString("warranty"));
			response.getSoftwareList().add(software);
		}
		
		rs = stmt.executeQuery("select * from  ticketassignment");
		while(rs.next()){
			assign = new TicketAssignment();
			assign.setTicketid(rs.getString("ticketid"));
			assign.setUserid(rs.getString("userid"));
			response.getAssinmentList().add(assign);
		}
		
		rs = stmt.executeQuery("select * from  company");
		while(rs.next()){
			company = new Company();
			company.setAddressLine1(rs.getString("addressLine1"));
			company.setAddressLine2(rs.getString("addressLine2"));
			company.setCity(rs.getString("city"));
			company.setEmailID(rs.getString("emailId"));
			company.setName(rs.getString("name"));
			company.setPhoneNumber(rs.getString("phoneNumber"));
			company.setStateName(rs.getString("stateName"));
			company.setZipcode(rs.getString("zipcode"));
			response.getCompanyList().add(company);
		}
		return response;
	}
	
	public SaveTicketDetailsResponse saveDetails(SaveTicketDetailsRequest request) throws SQLException{
		
		SaveTicketDetailsResponse response = new SaveTicketDetailsResponse();
		
		PreparedStatement ps;
		
		String query;
		saveDevices(request);
		saveSoftwares(request);
		String companyId = saveCompany(request);
		
		List<Tickets> ticketList = new ArrayList<Tickets>();
		for(Tickets ticket:request.getTicketList()){
			ticket.setCompany(companyId);
			ticketList.add(ticket);
		}
		request.setTicketList(ticketList);	

		String ticketId = saveTicket(request);
		
		List<TicketAssignment> assinmentList = new ArrayList<TicketAssignment>();
		for(TicketAssignment assign:request.getAssinmentList()){
			assign.setTicketid(ticketId);
			assinmentList.add(assign);
		}
		request.setAssinmentList(assinmentList);
		saveAssign(request);
		
		return response;
	}


	private String saveTicket(SaveTicketDetailsRequest request)
			throws SQLException {
		conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		PreparedStatement ps;
		String query;
		query = " insert into tickets(company,title,priority,description,ticketCreated,ticketEnded,deviceIds,softwareIds) values (?,?,?,?,current_timestamp,null,?,?)";
		ps = conn.prepareStatement(query);
		for(Tickets ticket:request.getTicketList()){
			ps.setString(1, ticket.getCompany());
			ps.setString(2,ticket.getTitle());
			ps.setString(3,ticket.getPriority());
			ps.setString(4,ticket.getDescription());
		//	ps.setString(5,ticket.getTicketCreated().toGMTString());
			//ps.setString(5,null);
			ps.setString(5,ticket.getDeviceIds());
			ps.setString(6,ticket.getSoftwareIds());
			ps.executeUpdate();
		}
		query = "select LAST_INSERT_ID(ID) FROM tickets";
		String id = null;
		ResultSet rs = conn.createStatement().executeQuery(query);
		while(rs.next()){
			id = rs.getString(1);
		}
		return id;
	}


	private void saveAssign(SaveTicketDetailsRequest request)
			throws SQLException {
		conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		PreparedStatement ps;
		String query;
		query = " insert into ticketAssignment(ticketid,userid) values (?,?);";
		ps = conn.prepareStatement(query);
		for(TicketAssignment assign:request.getAssinmentList()){
			ps.setString(1, assign.getTicketid());
			ps.setString(2,assign.getUserid());
			ps.executeUpdate();
		}
	}


	private String saveCompany(SaveTicketDetailsRequest request)
			throws SQLException {
		conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		PreparedStatement ps;
		String query;
		query = "insert into company (name,addressLine1,addressLine2,city,stateName,zipcode,emailID,phoneNumber) values (?,?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(query);
		for(Company company:request.getCompanyList()){
			ps.setString(1, company.getName());
			ps.setString(2,company.getAddressLine1());
			ps.setString(3,company.getAddressLine2());
			ps.setString(4,company.getCity());
			ps.setString(5,company.getStateName());
			ps.setString(6,company.getZipcode());
			ps.setString(7,company.getEmailID());
			ps.setString(8,company.getPhoneNumber());
			ps.executeUpdate();
		}
		query = "select LAST_INSERT_ID(ID) FROM company";
		String id = null;
		ResultSet rs = conn.createStatement().executeQuery(query);
		while(rs.next()){
			id = rs.getString(1);
		}
		return id;
	}


	private String saveSoftwares(SaveTicketDetailsRequest request)
			throws SQLException {
		conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		PreparedStatement ps;
		String query;
		query = "insert into softwares(manufacture,version,serialNumber,purchaseDate,warranty) values (?,?,?,?,?)";
		ps = conn.prepareStatement(query);
		for(Softwares software:request.getSoftwareList()){
			ps.setString(1,software.getManufacture());
			ps.setString(2,software.getVersion());
			ps.setString(3,software.getSerialNumber());
			ps.setString(4,software.getPurchaseDate());
			ps.setString(5,software.getWarranty());
			ps.executeUpdate();
		}
		query = "select LAST_INSERT_ID(ID) FROM softwares";
		String id = null;
		ResultSet rs = conn.createStatement().executeQuery(query);
		while(rs.next()){
			id = rs.getString(1);
		}
		return id;
	}


	private String saveDevices(SaveTicketDetailsRequest request)
			throws SQLException {
		conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		PreparedStatement ps;
		String query = "insert into devices (devicetype, manufacture, model, serialNumber, purchaseDate, warranty) values (?,?,?,?,?,?)";
		
		 
		ps = conn.prepareStatement(query);
		for(Devices device:request.getDeviceList()){
			ps.setString(1, device.getDevicetype());
			ps.setString(2,device.getManufacture());
			ps.setString(3,device.getModel());
			ps.setString(4,device.getSerialNumber());
			ps.setString(5,device.getPurchaseDate());
			ps.setString(6,device.getWarranty());
			ps.executeUpdate();
		}
		query = "select LAST_INSERT_ID(ID) FROM DEVICES";
		String id = null;
		ResultSet rs = conn.createStatement().executeQuery(query);
		while(rs.next()){
			id = rs.getString(1);
		}
		return id;
	}
	
	public static void main(String[] args){
		
		/*SaveTicketDetailsRequest request = new SaveTicketDetailsRequest();
		Tickets ticket = new Tickets();
		ticket.setCompany( "1");
		ticket.setDescription( "description");
		ticket.setDeviceIds( "deviceIds");
		ticket.setPriority( "priority");
		//ticket.setTicketCreated( new Timestamp(arg0));
		//ticket.setTicketEnded(null);
		ticket.setSoftwareIds( "softwareIds");
		ticket.setTitle( "title");
		request.getTicketList().add(ticket);
		
		Devices device = new Devices();
		device.setDevicetype( "deviceType");
		device.setId( "id");
		device.setManufacture( "manufacture");
		device.setModel( "model");
		device.setPurchaseDate( "purchase");
		device.setSerialNumber( "serialNumber");
		device.setWarranty( "warranty");
		request.getDeviceList().add(device);
		
		Company company = new Company();
		company.setAddressLine1( "addressLine1");
		company.setAddressLine2( "addressLine2");
		company.setCity( "city");
		company.setEmailID( "emailId");
		company.setName( "name");
		company.setPhoneNumber( "phoneNumber");
		company.setStateName( "stateName");
		company.setZipcode( "zipcode");
		request.getCompanyList().add(company);
		
		Softwares software = new Softwares();
		software.setId( "id");
		software.setManufacture( "manufacture");
		software.setPurchaseDate( "purchase");
		software.setSerialNumber( "serialNumber");
		software.setVersion( "version");
		software.setWarranty( "warranty");
		request.getSoftwareList().add(software);
		
		TicketAssignment assign = new TicketAssignment();
		assign.setTicketid( "ticketid");
		assign.setUserid( "1");
		request.getAssinmentList().add(assign);*/
		
		TicketDaoImpl impl = new TicketDaoImpl();
		/*try {
			impl.saveDetails(request);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		try {
			impl.retrieveAllDetails(new GetTicketDetailsRequest());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
