package br.com.vpn.parking.controller;

import br.com.vpn.parking.dto.ParkingFormDTO;
import br.com.vpn.parking.dto.ParkingDTO;
import br.com.vpn.parking.mapper.ParkingMapper;
import br.com.vpn.parking.service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@SecurityRequirement(name = "api")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @Operation(summary = "List all parking lots")
    public ResponseEntity<List<ParkingDTO>> findAll() {

        var parkingList = parkingService.findAll();
        var result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a parking by its id")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        var parking = parkingService.findById(id);
        var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a parking by its id")
    public ResponseEntity delete(@PathVariable String id) {
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Create a new parking")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingFormDTO dto) {
        var parkingCreate = parkingMapper.parkingFormToParking(dto);
        var parking = parkingService.create(parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{id}")
    @Operation(summary = "Exit from parking")
    public ResponseEntity checkout(@PathVariable String id) {
        var parking = parkingService.checkout(id);
        return ResponseEntity.ok(parking);
    }
}
