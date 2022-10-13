package br.com.vpn.parking.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
public class Parking {

    @Id
    private String id;
    @ManyToOne()
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill;

    public Parking() {}

    private void calculateBill() {
        var basePrice = 5.0;
        var pricePerHour = 2.0;
        var pricePerMinute = pricePerHour / 60;
        var timeOfUse = ChronoUnit.MINUTES.between(entryDate, exitDate);

        setBill(basePrice + (timeOfUse * pricePerMinute));
    }

    public boolean isActive() {
        return exitDate == null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDateTime exitDate) {

        this.exitDate = exitDate;
        this.calculateBill();
    }

    public Double getBill() {
        return bill;
    }

    private void setBill(Double bill) {
        this.bill = bill;
    }
}
