package com.techical_test_riservi.reservation.domain.exceptions;

public class DateNotAvailable extends RuntimeException{
    public DateNotAvailable(String message) {
        super(message);
    }
}
