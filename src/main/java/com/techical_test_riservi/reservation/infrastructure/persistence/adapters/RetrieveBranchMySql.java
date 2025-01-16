package com.techical_test_riservi.reservation.infrastructure.persistence.adapters;

import com.techical_test_riservi.reservation.domain.ports.RetrieveBranches;
import com.techical_test_riservi.reservation.infrastructure.persistence.entities.BranchEntity;
import com.techical_test_riservi.reservation.infrastructure.persistence.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RetrieveBranchMySql implements RetrieveBranches {

    private BranchRepository branchRepository;

    @Autowired
    public RetrieveBranchMySql(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Integer getAvailableSeatsInTheBranch(UUID branchId) {
        return branchRepository
                .findById(branchId.toString())
                .map(BranchEntity::getAvailableSeats)
                .get();
    }

    @Override
    public Boolean getBranch(UUID branchId) {
        return branchRepository.existsById(branchId.toString());
    }
}
