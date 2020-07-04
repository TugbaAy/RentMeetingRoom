package com.demo.MeetingRoom.repository;

import com.demo.MeetingRoom.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

    @Query("SELECT u FROM Reservation u WHERE u.dateReserved = ?1 and u.meetingHalls.id=?2 and u.isAvailable = false")
    public List<Reservation> findReservedDate(String reservedDate, String id);
}
