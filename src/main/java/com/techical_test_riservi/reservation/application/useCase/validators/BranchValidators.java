package com.techical_test_riservi.reservation.application.useCase.validators;

import com.techical_test_riservi.reservation.domain.exceptions.BranchNotFound;
import com.techical_test_riservi.reservation.domain.ports.RetrieveBranches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BranchValidators {

    private RetrieveBranches retrieveBranches;

    @Autowired
    public BranchValidators(RetrieveBranches retrieveBranches) {
        this.retrieveBranches = retrieveBranches;
    }

    public void execute(UUID branchId){
        if (!retrieveBranches.getBranch(branchId)) {
            throw new BranchNotFound("Branch not found");
        }
    }
}
