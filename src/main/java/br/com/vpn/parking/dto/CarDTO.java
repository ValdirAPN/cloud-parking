package br.com.vpn.parking.dto;

import br.com.vpn.parking.enums.Brand;

import javax.persistence.Entity;
import javax.persistence.Id;

public class CarDTO {

    private String id;
    private Brand brand;
    private String model;
    private String license;
}
