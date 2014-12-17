package supportEvent;

import java.io.*;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
//import org.primefaces.showcase.domain.Ticket;
//import org.primefaces.showcase.service.TicketService;

@ManagedBean(name="supportFilterView")
@ViewScoped
public class supportFilterView implements Serializable {
     
    private List<supportTicket> Tickets;
    private List<supportTicket> managedTickets;
    
    private List<supportTicket> filteredTickets;
    private List<supportTicket> filteredManagedTickets;
    
    private supportTicket selectedTicket;
    
    @ManagedProperty("#{ticketService}")
    private supportTicketService service;
    
    @PostConstruct
    public void init() {
        //Tickets = service.createTickets(10);
    	//service = new TicketService();
    	//System.out.println("IN");
    	Tickets = service.createTickets(30);
    	//filteredTickets = Tickets;
    	//System.out.println("IN");
    	managedTickets = service.createTickets(0);
    }
     
    public boolean filterByTime(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
     
    public List<String> getClients() {
        return service.getClient();
    }
     
    public List<String> getAssigned() {
        return service.getAssigned();
    }
    
    public List<String> getPriorityList() {
        return service.getPriorityList();
    }
     
    public List<supportTicket> getTickets() {
        return Tickets;
    }
    public List<supportTicket> getManagedTickets() {
        return managedTickets;
    }
 
    public List<supportTicket> getFilteredTickets() {
        return filteredTickets;
    }
    public List<supportTicket> getFilteredManagedTickets() {
        return filteredManagedTickets;
    }
 
    public void setFilteredTickets(List<supportTicket> filteredTickets) {
        this.filteredTickets = filteredTickets;
    }
 
    public void setService(supportTicketService service) {
        this.service = service;
    }
    
    public void setSelectedTicket(supportTicket selectedTicket) {
        this.selectedTicket = selectedTicket;
    }
    public supportTicket getSelectedTicket() {
        return selectedTicket;
    }
    
    public void deleteTicket() {
        Tickets.remove(selectedTicket);
        selectedTicket = null;
    }
    
    public void moveToManagedSupportEvent() {
    	managedTickets.add(selectedTicket);
        Tickets.remove(selectedTicket);
        selectedTicket = null;
    }
}