package com.techical_test_riservi.reservation.infrastructure.persistence.adapters;

import com.techical_test_riservi.reservation.infrastructure.persistence.entities.BranchEntity;
import com.techical_test_riservi.reservation.infrastructure.persistence.repository.BranchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RetrieveBranchMySqlTest {

    @InjectMocks
    private RetrieveBranchMySql retrieveBranchMySql;

    @Mock
    private BranchRepository branchRepository;

    @Test
    void getAvailableSeatsInTheBranch() {
        String branchId = "f18b1cc5-d076-4421-a3d0-84f824fe5a2f";

        BranchEntity branchEntity = BranchEntity.builder()
                .availableSeats(10)
                .build();

        Optional<BranchEntity> mockBranchEntity = Optional.of(branchEntity);

        Mockito.when(branchRepository.findById(branchId))
                .thenReturn(mockBranchEntity);

        //when
        var result = retrieveBranchMySql.getAvailableSeatsInTheBranch(UUID.fromString(branchId));

        //then
        assertEquals(branchEntity.getAvailableSeats(), result);
        Mockito.verify(branchRepository).findById(branchId);

    }

    @Test
    void getBranch() {
        String branchId = "f18b1cc5-d076-4421-a3d0-84f824fe5a2f";

        Mockito.when(branchRepository.existsById(branchId))
                .thenReturn(true);

        //when
        var result = retrieveBranchMySql.getBranch(UUID.fromString(branchId));

        //then
        assertEquals(true, result);
        Mockito.verify(branchRepository).existsById(branchId);
    }
}