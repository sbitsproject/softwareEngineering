package supportEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import uiBeans.InvoiceTable;

@ManagedBean
@SessionScoped
public class Invoiceval implements Serializable {

	
	
	private List<InvoiceTable> invoicelist = new ArrayList<InvoiceTable>();

	public List<InvoiceTable> getInvoicelist() {
		return invoicelist;
	}

	public void setInvoicelist(List<InvoiceTable> invoicelist) {
		this.invoicelist = invoicelist;
	}
	
	
}
