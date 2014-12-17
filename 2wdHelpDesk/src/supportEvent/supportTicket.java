package supportEvent;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

//import sunw.io.Serializable;


public class supportTicket implements Serializable {

	String id = new String();
	String Client = new String();
	String Date = new String();
	String Assigned = new String();
	int Time;
	boolean PriorityState;
	public supportTicket(String randomId, String randomClient, String randomDate,
			String randomAssigned, int randomTime, boolean randomPriorityState) {
		this.id=randomId;
		this.Client=randomClient;
		this.Date=randomDate;
		this.Assigned=randomAssigned;
		this.Time=randomTime;
		this.PriorityState=randomPriorityState;
		// TODO Auto-generated constructor stu
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClient() {
		return Client;
	}
	public void setClient(String Client) {
		this.Client = Client;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String Date) {
		this.Date = Date;
	}
	public String getAssigned() {
		return Assigned;
	}
	public void setAssigned(String Assigned) {
		this.Assigned = Assigned;
	}
	public int getTime() {
		return Time;
	}
	public void setTime(int Time) {
		this.Time = Time;
	}
	public boolean isPriorityState() {
		return PriorityState;
	}
	public void setPriorityState(boolean PriorityState) {
		this.PriorityState = PriorityState;
	}
	public boolean getPriority() {
		return PriorityState;
	}
	public void setPriority(boolean PriorityState) {
		this.PriorityState = PriorityState;
	}
		
}
