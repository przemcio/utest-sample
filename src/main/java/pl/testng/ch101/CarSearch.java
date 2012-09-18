package pl.testng.ch101;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CarSearch {

private List<Car> cars = new ArrayList<Car>();


    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> findSportCar() {
        List<Car> sportCars = new ArrayList<Car>();

        for (Car car :cars) {
            if(car.getEngine().getNbOfCylinders() > 6
                    && Color.RED.equals(car.getColor())
                    && "Ferrari".equals(car.getManufacture().getName())) {
                sportCars.add(car);
            }
        }
        return sportCars;
    }
}

