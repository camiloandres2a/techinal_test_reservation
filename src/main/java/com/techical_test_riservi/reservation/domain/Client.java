package com.techical_test_riservi.reservation.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Builder
@Getter
@Setter
public class Client {
    private UUID id;
    private String cellPhoneNumber;
    private String name;

    public static Boolean isValidCellPhoneNumber(String cellPhoneNumber){
        return cellPhoneNumber.length() == 10;
    }

    public static Client instantiate(String cellPhoneNumber, String name, Reservation reservation, UUID uuid){
        reservation.getClient().setId(uuid);
        return Client.builder()
                .id(uuid)
                .name(name)
                .cellPhoneNumber(cellPhoneNumber)
                .build();
    }
}
