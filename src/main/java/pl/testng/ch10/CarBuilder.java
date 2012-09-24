package pl.testng.ch10;


import java.awt.*;

public class CarBuilder {

    Color color;
    Manufacturer manufacturer;
    Engine engine;

    public Color getColor() {
        return color;
    }

    public CarBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public CarBuilder setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;

        return this;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Car build() {


        return new MyCar(color,manufacturer,engine);

    }


}