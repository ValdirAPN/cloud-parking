package br.com.vpn.parking.service;

import br.com.vpn.parking.model.Car;
import br.com.vpn.parking.model.Parking;
import br.com.vpn.parking.repository.CarRepository;
import br.com.vpn.parking.util.IdUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    public Car create(Car car) {
        var id = IdUtil.getUUID();
        car.setId(id);

        carRepository.save(car);
        return car;
    }
}
