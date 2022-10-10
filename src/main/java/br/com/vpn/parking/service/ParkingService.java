package br.com.vpn.parking.service;

import br.com.vpn.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap();

    static {
        var id1 = getUUID();
        var id2 = getUUID();
        var parking1 = new Parking(id1, "DMS-1111", "SC", "CELTA", "PRETO");
        var parking2 = new Parking(id2, "WAS-1234", "SP", "VW GOL", "VERMELHO");
        parkingMap.put(id1, parking1);
        parkingMap.put(id2, parking2);
    }

    public List<Parking> findAll() {
        return new ArrayList<>(parkingMap.values());
    }

    public Parking findById(String id) {
        return parkingMap.get(id);
    }

    public Parking create(Parking parkingCreate) {
        String id = getUUID();
        parkingCreate.setId(id);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(id, parkingCreate);

        return parkingCreate;
    }

    private static String getUUID() {

        return UUID.randomUUID().toString().replace("-", "");
    }
}
