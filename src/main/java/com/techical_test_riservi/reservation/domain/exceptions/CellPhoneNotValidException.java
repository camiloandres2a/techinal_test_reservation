package com.techical_test_riservi.reservation.domain.exceptions;

public class CellPhoneNotValidException extends RuntimeException{
    public CellPhoneNotValidException(String message){
       super(message);
    }
}
