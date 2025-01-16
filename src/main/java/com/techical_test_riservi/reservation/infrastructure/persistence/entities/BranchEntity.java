package com.techical_test_riservi.reservation.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "branch")
public class BranchEntity {

    @Id
    @Column(name = "id_branch", length = 36)
    private String id;

    @Column(name = "available_seats")
    private Integer availableSeats;

    private String address;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private RestaurantEntity restaurant;

    public BranchEntity() {
    }

    public BranchEntity(String id, Integer availableSeats, String address, RestaurantEntity restaurant) {
        this.id = id;
        this.availableSeats = availableSeats;
        this.address = address;
        this.restaurant = restaurant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }
}
