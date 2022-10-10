package br.com.vpn.parking.service;

import br.com.vpn.parking.exception.ParkingNotFoundException;
import br.com.vpn.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap();

    public List<Parking> findAll() {
        return new ArrayList<>(parkingMap.values());
    }

    public Parking findById(String id) {
        var parking = parkingMap.get(id);

        if (parking == null) {
            throw new ParkingNotFoundException(id);
        }

        return parking;
    }

    public Parking create(Parking parkingCreate) {
        String id = getUUID();
        parkingCreate.setId(id);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(id, parkingCreate);

        return parkingCreate;
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    private static String getUUID() {

        return UUID.randomUUID().toString().replace("-", "");
    }

    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parkingMap.replace(id, parking);
        return parking;
    }


    public Parking exit(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parkingMap.replace(id, parking);
        return parking;
    }
}
