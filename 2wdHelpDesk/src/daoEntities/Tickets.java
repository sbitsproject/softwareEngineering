package daoEntities;

import java.sql.Timestamp;

public class Tickets  {

	 private String id;
	 private String company;
	 private String title;
	 private String priority;
	 private String description;
	 private Timestamp ticketCreated;
	 private Timestamp ticketEnded;
	 private String deviceIds;
	 private String softwareIds;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getTicketCreated() {
		return ticketCreated;
	}
	public void setTicketCreated(Timestamp ticketCreated) {
		this.ticketCreated = ticketCreated;
	}
	public Timestamp getTicketEnded() {
		return ticketEnded;
	}
	public void setTicketEnded(Timestamp ticketEnded) {
		this.ticketEnded = ticketEnded;
	}
	public String getDeviceIds() {
		return deviceIds;
	}
	public void setDeviceIds(String deviceIds) {
		this.deviceIds = deviceIds;
	}
	public String getSoftwareIds() {
		return softwareIds;
	}
	public void setSoftwareIds(String softwareIds) {
		this.softwareIds = softwareIds;
	}
	
	 
}
