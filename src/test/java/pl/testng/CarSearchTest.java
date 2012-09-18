package pl.testng;



import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.testng.ch101.CarSearch;

public class CarSearchTest {

    private CarSearch carSearch;

    @BeforeTest
    public void setUp() {
        carSearch = new CarSearch();
    }

    @Test
    public void shouldReturnSportCars() {

        carSearch.findSportCar();

    }

}
