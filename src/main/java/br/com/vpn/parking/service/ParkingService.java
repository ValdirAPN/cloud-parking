package br.com.vpn.parking.service;

import br.com.vpn.parking.exception.CarAlreadyParkedException;
import br.com.vpn.parking.exception.CarNotFoundException;
import br.com.vpn.parking.exception.ParkingNotFoundException;
import br.com.vpn.parking.model.Parking;
import br.com.vpn.parking.repository.CarRepository;
import br.com.vpn.parking.repository.ParkingRepository;
import br.com.vpn.parking.util.IdUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;
    private final CarService carService;

    public ParkingService(ParkingRepository parkingRepository, CarService carService) {
        this.parkingRepository = parkingRepository;
        this.carService = carService;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll() {
        return new ArrayList<>(parkingRepository.findAll());
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Parking findById(String id) {
        return parkingRepository.findById(id)
                .orElseThrow(() -> new ParkingNotFoundException(id));
    }

    @Transactional
    public Parking create(Parking parkingCreate) {
        var car = carService.findById(parkingCreate.getCar().getId());
        var hasActiveParking = car.hasActiveParking();
        if (hasActiveParking) throw new CarAlreadyParkedException(car.getId());

        var id = IdUtil.getUUID();
        parkingCreate.setId(id);
        parkingCreate.setEntryDate(LocalDateTime.now());

        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }

    @Transactional
    public Parking checkout(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parkingRepository.save(parking);
        return parking;
    }

    public List<Parking> findByCarId(String id) throws CarNotFoundException {
        return parkingRepository.findAllByCarId(id);
    }


}
