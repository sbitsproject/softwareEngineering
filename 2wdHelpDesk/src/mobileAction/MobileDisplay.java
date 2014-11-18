package mobileAction;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import beans.GetTicketDetailsRequest;
import beans.GetTicketDetailsResponse;
import daoEntities.Company;
import daoEntities.TicketAssignment;
import daoEntities.Tickets;
import daoImpl.TicketDaoImpl;


@ManagedBean
@SessionScoped
public class MobileDisplay implements Serializable {

	public List<DisplayBean> getDisplayList() {
		return displayList;
	}


	public void setDisplayList(List<DisplayBean> displayList) {
		this.displayList = displayList;
	}


	List<DisplayBean> displayList = new ArrayList<DisplayBean>();
	
	
	public String loadDisplay(){
		
		TicketDaoImpl impl = new TicketDaoImpl();
		GetTicketDetailsResponse response = null;
		try {
			response = impl.retrieveAllDetails(new GetTicketDetailsRequest());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Tickets ticket:response.getTicketList()){
			DisplayBean display = new DisplayBean();
			display.setTicketId(ticket.getId());
			display.setTicketDescription(ticket.getDescription());
			
			
			
			display.setTicketCreated(ticket.getTicketCreated().toString());
			if(null != ticket.getTicketEnded()){
				display.setTicketEnded(ticket.getTicketEnded().toGMTString());
			}else{
				display.setTimeElapsed((new Timestamp(System.currentTimeMillis()).getTime()-ticket.getTicketCreated().getTime())+"");
			}
			
			for(Company company:response.getCompanyList()){
				if(ticket.getCompany().equals(company.getId()))
				display.setComapanyName(company.getName());
			}
			
			for(TicketAssignment ticketAssign:response.getAssinmentList()){
				if(ticket.getId().equals(ticketAssign.getTicketid()))
				display.setAssignedTo(","+ticketAssign.getUserid()+",");;
			}
			displayList.add(display);
		}
		
		
		
		return "continue";
	}
	
}
