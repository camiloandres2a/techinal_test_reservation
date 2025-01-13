package com.techical_test_riservi.reservation.domain.ports;

import com.techical_test_riservi.reservation.domain.Branch;

import java.util.Optional;
import java.util.UUID;

public interface RetrieveBranches {
    Integer getAvailableSeatsInTheBranch(UUID branchId);
    Optional<Branch> getBranch(UUID branchId);
}
