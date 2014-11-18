package mobileAction;

import java.io.Serializable;

public class DisplayBean implements Serializable {

	
	private String ticketId;
	
	private String ticketDescription;
	
	private String assignedTo;
	
	private String ticketCreated;
	
	private String ticketEnded;
	
	private String timeElapsed;
	
	private String comapanyName;

	public String getComapanyName() {
		return comapanyName;
	}

	public void setComapanyName(String comapanyName) {
		this.comapanyName = comapanyName;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getTicketCreated() {
		return ticketCreated;
	}

	public void setTicketCreated(String ticketCreated) {
		this.ticketCreated = ticketCreated;
	}

	public String getTicketEnded() {
		return ticketEnded;
	}

	public void setTicketEnded(String ticketEnded) {
		this.ticketEnded = ticketEnded;
	}

	public String getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(String timeElapsed) {
		this.timeElapsed = timeElapsed;
	}
	
	
	
	
	
	
}
