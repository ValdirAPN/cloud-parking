package br.com.vpn.parking.repository;

import br.com.vpn.parking.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {
}
