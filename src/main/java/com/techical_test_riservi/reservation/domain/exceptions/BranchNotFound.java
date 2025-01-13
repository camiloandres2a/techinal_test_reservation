package com.techical_test_riservi.reservation.domain.exceptions;

public class BranchNotFound extends RuntimeException{
    public BranchNotFound(String message){
        super(message);
    }
}
