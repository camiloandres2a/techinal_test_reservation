package com.techical_test_riservi.reservation.domain.ports;

import java.util.UUID;

public interface RetrieveBranches {
    Integer getAvailableSeatsInTheBranch(UUID branchId);
    Boolean getBranch(UUID branchId);
}
