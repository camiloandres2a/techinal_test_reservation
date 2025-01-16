package com.techical_test_riservi.reservation.infrastructure.persistence.repository;

import com.techical_test_riservi.reservation.infrastructure.persistence.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, String> {

    Boolean existsByBranchEntityIdAndDate(String branchId, DayOfWeek date);
    List<ScheduleEntity> findByBranchEntityIdAndDate(String branchId, DayOfWeek date);
}
