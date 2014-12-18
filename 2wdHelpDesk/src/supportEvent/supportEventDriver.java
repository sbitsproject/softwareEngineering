package supportEvent;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import validation.Validation;


//import com.mysql.jdbc.Driver;

@ManagedBean (name="supportEventDriver1")
@ViewScoped
public class supportEventDriver {
	

	String ticketId;
	String userId;

	
	public supportEventDriver()
	{
		
	}
	
	
	
	public String getTicketId() {
        return ticketId;
    }
 
    public void setTicketId(String username) {
        this.ticketId = username;
    }
 
    public String getAssigned() {
        return userId;
    }
 
    public void setAssign(String password) {
        this.userId = password;
    }
	
	
	public boolean checkSupportCreation() {
		System.out.println("came");
		Validation validation = (Validation) getSessionBean("validation");
		String user = validation.getUsername();
		String params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idVal");
		 
			
    	String assignedUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("assUser");
    	String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        //String validate_password = database.retrieve_from_table("user", password, "password");
    	//String validate_password = database.retrieve_from_table("ticketassignment", value_constraints, column_constraints);
        if(assignedUser != null && user != null && assignedUser.equals(user) == true && assignedUser.equals(null) == false && user.equals(null) == false)
        {
        	System.out.println("IN");
        	System.out.println(assignedUser + ", " + user);
        	//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + username));
        	return true;
        }
        else
        {
        	System.out.println(assignedUser + " " + user);
        	return false;
        }
        
        
    }
	
	public boolean supportCreation(String ticket, String user) {
    	connector.driver database = new connector.driver();
    	String[] value_constraints = {ticketId, userId};
    	String[] column_constraints = {"ticketid", "userid"};
        //String validate_password = database.retrieve_from_table("user", password, "password");
    	String validate_password = database.retrieve_from_table("ticketassignment", value_constraints, column_constraints);
        if(validate_password != null && validate_password.equals(ticketId) == true)
        {
        	//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + username));
        	return true;
        }
        else
        {
        	return false;
        }
        
        
    }
	
	public Object getSessionBean(String sessionBeanName)
    {
        return FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, sessionBeanName);
    }
}
