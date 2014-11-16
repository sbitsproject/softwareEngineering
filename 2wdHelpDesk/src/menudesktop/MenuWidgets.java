package menudesktop;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class MenuWidgets 
{
	//////////////////////////////////////////
	// Quick and dirty methods
	//////////////////////////////////////////
	public String getRaisedTickets()
	{
		String temp = "12";
		return temp;
	}
	public String getSolvedTickets()
	{
		String temp = "8";
		return temp;
	}
	
	public String getAssignedTickets()
	{
		String temp = "8";
		return temp;
	}
	public String getResolvedTickets()
	{
		String temp = "14";
		return temp;
	}
	public String getOpenTickets()
	{
		String temp = "18";
		return temp;
	}
	
	public String getQuickLinks()
	{
		String temp = "Create Ticket\nManage Tickets\nSee Payments\n";
		return temp;
	}

}
