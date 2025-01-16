package com.techical_test_riservi.reservation.infrastructure.persistence.repository;

import com.techical_test_riservi.reservation.infrastructure.persistence.entities.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, String> {
}
