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
public class FilterView implements Serializable {
     
    private List<Ticket> Tickets;
     
    private List<Ticket> filteredTickets;
     
    private Ticket selectedTicket;
    
    @ManagedProperty("#{ticketService}")
    private TicketService service;
    
    @PostConstruct
    public void init() {
        //Tickets = service.createTickets(10);
    	//service = new TicketService();
    	System.out.println("IN");
    	Tickets = service.createTickets(30);
    	//filteredTickets = Tickets;
    	System.out.println("IN");
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
    	System.out.println(service.getClient());
    	System.out.println("OUT");
        return service.getClient();
    }
     
    public List<String> getAssigned() {
    	System.out.println(service.getAssigned());
    	System.out.println("OUT");
        return service.getAssigned();
    }
     
    public List<Ticket> getTickets() {
        return Tickets;
    }
 
    public List<Ticket> getFilteredTickets() {
        return filteredTickets;
    }
 
    public void setFilteredTickets(List<Ticket> filteredTickets) {
        this.filteredTickets = filteredTickets;
    }
 
    public void setService(TicketService service) {
        this.service = service;
    }
    
    public void setSelectedTicket(Ticket selectedTicket) {
        this.selectedTicket = selectedTicket;
    }
    public Ticket getSelectedTicket() {
        return selectedTicket;
    }
    
    public void deleteTicket() {
        Tickets.remove(selectedTicket);
        selectedTicket = null;
    }
}