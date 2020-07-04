package com.demo.MeetingRoom.repository;

import com.demo.MeetingRoom.model.MeetingHallWorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingHallWorkingTimeRepository extends JpaRepository<MeetingHallWorkingTime, String> {

    @Query("SELECT u FROM MeetingHallWorkingTime u WHERE u.meetingHalls.id=?1")
    public List<MeetingHallWorkingTime> findByMeetingHalls(String id);


}
