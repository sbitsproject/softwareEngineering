package menudesktop;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class UserAccount 
{

	public String gotoMyAccount()
	{
		return null;
	}
	
	public String signOut()
	{
		return "signout"; //see navigation rule in "WEB-INF/faces-config.xml"
	}

}
