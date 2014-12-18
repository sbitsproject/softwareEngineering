package daoEntities;

public class InvoiceTable {
	
	private String companyID;
	private String invoiceCreated ;
	private double amount;
	private String invoiceFrom;
	private String invoiceTo;
	public String getInvoiceFrom() {
		return invoiceFrom;
	}
	public void setInvoiceFrom(String invoiceFrom) {
		this.invoiceFrom = invoiceFrom;
	}
	public String getInvoiceTo() {
		return invoiceTo;
	}
	public void setInvoiceTo(String invoiceTo) {
		this.invoiceTo = invoiceTo;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getInvoiceCreated() {
		return invoiceCreated;
	}
	public void setInvoiceCreated(String invoiceCreated) {
		this.invoiceCreated = invoiceCreated;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	

}
