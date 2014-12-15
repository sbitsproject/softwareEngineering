package supportEvent;

import java.io.*;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
//import org.primefaces.showcase.domain.Car;
//import org.primefaces.showcase.service.CarService;

@ManagedBean(name="supportFilterView")
@ViewScoped
public class FilterView implements Serializable {
     
    private List<Car> cars;
     
    private List<Car> filteredCars;
     
    private Car selectedCar;
    
    @ManagedProperty("#{carService2}")
    private CarService service;
    
    @PostConstruct
    public void init() {
        //cars = service.createCars(10);
    	//service = new CarService();
    	System.out.println("IN");
    	cars = service.createCars(30);
    	//filteredCars = cars;
    	System.out.println("IN");
    }
     
    public boolean filterByPrice(Object value, Object filter, Locale locale) {
    	System.out.println("OUT");
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
        System.out.println(((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0);
    	System.out.println("OUT");
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
     
    public List<String> getBrands() {
    	System.out.println(service.getClient());
    	System.out.println("OUT");
        return service.getClient();
    }
     
    public List<String> getColors() {
    	System.out.println(service.getAssigned());
    	System.out.println("OUT");
        return service.getAssigned();
    }
     
    public List<Car> getCars() {
        return cars;
    }
 
    public List<Car> getFilteredCars() {
        return filteredCars;
    }
 
    public void setFilteredCars(List<Car> filteredCars) {
        this.filteredCars = filteredCars;
    }
 
    public void setService(CarService service) {
        this.service = service;
    }
    
    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }
    public Car getSelectedCar() {
        return selectedCar;
    }
}