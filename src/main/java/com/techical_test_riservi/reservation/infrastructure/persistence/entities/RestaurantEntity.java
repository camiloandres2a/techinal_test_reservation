package com.techical_test_riservi.reservation.infrastructure.persistence.entities;

import com.techical_test_riservi.reservation.domain.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    @Column(length = 36)
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant toEntity() {
        return Restaurant.builder()
                .id(UUID.fromString(this.id))
                .build();
    }

    public static RestaurantEntity from(UUID id) {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setId(String.valueOf(id));

        return entity;
    }

}
