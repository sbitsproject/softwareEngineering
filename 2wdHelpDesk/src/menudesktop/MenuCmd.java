package menudesktop;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class MenuCmd 
{
	private String centerPanel = "dashboard.xhtml";

	public String gotoMyAccount()
	{
		return null;
	}
	
	public void gotoCreateTicket()
	{
		centerPanel = "new.xhtml";
	}
	
	public void changeCenterPanel()
	{
		String str = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("input");
		centerPanel = str;
	}
	
	public String signOut()
	{
		return "signout"; //see navigation rule in "WEB-INF/faces-config.xml"
	}
	
	public String getCenterPanel()
	{
		return centerPanel;
	}

}