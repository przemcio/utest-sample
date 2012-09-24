package pl.testng.ch10;



import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class CarSearchTest {

    private CarSearch carSearch;

    @BeforeTest
    public void setUp() {
        carSearch = new CarSearch();
    }

    @Test
    public void shouldReturnSportCars() {

        CarBuilder carBuilder = new CarBuilder();

        Manufacturer manufacturer = mock(Manufacturer.class);
        when(manufacturer.getName()).thenReturn("Ferrari");

        Engine engine = mock(Engine.class);
        when(engine.getNbOfCylinders()).thenReturn(8);

        carBuilder.setColor(Color.RED).setManufacturer(manufacturer).setEngine(engine);

        carSearch.addCar(carBuilder.build());

        List<Car> sportCars = carSearch.findSportCar();

        assertEquals(sportCars.size(),1);


    }

}
