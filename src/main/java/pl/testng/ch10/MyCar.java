package pl.testng.ch10;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: przemcio
 * Date: 19.09.12
 * Time: 21:51
 * To change this template use File | Settings | File Templates.
 */
public class MyCar implements Car {

    Color carColor;
    Manufacturer carManufacturer;
    Engine carEngine;

    public MyCar(Color color, Manufacturer manufacturer, Engine engine) {
                   carColor = color;
        carManufacturer = manufacturer;
        carEngine = engine;

    }

    @Override
    public Engine getEngine() {
        return carEngine;
    }

    @Override
    public Color getColor() {
        return carColor;
    }

    @Override
    public Manufacturer getManufacture() {
        return carManufacturer;
    }
}
