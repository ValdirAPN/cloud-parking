package br.com.vpn.parking.service;

import br.com.vpn.parking.exception.CarNotFoundException;
import br.com.vpn.parking.model.Car;
import br.com.vpn.parking.repository.CarRepository;
import br.com.vpn.parking.util.IdUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll() {
        return new ArrayList<>(carRepository.findAll());
    }

    public Car findById(String id) { return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id)); }

    public Car create(Car car) {
        var id = IdUtil.getUUID();
        car.setId(id);

        carRepository.save(car);
        return car;
    }

    public void delete(String id) {
        findById(id);
        carRepository.deleteById(id);
    }
}
