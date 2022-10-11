package br.com.vpn.parking.dto;

import br.com.vpn.parking.enums.Brand;

public class CarFormDTO {

    private Brand brand;
    private String model;
    private String license;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
