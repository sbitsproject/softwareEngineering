package validation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
@ManagedBean
public class Validation 
{
     
    private String username;
    private String password;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public void login() {
    	connector.driver database = new connector.driver();
        String validate_password = database.retrieve_from_table("user", password, "password");
        if(validate_password != null && validate_password.equals(password) == true)
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + username));
        else
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect Password or Username"));
        
    }
}