package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {
    private CarRepository carRepository;
    private Car car;

    @BeforeEach
    public void setUp() {
        carRepository = new CarRepository();
        car = Mockito.mock(Car.class);
        Mockito.when(car.getCarName()).thenReturn("Tesla Model S");
        Mockito.when(car.getCarColor()).thenReturn("Red");
        Mockito.when(car.getCarQuantity()).thenReturn(5);
    }

    @Test
    void testCreate() {
        Car createdCar = carRepository.create(car);
        assertEquals(car, createdCar);
    }

    @Test
     void testFindAll() {
        carRepository.create(car);
        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        assertEquals(car, carIterator.next());
    }

    @Test
     void testFindById() {
        Car createdCar = carRepository.create(car);
        Car foundCar = carRepository.findById(createdCar.getCarId());
        assertEquals(createdCar, foundCar);
    }

    @Test
     void testEdit() {
        Car createdCar = carRepository.create(car);
        Car updatedCar = Mockito.mock(Car.class);
        Mockito.when(updatedCar.getCarId()).thenReturn(createdCar.getCarId());
        Mockito.when(updatedCar.getCarName()).thenReturn("Tesla Model X");
        Mockito.when(updatedCar.getCarColor()).thenReturn("Blue");
        Mockito.when(updatedCar.getCarQuantity()).thenReturn(10);
        carRepository.edit(updatedCar);
        Car foundCar = carRepository.findById(createdCar.getCarId());
        assertEquals("Tesla Model X", foundCar.getCarName());
        assertEquals("Blue", foundCar.getCarColor());
        assertEquals(10, foundCar.getCarQuantity());
    }

    @Test
     void testDelete() {
        Car createdCar = carRepository.create(car);
        Car deletedCar = carRepository.delete(createdCar.getCarId());
        assertEquals(createdCar, deletedCar);
        assertNull(carRepository.findById(createdCar.getCarId()));
    }

    @Test
     void testValidateObject() {
        assertThrows(IllegalArgumentException.class, () -> carRepository.validateObject(null));
        Mockito.when(car.getCarName()).thenReturn("");
        assertThrows(IllegalArgumentException.class, () -> carRepository.validateObject(car));
        Mockito.when(car.getCarName()).thenReturn("Tesla Model S");
        Mockito.when(car.getCarColor()).thenReturn("");
        assertThrows(IllegalArgumentException.class, () -> carRepository.validateObject(car));
        Mockito.when(car.getCarColor()).thenReturn("Red");
        Mockito.when(car.getCarQuantity()).thenReturn(-1);
        assertThrows(IllegalArgumentException.class, () -> carRepository.validateObject(car));
    }
}