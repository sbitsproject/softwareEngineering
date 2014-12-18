package supportEvent;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import daoEntities.InvoiceTable;
import daoImpl.InvoicedaoImpl;

@ManagedBean
public class InvoiceAction {

	public void createInvoice() {
		try {

			InvoicedaoImpl dao = new InvoicedaoImpl();

			dao.createinvoice();
			displayInvoices();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void displayInvoices(){
		InvoicedaoImpl dao = new InvoicedaoImpl();

		Invoiceval val = (Invoiceval) getSessionBean("invoiceval");
		List<InvoiceTable> list = null;
		try {
			 list = dao.getInvoiceDetails();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(InvoiceTable table:list){
			uiBeans.InvoiceTable tab = new uiBeans.InvoiceTable();
			tab.setAmount(table.getAmount());
			tab.setCompanyID(table.getCompanyID());
			tab.setInvoiceCreated(table.getInvoiceCreated());
			tab.setInvoiceFrom(table.getInvoiceFrom());
			tab.setInvoiceTo(table.getInvoiceTo());
			val.getInvoicelist().add(tab);
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
    	context.getExternalContext().getSessionMap().put("invoiceval", val);
		
	}
	
	public Object getSessionBean(String sessionBeanName)
    {
        return FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, sessionBeanName);
    }
}
