package com.demo.MeetingRoom.service;

import com.demo.MeetingRoom.model.CheckAvailability;
import com.demo.MeetingRoom.model.Reservation;
import com.demo.MeetingRoom.repository.CheckAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * This service control the reservation request is available
 *
 * @author tugbaay
 * @version 1.0.0
 */
@Service
public class CheckAvailabilityService {

    @Autowired
    private CheckAvailabilityRepository checkAvailabilityRepository;

    /**
     * This method save a checkAvailability object.
     *
     * @param reservedDate reservation date
     * @param time         reservation time
     * @param reservation  reservation objec
     * @param state        reservation state
     * @return CheckAvailability - object
     */
    public CheckAvailability save(String reservedDate, String time, Reservation reservation, boolean state) throws IOException {
        CheckAvailability checkAvailability = new CheckAvailability();
        checkAvailability = new CheckAvailability();
        checkAvailability.setDateReserved(reservedDate);
        checkAvailability.setTime(time);
        checkAvailability.setAvailable(state);
        checkAvailability.setReservation(reservation);

        return checkAvailabilityRepository.save(checkAvailability);
    }

    /**
     * This method lists checkAvailability by the date
     *
     * @param date reservation date
     * @return List<CheckAvailability> - object
     */
    List<CheckAvailability> findByDate(String date) {
        return checkAvailabilityRepository.findByDateReserved(date);
    }

}
