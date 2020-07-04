package com.demo.MeetingRoom.service;

import com.demo.MeetingRoom.exceptions.EmptyFieldException;
import com.demo.MeetingRoom.exceptions.RecordNotFoundException;
import com.demo.MeetingRoom.model.MeetingHall;
import com.demo.MeetingRoom.model.MeetingHallWorkingTime;
import com.demo.MeetingRoom.operations.FindHour;
import com.demo.MeetingRoom.repository.MeetingHallRepository;
import com.demo.MeetingRoom.repository.MeetingHallWorkingTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 * This service control the CRUD operations of the Meeting Halls
 *
 * @author tugbaay
 * @version 1.0.0
 */

@Service
public class MeetingHallService {

    @Autowired
    private MeetingHallRepository meetingHallRepository;

    @Autowired
    private MeetingHallWorkingTimeRepository meetingHallWorkingTimeRepository;

    /**
     * This method save MeetingHall.
     *
     * @param hall MeetingHall
     * @return MeetingHall
     */
    //TO-DO change exception
    public MeetingHall save(MeetingHall hall) {
        if (hall.getCapacity() == 0) {
            throw new EmptyFieldException("Capacity should be bigger than 0");
        }
        hall.setInsertDate(new Date());
        hall.setActive(true);

        MeetingHall meetingHall = meetingHallRepository.save(hall);

        int startTime = Integer.valueOf(meetingHall.getTimeReservedStart().split(":")[0]);
        int endTime = Integer.valueOf(meetingHall.getTimeReservedEnd().split(":")[0]);

        for (int i = startTime; i < endTime; i++) {
            MeetingHallWorkingTime meetingHallWorkingTime = new MeetingHallWorkingTime();
            meetingHallWorkingTime.setMeetingHalls(meetingHall);
            FindHour.setTimes(meetingHallWorkingTime, i);
            meetingHallWorkingTimeRepository.save(meetingHallWorkingTime);
        }
        return meetingHall;
    }

    /**
     * This method returns MeetingHall by id.
     *
     * @param id MeetingHall id
     * @return MeetingHall
     */
    public MeetingHall findById(String id) {
        return meetingHallRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Meeting Hall not found for this id : " + id));
    }

    /**
     * This method returns meetingHall by id which is company status active.
     *
     * @param id MeetingHall id
     * @return MeetingHall
     */
    public MeetingHall findByIdAndActive(String id) {
        return meetingHallRepository.findByIdAndActive(id);
    }

    /**
     * This method returns MeetingHall by meetingHallName but it should be active.
     *
     * @param meetingHallName
     * @return MeetingHall
     */
    //TO-DO Edit exception
    public MeetingHall findByMeetingHallNameifActive(String meetingHallName) {
        if (meetingHallRepository.findByMeetingHallNameifActive(meetingHallName) == null) {
            throw new RecordNotFoundException("This Meeting Hall not found");
        } else {
            return meetingHallRepository.findByMeetingHallNameifActive(meetingHallName);
        }
    }

    /**
     * This method returns MeetingHall by meetingHallName but it should be passive.
     *
     * @param meetingHallName
     * @return MeetingHall
     */
    //TO-DO Edit exception
    public MeetingHall findByMeetingHallNameifPassive(String meetingHallName) {
        if (meetingHallRepository.findByMeetingHallNameifPassive(meetingHallName) == null) {
            throw new RecordNotFoundException("This Meeting Hall is not found");
        } else {
            return meetingHallRepository.findByMeetingHallNameifActive(meetingHallName);
        }
    }

    /**
     * This method returns MeetingHall by capacity but it should be active.
     *
     * @param capacity
     * @return List<MeetingHall> - list of MeetingRoom's
     */
    //TO-DO Edit exception
    public List<MeetingHall> findByCapacityAndActive(int capacity) {
        if (meetingHallRepository.findByCapacityAndActive(capacity) == null) {
            throw new RecordNotFoundException("About this capacity information cannot be found any records");
        } else {
            return meetingHallRepository.findByCapacityAndActive(capacity);
        }
    }

    /**
     * This method returns MeetingHall by time but it should be active.
     *
     * @param timeReservedStart
     * @param timeReservedEnd
     * @return List<MeetingHall> - list of MeetingRoom's
     */
    //TO-DO Edit exception
    public List<MeetingHall> findByTimeReservedStartAndTimeReservedEnd(String timeReservedStart, String timeReservedEnd) {
        if (meetingHallRepository.findByTimeReservedStartAndTimeReservedEnd(timeReservedStart, timeReservedEnd) == null) {
            throw new RecordNotFoundException("About this parameters cannot be found any records");
        } else {
            return meetingHallRepository.findByTimeReservedStartAndTimeReservedEnd(timeReservedStart, timeReservedEnd);
        }
    }

    /**
     * This method returns all MeetingHall list.
     *
     * @return List<MeetingHall>
     */
    public List<MeetingHall> findAll() {
        return meetingHallRepository.findAll();
    }

    /**
     * This method returns active MeetingHall list.
     *
     * @return List<MeetingHall>
     */
    public List<MeetingHall> findAllByActive() {
        return meetingHallRepository.findAllByActive();
    }

    /**
     * This method returns passive MeetingHall list.
     *
     * @return List<MeetingHall>
     */
    public List<MeetingHall> findAllByPassive() {
        return meetingHallRepository.findAllByPassive();
    }

    /**
     * This method returns active MeetingHall list by cityName.
     *
     * @return List<MeetingHall>
     */
    public List<MeetingHall> findByCity(String cityName) {
        return meetingHallRepository.findByCity(cityName);
    }


}
