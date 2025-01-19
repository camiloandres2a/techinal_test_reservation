package com.techical_test_riservi.reservation.infrastructure.persistence.entities;

import com.techical_test_riservi.reservation.domain.Branch;
import com.techical_test_riservi.reservation.domain.Client;
import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collections;
import java.util.UUID;

@Entity
@Table(name = "reservation")
@Builder
@AllArgsConstructor
public class ReservationEntity {

    @Id
    @Column(name = "id_reservation", length = 36)
    private String id;

    @Column(name = "id_client")
    private String clientId;

    @Transient
    private String clientName;

    @Transient
    private String clientCellPhoneNumber;

    @Transient
    private String restaurantId;

    @Column(name = "id_branch")
    private String branchId;

    @Column(name = "day_taken")
    private String dateTaken;
    @Column(name = "hour_taken")
    private LocalTime hourTaken;
    @Column(name = "number_people")
    private Integer numberPeople;

    public ReservationEntity() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public LocalTime getHourTaken() {
        return hourTaken;
    }

    public void setHourTaken(LocalTime hourTaken) {
        this.hourTaken = hourTaken;
    }

    public Integer getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(Integer numberPeople) {
        this.numberPeople = numberPeople;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientCellPhoneNumber() {
        return clientCellPhoneNumber;
    }

    public void setClientCellPhoneNumber(String clientCellPhoneNumber) {
        this.clientCellPhoneNumber = clientCellPhoneNumber;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public static ReservationEntity from(Reservation reservation) {
        ReservationEntity entity = new ReservationEntity();
        entity.setId(String.valueOf(reservation.getId()));
        entity.setDateTaken(String.valueOf(reservation.getDate()));

        entity.setClientId(String.valueOf(reservation.getClient().getId()));
        entity.setClientName(reservation.getClient().getName());
        entity.setClientCellPhoneNumber(reservation.getClient().getCellPhoneNumber());

        entity.setBranchId(String.valueOf(reservation.getRestaurant().getBranches().get(0).getId()));

        entity.setRestaurantId(String.valueOf(reservation.getRestaurant().getId()));

        entity.setDateTaken(reservation.getDate().toString());
        entity.setHourTaken(reservation.getTime());
        entity.setNumberPeople(reservation.getNumberPeople());

        return entity;
    }

    public Reservation toEntity(){
        return Reservation.builder()
                .id(UUID.fromString(this.id))
                .date(DayOfWeek.valueOf(this.dateTaken))
                .client(Client.builder()
                        .id(UUID.fromString(this.clientId))
                        .build()
                )
                .restaurant(Restaurant.builder()
                        .branches(Collections.singletonList(
                                Branch.builder()
                                        .id(UUID.fromString(this.branchId))
                                        .build()
                        ))
                        .build())
                .time(this.hourTaken)
                .numberPeople(this.numberPeople)
                .build();
    }

    public Reservation toEntityUpdate(){
        return Reservation.builder()
                .id(UUID.fromString(this.id))
                .date(DayOfWeek.valueOf(this.dateTaken))
                .client(Client.builder()
                        .id(UUID.fromString(this.clientId))
                        .build())
                .restaurant(Restaurant.builder()
                        .branches(Collections.singletonList(
                                Branch.builder()
                                        .id(UUID.fromString(this.branchId))
                                        .build()
                        ))
                        .build())
                .time(this.hourTaken)
                .numberPeople(this.numberPeople)
                .build();
    }

}
