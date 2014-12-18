package mobileAction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import uiBeans.SupportEventTest;
import validation.Validation;
import beans.SaveTicketDetailsRequest;
import daoEntities.SupportEvent;
import daoEntities.TicketAssignment;
import daoImpl.SupportEventDao;
import daoImpl.TicketDaoImpl;

@ManagedBean
public class DisplayAction {

	private String adduser;
	
	
	
	public String getAdduser() {
		return adduser;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	public void checkSupportCreation() throws SQLException {
		System.out.println("came");
		Validation validation = (Validation) getSessionBean("validation");
		String user = validation.getUsername();
		String params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idVal");
		 
		MobileDisplay view = (MobileDisplay) getSessionBean("mobileDisplay");
		
    	TicketDaoImpl ticketDao = new TicketDaoImpl();
    	List<String> tic = ticketDao.getAssigmentDetails(params);
        if(tic.contains(user))
        {
        	System.out.println("IN");
        	SupportEvent support = new SupportEvent();
        	support.setCreatedBy(user);
        	support.setTicketID(params);
        	SupportEventDao supDao = new SupportEventDao();
        	supDao.insertSupportEvent(support);
        	List<uiBeans.SupportEvent> sp = new ArrayList<uiBeans.SupportEvent>();
        	for(SupportEvent as:supDao.getsuppportEvent(params)){
        		uiBeans.SupportEvent s = new uiBeans.SupportEvent();
        		s.setCreatedBy(as.getCreatedBy());
        		s.setSupportEventCreated(as.getSupportEventCreated());
        		s.setSupportEventEnded(as.getSupportEventEnded());
        		s.setSupportEventID(as.getSupportEventID());
        		s.setTicketID(as.getTicketID());
        		if(null!=as.getSupportEventEnded()){
        			s.setEnded(true);
        		}
        		sp.add(s);
        	}
        		
        	List<DisplayBean> tics = view.getDisplayList();
        	
        	for(DisplayBean ticOne:tics){
        		if(ticOne.getTicketId().equalsIgnoreCase(params)){
        			ticOne.setList(sp);
        			break;
        		}
        	}
        	view.setDisplayList(tics);
        	FacesContext context = FacesContext.getCurrentInstance();
        	context.getExternalContext().getSessionMap().put("mobileDisplay", view);
        	//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + username));
        }
        
        
    }
	
	public void stopSupportEvent() throws SQLException{
		Validation validation = (Validation) getSessionBean("validation");
		String user = validation.getUsername();
		String params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idVal1");
		String params1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idVal2");
		MobileDisplay view = (MobileDisplay) getSessionBean("supportFilterView");
		
    	TicketDaoImpl ticketDao = new TicketDaoImpl();
    	List<String> tic = null;
		try {
			tic = ticketDao.getAssigmentDetails(params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(tic.contains(user))
        {
        	SupportEvent support = new SupportEvent();
        	support.setSupportEventID(params1);
        	SupportEventDao supDao = new SupportEventDao();
        	try {
				supDao.updateSupportEvent(support);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	List<uiBeans.SupportEvent> sp = new ArrayList<uiBeans.SupportEvent>();
        	for(SupportEvent as:supDao.getsuppportEvent(params)){
        		uiBeans.SupportEvent s = new uiBeans.SupportEvent();
        		s.setCreatedBy(as.getCreatedBy());
        		s.setSupportEventCreated(as.getSupportEventCreated());
        		s.setSupportEventEnded(as.getSupportEventEnded());
        		s.setSupportEventID(as.getSupportEventID());
        		s.setTicketID(as.getTicketID());
        		if(null!=as.getSupportEventEnded()){
        			s.setEnded(true);
        		}
        		sp.add(s);
        	}
        		
        	List<DisplayBean> tics = view.getDisplayList();
        	
        	for(DisplayBean ticOne:tics){
        		if(ticOne.getTicketId().equalsIgnoreCase(params)){
        			ticOne.setList(sp);
        		}
        	}
        	view.setDisplayList(tics);
        	FacesContext context = FacesContext.getCurrentInstance();
        	context.getExternalContext().getSessionMap().put("mobileDisplay", view);
        }
	}
	
	
	
	public void assignUser(){
		SupportEventTest view = (SupportEventTest) getSessionBean("supportEventTest");
		
		String params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idVal2");
		TicketDaoImpl impl = new TicketDaoImpl();
		SaveTicketDetailsRequest request = new SaveTicketDetailsRequest();
		
		TicketAssignment ass= new  TicketAssignment();
		ass.setTicketid(params);
		ass.setUserid(view.getAddUser());
		request.getAssinmentList().add(ass);
		try {
			String h ="";
			impl.saveAssign(request);
			List<String> val = impl.getAssigmentDetails(params);
			MobileDisplay view1 = (MobileDisplay) getSessionBean("mobileDisplay");
			for(String str:val){
				h += str+",";
				int i=0;
				for(DisplayBean tic:view1.getDisplayList()){
					if(tic.getTicketId().equals(params)){
						view1.getDisplayList().get(i).setAssignList(val);
						break;
					}
					i++;
				}
				
			}
			int i=0;
			for(DisplayBean tic:view1.getDisplayList()){
				if(tic.getTicketId().equals(params)){
					view1.getDisplayList().get(i).setAssignedTo(h);;
					break;
				}
				i++;
			}
			FacesContext context = FacesContext.getCurrentInstance();
        	context.getExternalContext().getSessionMap().put("supportFilterView", view1);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deleteUser(){
		
		String params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idVal3");
		String params1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idVal4");
		TicketAssignment ass= new  TicketAssignment();
		ass.setTicketid(params);
		ass.setUserid(params1);
		TicketDaoImpl impl = new TicketDaoImpl();
		try {
			String h ="";
			impl.deleteAssign(ass);
			List<String> val = impl.getAssigmentDetails(params);
			MobileDisplay view1 = (MobileDisplay) getSessionBean("mobileDisplay");
			for(String str:val){
				h += str+",";
				int i=0;
				for(DisplayBean tic:view1.getDisplayList()){
					if(tic.getTicketId().equals(params)){
						view1.getDisplayList().get(i).setAssignList(val);
						break;
					}
					i++;
				}
				
			}
			int i=0;
			for(DisplayBean tic:view1.getDisplayList()){
				if(tic.getTicketId().equals(params)){
					view1.getDisplayList().get(i).setAssignedTo(h);;
					break;
				}
				i++;
			}
			FacesContext context = FacesContext.getCurrentInstance();
        	context.getExternalContext().getSessionMap().put("mobileDisplay", view1);
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Object getSessionBean(String sessionBeanName)
    {
        return FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, sessionBeanName);
    }
	
	
}
