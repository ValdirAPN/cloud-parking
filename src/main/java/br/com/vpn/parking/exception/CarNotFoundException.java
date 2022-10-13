package br.com.vpn.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String id) {
        super("Car not found with Id: " + id);
    }
}
