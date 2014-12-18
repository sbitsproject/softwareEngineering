package uiBeans;

import java.io.Serializable;

public class SupportEvent implements Serializable{

	private String supportEventID ;
	private String createdBy ;
	private String supportEventCreated;
	private String supportEventEnded ;
	private String TicketID ;
	private boolean ended;
	
	public boolean isEnded() {
		return ended;
	}
	public void setEnded(boolean ended) {
		this.ended = ended;
	}
	public String getSupportEventID() {
		return supportEventID;
	}
	public void setSupportEventID(String supportEventID) {
		this.supportEventID = supportEventID;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getSupportEventCreated() {
		return supportEventCreated;
	}
	public void setSupportEventCreated(String supportEventCreated) {
		this.supportEventCreated = supportEventCreated;
	}
	public String getSupportEventEnded() {
		return supportEventEnded;
	}
	public void setSupportEventEnded(String supportEventEnded) {
		this.supportEventEnded = supportEventEnded;
	}
	public String getTicketID() {
		return TicketID;
	}
	public void setTicketID(String ticketID) {
		TicketID = ticketID;
	}
	
	
	
}
