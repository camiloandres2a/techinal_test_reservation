package com.techical_test_riservi.reservation.infrastructure.persistence.entities;

import com.techical_test_riservi.reservation.domain.Schedule;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {

    @Id
    @Column(name = "id_schedule", length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_branch", nullable = false)
    private BranchEntity branchEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "day", nullable = false)
    private DayOfWeek date;

    @Column(name = "start_hour")
    private LocalTime startHour;

    @Column(name = "end_hour")
    private LocalTime endHour;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BranchEntity getBranchEntity() {
        return branchEntity;
    }

    public void setBranchEntity(BranchEntity branchEntity) {
        this.branchEntity = branchEntity;
    }

    public DayOfWeek getDate() {
        return date;
    }

    public void setDate(DayOfWeek date) {
        this.date = date;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public Schedule toEntity(){
        return Schedule.builder()
                .id(UUID.fromString(this.id))
                .day(this.date)
                .startTime(this.startHour)
                .endTime(this.endHour)
                .build();
    }

    public static ScheduleEntity toRecord(UUID idBranch, DayOfWeek date) {
        ScheduleEntity entity = new ScheduleEntity();
        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setId(String.valueOf(idBranch));
        entity.setBranchEntity(branchEntity);
        entity.setDate(date);

        return entity;
    }
}
