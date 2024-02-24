package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository implements RepositoryInterface<Car>{
    static int id = 0;

    private List<Car> carData = new ArrayList<>();

    @Override
    public Car create(Car car){
        validateObject(car);

        if (car.getCarId() == null) {
            UUID uuid = UUID.randomUUID();
            car.setCarId(uuid.toString());
        }
        carData.add(car);
        return car;
    }

    @Override
    public Iterator<Car> findAll(){
        return carData.iterator();
    }

    @Override
    public Car findById(String id) {
        for(Car car: carData){
            if (car.getCarId().equals(id)){
                return car;
            }
        }
        return null;
    }

    @Override
    public Car edit(Car updatedCar) {
        validateObject(updatedCar);

        String updatedCarId = updatedCar.getCarId();
        Car car = findById(updatedCarId);
        car.setCarName(updatedCar.getCarName());
        car.setCarQuantity(updatedCar.getCarQuantity());
        car.setCarColor(updatedCar.getCarColor());
        return null; // Handle the case where the car is not found
    }

    @Override
    public Car delete(String id) { 
        Car carToDelete = findById(id);
        carData.remove(carToDelete);
        return carToDelete;
    }

    @Override
    public void validateObject(Car car) {
        if (car == null 
        || car.getCarName() == null 
        || car.getCarName().isEmpty() 
        || car.getCarColor() == null 
        || car.getCarColor().isEmpty()
        ) {
            throw new IllegalArgumentException("Car should have a name, quantity, and color(s)");
        }
        else if (car.getCarQuantity() < 0) {
            throw new IllegalArgumentException("Car quantity should not be negative");
        }
    }
}
