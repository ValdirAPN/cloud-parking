package br.com.vpn.parking.controller;

import br.com.vpn.parking.dto.CarDTO;
import br.com.vpn.parking.dto.CarFormDTO;
import br.com.vpn.parking.dto.ParkingDTO;
import br.com.vpn.parking.mapper.CarMapper;
import br.com.vpn.parking.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cars")
@SecurityRequirement(name = "api")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @GetMapping
    @Operation(summary = "List all cars")
    public ResponseEntity<List<CarDTO>> findAll() {

        var carList = carService.findAll();
        var result = carMapper.toCarDTOList(carList);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(summary = "Create a new car")
    public ResponseEntity<CarDTO> create(@RequestBody CarFormDTO dto) {
        var carCreate = carMapper.carFormToCar(dto);
        var car = carService.create(carCreate);
        var result = carMapper.toCarDTO(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
