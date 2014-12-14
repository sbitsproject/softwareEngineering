package supportEvent;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

//import sunw.io.Serializable;


public class Car implements Serializable {

	String id = new String();
	String Brand = new String();
	int year;
	String color = new String();
	int price;
	boolean soldState;
	public Car(String randomId, String randomBrand, int randomYear,
			String randomColor, int randomPrice, boolean randomSoldState) {
		this.id=randomId;
		this.Brand=randomBrand;
		this.year=randomYear;
		this.color=randomColor;
		this.price=randomPrice;
		this.soldState=randomSoldState;
		// TODO Auto-generated constructor stu
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isSoldState() {
		return soldState;
	}
	public void setSoldState(boolean soldState) {
		this.soldState = soldState;
	}
	public boolean getSold() {
		return soldState;
	}
	public void setSold(boolean soldState) {
		this.soldState = soldState;
	}
		
}
