package br.com.vpn.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CarAlreadyParkedException extends RuntimeException {

    public CarAlreadyParkedException(String id) {
        super("Car with Id: " + id + " is already parked.");
    }
}
