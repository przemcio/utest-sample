package pl.testng.ch10;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarSeach2Test {

    private CarSearch2 carSearch2;

    @BeforeTest
    public void setUp() {
        carSearch2 = new CarSearch2();
    }


    @Test
    public void shouldReturnSportCars() {

        Car2 sportCar = mock(Car2.class);
        when(sportCar.isSportCar()).thenReturn(true);


    }
}
