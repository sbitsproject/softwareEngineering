package uiBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import supportEvent.supportFilterView;

@ManagedBean(name="supportEventTest")
public class SupportEventTest implements Serializable {

	private String addUser;

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	
	 public List<String> completeTxt(){
		 supportFilterView s = (supportFilterView)getSessionBean("supportFilterView");
	            List<String> results = new ArrayList<String>();
	            for(int i = 0; i < s.getUserList1().size(); i++) {
	            	if(getAddUser().equalsIgnoreCase(s.getUserList1().get(i).getValue().substring(0, getAddUser().length()))){
	            		results.add(s.getUserList1().get(i).getValue());	
	            	}
	                
	            }
	             
	            return results;
	        }
	 public Object getSessionBean(String sessionBeanName)
	    {
	        return FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, sessionBeanName);
	    }
}
