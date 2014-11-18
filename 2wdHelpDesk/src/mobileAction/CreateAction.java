package mobileAction;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import uiBeans.Tickets;

@ManagedBean
@SessionScoped
public class CreateAction {


	
	
	public String goToCreateStep1(){
		System.out.println("Step 1");
		return "step1";
	}
	
	public String goToCreateStep2(){
		System.out.println("Step 2");
		return "step2";
	}
	
	public String goToCreateStep3(){
		System.out.println("Step 3");
		return "step3";
	}
	
	public String goToCreateStep4(){
		System.out.println("Step 4");
		Tickets ticket = (Tickets) getSessionBean("tickets");
		System.out.println("------------------>"+ticket.getPriority());
		return "step4";
	}

	
	public String saveAll(){
		
		
		return "step4";
	}
	
	public Object getSessionBean(String sessionBeanName)
    {
        return FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, sessionBeanName);
    }
	
	
}
