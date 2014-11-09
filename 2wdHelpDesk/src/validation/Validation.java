package validation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
@ManagedBean
public class Validation 
{
    private connector.driver database = new connector.driver();
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
    	
    	String[] value_constraints = {password, username};
    	String[] column_constraints = {"password", "firstName"}; 
        //String validate_password = database.retrieve_from_table("user", password, "password");
    	String validate_password = database.retrieve_from_table("user", value_constraints, column_constraints);
        if(validate_password != null && validate_password.equals(password) == true)
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + username));
        else
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect Password or Username"));
        
    }
}