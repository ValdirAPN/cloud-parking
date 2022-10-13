package br.com.vpn.parking.model;

import br.com.vpn.parking.enums.Brand;

import javax.persistence.*;
import java.util.List;

@Entity
public class Car {

    @Id
    private String id;
    private Brand brand;
    private String model;
    private String color;
    private String license;
    @OneToMany(mappedBy = "car")
    private List<Parking> parkingList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public List<Parking> getParking() {
        return parkingList;
    }

    public void addParking(Parking parking) {
        this.parkingList.add(parking);
    }
}
