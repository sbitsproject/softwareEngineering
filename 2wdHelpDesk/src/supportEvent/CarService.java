package supportEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
//import org.primefaces.showcase.domain.Car;


@ManagedBean(name = "carService2")
@ApplicationScoped
public class CarService implements Serializable {
     
    private final static String[] assigned;
     
    private final static String[] client;
     
    static {
    	assigned = new String[10];
    	assigned[0] = "Andrew";
        assigned[1] = "Larry";
        assigned[2] = "Steve";
        assigned[3] = "Jeff";
        assigned[4] = "Sheldon";
        assigned[5] = "Leonard";
        assigned[6] = "Penny";
        assigned[7] = "Howard";
        assigned[8] = "Amy";
        assigned[9] = "Will";
         
        client = new String[10];
        client[0] = "Atmel";
        client[1] = "Intel";
        client[2] = "Microchip";
        client[3] = "Microsystems";
        client[4] = "Inspironix";
        client[5] = "Najsoft";
        client[6] = "Oracle";
        client[7] = "Tech";
        client[8] = "Helix Inc.";
        client[9] = "Dijkstra";
    }
     
    public List<Car> createCars(int size) {
        List<Car> list = new ArrayList<Car>();
        for(int i = 0 ; i < size ; i++) {
            list.add(new Car(getRandomId(), getRandomBrand(), getRandomYear(), getRandomColor(), getRandomPrice(), getRandomSoldState()));
        }
         
        return list;
    }
     
    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
     
    private String getRandomYear() {
        return (int)(Math.random() * 11 + 1) + "-" + (int)(Math.random() * 28 + 1) + "-" + (2014) ;
    }
     
    private String getRandomColor() {
        return assigned[(int) (Math.random() * 10)];
    }
     
    private String getRandomBrand() {
        return client[(int) (Math.random() * 10)];
    }
     
    public int getRandomPrice() {
        return (int) (Math.random() * 100);
    }
     
    public boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true: false;
    }
 
    public List<String> getAssigned() {
        return Arrays.asList(assigned);
    }
     
    public List<String> getClient() {
        return Arrays.asList(client);
    }
}