package menudesktop;

import javax.faces.bean.ManagedBean;

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
	
	public String signOut()
	{
		return "signout"; //see navigation rule in "WEB-INF/faces-config.xml"
	}
	
	public String getCenterPanel()
	{
		return centerPanel;
	}

}