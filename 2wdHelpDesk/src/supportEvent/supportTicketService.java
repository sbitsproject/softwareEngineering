package supportEvent;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
//import org.primefaces.showcase.domain.Ticket;








import beans.GetTicketDetailsRequest;
import beans.GetTicketDetailsResponse;
import daoEntities.Company;
import daoEntities.SupportEvent;
import daoEntities.TicketAssignment;
import daoEntities.Tickets;
import daoImpl.SupportEventDao;
import daoImpl.TicketDaoImpl;
import mobileAction.DisplayBean;
import mobileAction.MobileDisplay;


@ManagedBean(name = "ticketService")
@ApplicationScoped
public class supportTicketService implements Serializable {
     
    private final static String[] assigned;
     
    private final static String[] client;
    
    private final static String[] priorityList = {"High", "Medium", "Low"};
    
    private static String[] tickets;
    
    static {
    	assigned = new String[10];
    	assigned[0] = "Andrew";
        assigned[1] = "Larry";
        assigned[2] = "Steve";
        assigned[3] = "Jeff";
        assigned[4] = "Sheldon";
        assigned[5] = "Leonard";
        assigned[6] = "Penny";
        assigned[7] = "Howard";
        assigned[8] = "Amy";
        assigned[9] = "Will";
         
        client = new String[10];
        client[0] = "Atmel";
        client[1] = "Intel";
        client[2] = "Microchip";
        client[3] = "Microsystems";
        client[4] = "Inspironix";
        client[5] = "Najsoft";
        client[6] = "Oracle";
        client[7] = "Tech";
        client[8] = "Helix Inc.";
        client[9] = "Dijkstra";
    }
     
    public List<supportTicket> createTickets(int size) throws SQLException {
    	TicketDaoImpl dao = new TicketDaoImpl();
    	GetTicketDetailsResponse response = dao.retrieveAllDetails(new GetTicketDetailsRequest());
    	 List<supportTicket> list = new ArrayList<supportTicket>();
    	for(Tickets ticket:response.getTicketList()){
    		String id,compan = "",createdDate,assign = "",elapsedTime = "",priority;
			id =ticket.getId();
			priority = ticket.getPriority();
			createdDate = ticket.getTicketCreated().toString();
			if(null != ticket.getTicketEnded()){
				//display.setTicketEnded(ticket.getTicketEnded().toGMTString());
			}else{
				elapsedTime = new Timestamp(System.currentTimeMillis()).getTime()-ticket.getTicketCreated().getTime()+"";
			}
			
			for(Company company:response.getCompanyList()){
				if(ticket.getCompany().equals(company.getId()))
				compan = company.getName();
			}
			
			for(TicketAssignment ticketAssign:response.getAssinmentList()){
				if(ticket.getId().equals(ticketAssign.getTicketid()))
				assign += ticketAssign.getUserid()+",";
			}
			SupportEventDao supDao = new SupportEventDao();
			List<uiBeans.SupportEvent> sp = new ArrayList<uiBeans.SupportEvent>();
        	for(SupportEvent as:supDao.getsuppportEvent(id)){
        		uiBeans.SupportEvent s = new uiBeans.SupportEvent();
        		s.setCreatedBy(as.getCreatedBy());
        		s.setSupportEventCreated(as.getSupportEventCreated());
        		s.setSupportEventEnded(as.getSupportEventEnded());
        		s.setSupportEventID(as.getSupportEventID());
        		s.setTicketID(as.getTicketID());
        		if(null!=as.getSupportEventEnded()){
        			s.setEnded(true);
        		}
        		sp.add(s);
        	}
        	
			list.add(new supportTicket(id, compan, createdDate, assign, elapsedTime, priority,sp,dao.getAssigmentDetails(id)));
		}
        return list;
    }
     
    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
     
    private String getRandomDate() {
        return (int)(Math.random() * 11 + 1) + "-" + (int)(Math.random() * 28 + 1) + "-" + (2014) ;
    }
     
    private String getRandomAssigned() {
        return assigned[(int) (Math.random() * 10)];
    }
     
    private String getRandomClient() {
        return client[(int) (Math.random() * 10)];
    }
     
    public int getRandomTime() {
        return (int) (Math.random() * 100);
    }
     
    public String getRandomPriorityState() {
    	
        return (Math.random() > 0.5) ? "Low" : (Math.random() > 0.5) ? "Medium": "High";
    }
 
    public List<String> getAssigned() {
        return Arrays.asList(assigned);
    }
     
    public List<String> getClient() {
        return Arrays.asList(client);
    }
    
    public List<String> getPriorityList() {
        return Arrays.asList(priorityList);
    }
    
    //public List<String> getDB() {
    	//static String[] temp = MobileDisplay.getDisplayList();
        //return Arrays.asList(temp);
    //}
    
    
     
    
}
