package com.demo.MeetingRoom.repository;

import com.demo.MeetingRoom.model.MeetingHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingHallRepository extends JpaRepository<MeetingHall, String> {

    @Query("SELECT u FROM MeetingHall u WHERE u.meetingRoomName = ?1 and u.isActive=true")
    public MeetingHall findByMeetingHallNameifActive(String category_name);

    @Query("SELECT u FROM MeetingHall u WHERE u.id = ?1 and u.isActive=true")
    public MeetingHall findByIdAndActive(String id);

    @Query("SELECT u FROM MeetingHall u WHERE u.city = ?1 and u.isActive=true")
    public List<MeetingHall> findByCity(String cityName);

    @Query("SELECT u FROM MeetingHall u WHERE u.capacity >= ?1 and u.isActive=true order by u.capacity ASC")
    public List<MeetingHall> findByCapacityAndActive(int capacity);

    @Query("SELECT u FROM MeetingHall u WHERE u.timeReservedStart = ?1 and u.timeReservedEnd = ?2  and u.isActive=true and u.theLastState = true")
    public List<MeetingHall> findByTimeReservedStartAndTimeReservedEnd(String timeReservedStart, String timeReservedEnd);

    @Query("SELECT u FROM MeetingHall u WHERE u.meetingRoomName = ?1 and u.isActive=true")
    public MeetingHall findByMeetingHallNameifPassive(String category_name);

    @Query("select m from MeetingHall m where m.isActive = true")
    public List<MeetingHall> findAllByActive();

    @Query("select m from MeetingHall m where m.isActive = false")
    public List<MeetingHall> findAllByPassive();


}
