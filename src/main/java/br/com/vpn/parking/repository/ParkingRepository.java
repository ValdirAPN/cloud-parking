package br.com.vpn.parking.repository;

import br.com.vpn.parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {

    List<Parking> findAllByCarId(String carId);
}
