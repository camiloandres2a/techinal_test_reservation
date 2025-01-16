package com.techical_test_riservi.reservation.infrastructure.persistence.repository;

import com.techical_test_riservi.reservation.infrastructure.persistence.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, String> {
    List<ReservationEntity> findByBranchIdAndDateTakenAndHourTaken(String branchId, String data, LocalTime time);
    List<ReservationEntity> findByDateTakenAndBranchId(String date, String branchId);
}
