package com.techical_test_riservi.reservation.domain.exceptions;

public class NumberPeopleNotAvailable extends RuntimeException{
    public NumberPeopleNotAvailable(String message) {
        super(message);
    }
}
