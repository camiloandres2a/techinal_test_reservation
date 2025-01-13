package com.techical_test_riservi.reservation.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@Builder
@Getter
public class Client {
    private UUID id;
    private String cellPhoneNumber;
    private String name;

    public static Boolean isValidCellPhoneNumber(String cellPhoneNumber){
        return cellPhoneNumber.length() == 10;
    }
}
