package com.group.libraryapp.domain.homework;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long price;
    @Transient
    private String status;
    private String sell_status;

//    @Transient
    private LocalDate warehousingDate;


    public Fruit(final String name, final long price, final String status, final String sell_status) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.sell_status = sell_status;
    }

    public Fruit(final String name, final LocalDate warehousingDate, final long price) {
        this.name = name;
        this.warehousingDate = warehousingDate;
        this.price = price;
    }

    public Fruit() {

    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getSell_status() {
        return sell_status;
    }

    public void assignId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void selling() {
        this.sell_status = "SELLING";
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }
}
