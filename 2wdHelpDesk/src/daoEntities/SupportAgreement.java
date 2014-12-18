package daoEntities;

public class SupportAgreement {

	private String companyID;
	private String supportType;
	private double perHourCharge;
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getSupportType() {
		return supportType;
	}
	public void setSupportType(String supportType) {
		this.supportType = supportType;
	}
	public double getPerHourCharge() {
		return perHourCharge;
	}
	public void setPerHourCharge(double perHourCharge) {
		this.perHourCharge = perHourCharge;
	}
	
	
}
