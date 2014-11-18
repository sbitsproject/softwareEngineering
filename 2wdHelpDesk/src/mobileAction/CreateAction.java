package mobileAction;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import daoEntities.Company;
import daoImpl.TicketDaoImpl;
import uiBeans.Devices;
import uiBeans.Softwares;
import uiBeans.TicketAssignment;
import uiBeans.Tickets;
import beans.SaveTicketDetailsRequest;

@ManagedBean
@SessionScoped
public class CreateAction {


	
	
	public String goToCreateStep1(){
		System.out.println("Step 1");
		MenuAction menu = (MenuAction) getSessionBean("menuAction");
		menu.setFlag(false);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("tickets", new Tickets());
		context.getExternalContext().getSessionMap().put("devices", new Devices());
		context.getExternalContext().getSessionMap().put("softwares", new Softwares());
		context.getExternalContext().getSessionMap().put("company", new Company());
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
		SaveTicketDetailsRequest request = new SaveTicketDetailsRequest();
		Tickets ticket = (Tickets) getSessionBean("tickets");
		Devices device = (Devices) getSessionBean("devices");
		Softwares software = (Softwares) getSessionBean("softwares");
		TicketAssignment assing = (TicketAssignment) getSessionBean("ticketAssignment");
		uiBeans.Company company = (uiBeans.Company) getSessionBean("company"); 
		
		daoEntities.Tickets ticke = new daoEntities.Tickets();
		daoEntities.TicketAssignment ass = new daoEntities.TicketAssignment();
		daoEntities.Company compa = new Company();
		daoEntities.Softwares soft = new daoEntities.Softwares();
		daoEntities.Devices dev = new daoEntities.Devices();
		
		ticke.setComments(ticket.getComments());
		ticke.setDescription(ticket.getDescription());
		ticke.setPriority(ticket.getPriority());
		ticke.setTitle(ticket.getTitle());
		ticke.setType(ticket.getType());
		ass.setUserid(assing.getUserid());
		
		compa.setAddressLine1(company.getAddressLine1());
		compa.setAddressLine2(company.getAddressLine2());
		compa.setCity(company.getCity());
		compa.setEmailID(company.getEmailID());
		compa.setName(company.getName());
		compa.setPhoneNumber(company.getPhoneNumber());
		compa.setStateName(company.getStateName());
		compa.setZipcode(company.getZipcode());
		
		dev.setDevicetype(device.getDevicetype());
		dev.setManufacture(device.getManufacture());
		dev.setModel(device.getModel());
		dev.setPurchaseDate(device.getPurchaseDate());
		dev.setSerialNumber(device.getSerialNumber());
		dev.setWarranty(device.getWarranty());
		
		soft.setManufacture(software.getManufacture());
		soft.setPurchaseDate(software.getPurchaseDate());
		soft.setSerialNumber(software.getSerialNumber());
		soft.setVersion(software.getVersion());
		soft.setWarranty(software.getWarranty());
		
		request.getAssinmentList().add(ass);
		request.getCompanyList().add(compa);
		request.getDeviceList().add(dev);
		request.getSoftwareList().add(soft);
		request.getTicketList().add(ticke);
		
		TicketDaoImpl impl = new TicketDaoImpl();
		try {
			impl.saveDetails(request);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MenuAction menu = (MenuAction) getSessionBean("menuAction");
		menu.setFlag(true);
		return "done";
	}
	
	public Object getSessionBean(String sessionBeanName)
    {
        return FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, sessionBeanName);
    }
	
	
}
