package pl.testng.ch10;


import java.util.ArrayList;
import java.util.List;

public class CarSearch2 {

    private List<Car2> cars = new ArrayList<Car2>();


    public void addCar(Car2 car) {
        cars.add(car);
    }

    public List<Car2> findSportCar() {
        List<Car2> sportCars = new ArrayList<Car2>();

        for (Car2 car :cars) {
            if(car.isSportCar()) {
                sportCars.add(car);
            }
        }
        return sportCars;
    }

}

