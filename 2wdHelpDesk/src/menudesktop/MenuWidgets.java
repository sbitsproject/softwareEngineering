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
		String temp = "Tickets Raised By You No. of Raised: 12\nNo. of Solved: 8\n";
		return temp;
	}
	
	public String getAssignedTickets()
	{
		String temp = "Tickets Assigned To You\nNo. of Resolved Tickets: 12\nNo. of Open Tickets:\n    ";
		temp = temp + "High: 3\n    Medium: 6\n    Low: 15\n";
		return temp;
	}
	
	public String getQuickLinks()
	{
		String temp = "Create Ticket\nManage Tickets\nSee Payments\n";
		return temp;
	}

}
