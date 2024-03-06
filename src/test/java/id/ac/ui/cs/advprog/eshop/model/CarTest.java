package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    private Car car;

    @BeforeEach
    public void setUp() {
        car = new Car();
    }

    @Test
    public void testSetAndGetCarId() {
        String carId = "CAR123";
        car.setCarId(carId);
        assertEquals(carId, car.getCarId());
    }

    @Test
    public void testSetAndGetCarName() {
        String carName = "Tesla Model S";
        car.setCarName(carName);
        assertEquals(carName, car.getCarName());
    }

    @Test
    public void testSetAndGetCarColor() {
        String carColor = "Red";
        car.setCarColor(carColor);
        assertEquals(carColor, car.getCarColor());
    }

    @Test
    public void testSetAndGetCarQuantity() {
        int carQuantity = 5;
        car.setCarQuantity(carQuantity);
        assertEquals(carQuantity, car.getCarQuantity());
    }
}