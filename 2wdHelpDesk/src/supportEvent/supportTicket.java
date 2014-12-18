package supportEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import uiBeans.SupportEvent;

//import sunw.io.Serializable;


public class supportTicket implements Serializable {

	String id = new String();
	String Client = new String();
	String Date = new String();
	String Assigned = new String();
	String Time;
	String PriorityState;
	private List<uiBeans.SupportEvent> list = new ArrayList<uiBeans.SupportEvent>();
	private List<String> assignList = new ArrayList<String>();
	
	public List<uiBeans.SupportEvent> getList() {
		return list;
	}
	public void setList(List<uiBeans.SupportEvent> list) {
		this.list = list;
	}
	public String getPriorityState() {
		return PriorityState;
	}
	public supportTicket(String randomId, String randomClient, String randomDate,
			String randomAssigned, String randomTime, String randomPriorityState, List<SupportEvent> sp,List<String> assingList) {
		this.id=randomId;
		this.Client=randomClient;
		this.Date=randomDate;
		this.Assigned=randomAssigned;
		this.Time=randomTime;
		this.PriorityState=randomPriorityState;
		this.list = sp;
		this.setAssignList(assingList);
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
	public String getTime() {
		return Time;
	}
	public void setTime(String Time) {
		this.Time = Time;
	}
	public String isPriorityState() {
		return PriorityState;
	}
	public void setPriorityState(String PriorityState) {
		this.PriorityState = PriorityState;
	}
	public String getPriority() {
		return PriorityState;
	}
	public void setPriority(String PriorityState) {
		this.PriorityState = PriorityState;
	}
	public List<String> getAssignList() {
		return assignList;
	}
	public void setAssignList(List<String> assignList) {
		this.assignList = assignList;
	}
		
}
