package com.techical_test_riservi.reservation.infrastructure.persistence.adapters;

import com.techical_test_riservi.reservation.domain.Schedule;
import com.techical_test_riservi.reservation.domain.ports.RetrieveSchedules;
import com.techical_test_riservi.reservation.infrastructure.persistence.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Component
public class RetrieveScheduleMySql implements RetrieveSchedules {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public RetrieveScheduleMySql(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Boolean isADayAvailable(UUID idBranch, DayOfWeek date) {
        return scheduleRepository.existsByBranchEntityIdAndDate(idBranch.toString(), date);
    }

    @Override
    public Boolean isHourAvailable(UUID idRestaurant, UUID idBranch, LocalTime hour) {
        return null;
    }

    @Override
    public Schedule getRangeHour(UUID idBranch, DayOfWeek day) {
        var result = scheduleRepository
                .findByBranchEntityIdAndDate(idBranch.toString(), day);
        return result.get(0).toEntity();
    }

    @Override
    public LocalTime getEndHour(UUID idBranch, DayOfWeek day) {
        return null;
    }
}
