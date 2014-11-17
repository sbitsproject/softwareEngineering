package beans;

import java.util.ArrayList;
import java.util.List;

import daoEntities.Company;
import daoEntities.Devices;
import daoEntities.Softwares;
import daoEntities.TicketAssignment;
import daoEntities.Tickets;

public class GetTicketDetailsResponse {

	private List<Tickets> ticketList = new ArrayList<Tickets>();
	
	private List<Softwares> softwareList = new ArrayList<Softwares>();
	
	private List<Devices> deviceList = new ArrayList<Devices>();
	
	private List<TicketAssignment> assinmentList = new ArrayList<TicketAssignment>();

	private List<Company> companyList = new ArrayList<Company>();

	
	public List<Tickets> getTicketList() {
		return ticketList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	public void setTicketList(List<Tickets> ticketList) {
		this.ticketList = ticketList;
	}

	public List<Softwares> getSoftwareList() {
		return softwareList;
	}

	public void setSoftwareList(List<Softwares> softwareList) {
		this.softwareList = softwareList;
	}

	public List<Devices> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<Devices> deviceList) {
		this.deviceList = deviceList;
	}

	public List<TicketAssignment> getAssinmentList() {
		return assinmentList;
	}

	public void setAssinmentList(List<TicketAssignment> assinmentList) {
		this.assinmentList = assinmentList;
	}
	
	
	
}
