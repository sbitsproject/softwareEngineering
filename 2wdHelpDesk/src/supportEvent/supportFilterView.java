package supportEvent;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
//import org.primefaces.showcase.domain.Ticket;
//import org.primefaces.showcase.service.TicketService;
import javax.faces.model.SelectItem;

import uiBeans.SupportEventBean;
import uiBeans.User;
import daoImpl.TicketDaoImpl;

@ManagedBean(name="supportFilterView")
@ViewScoped
public class supportFilterView implements Serializable {
     
    private List<supportTicket> Tickets;
    private List<supportTicket> managedTickets;
    
    private List<supportTicket> filteredTickets;
    private List<supportTicket> filteredManagedTickets;
    
    private supportTicket selectedTicket;
    
    private String adduser;
    
    public String getAdduser() {
		return adduser;
	}
	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	@ManagedProperty("#{ticketService}")
    private supportTicketService service;
    
    List<SelectItem> userList = new ArrayList<SelectItem>();
    
    List<User> userList1 = new ArrayList<User>();
    
    public List<String> completeTxt(){
    	SupportEventBean s = (SupportEventBean)getSessionBean("supportEventBean");
            List<String> results = new ArrayList<String>();
            for(int i = 0; i < userList1.size(); i++) {
            	if(s.getAddUser().equalsIgnoreCase(userList1.get(i).getValue().substring(0, s.getAddUser().length()))){
            		results.add(userList1.get(i).getValue());	
            	}
                
            }
             
            return results;
        }
    
    public Object getSessionBean(String sessionBeanName)
    {
        return FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, sessionBeanName);
    }
    
    @PostConstruct
    public void init()  {
        //Tickets = service.createTickets(10);
    	//service = new TicketService();
    	//System.out.println("IN");
    	try {
    		Tickets = new ArrayList<supportTicket>();
			Tickets = service.createTickets(30);
			TicketDaoImpl impl = new TicketDaoImpl();
			try {
				for(String user:impl.getUserDetails()){
					SelectItem si = new SelectItem(user,user);
					User u = new User();
					u.setLabel(user);
					u.setValue(user);
					userList1.add(u);
					userList.add(si);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//filteredTickets = Tickets;
    	//System.out.println("IN");
    	/*try {
			managedTickets = service.createTickets(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
	public List<User> getUserList1() {
		return userList1;
	}
	public void setUserList1(List<User> userList1) {
		this.userList1 = userList1;
	}
	public List<SelectItem> getUserList() {
		return userList;
	}



	public void setUserList(List<SelectItem> userList) {
		this.userList = userList;
	}

     
    public void setTickets(List<supportTicket> tickets) {
		Tickets = tickets;
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