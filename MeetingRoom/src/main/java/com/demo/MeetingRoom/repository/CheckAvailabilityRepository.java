package com.demo.MeetingRoom.repository;

import com.demo.MeetingRoom.model.CheckAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckAvailabilityRepository extends JpaRepository<CheckAvailability, String> {

    @Query("SELECT u FROM CheckAvailability u WHERE u.dateReserved = ?1")
    public List<CheckAvailability> findByDateReserved(String reservedDate);

}
