package com.demo.MeetingRoom.service;

import com.demo.MeetingRoom.exceptions.NoMeetingRoom;
import com.demo.MeetingRoom.exceptions.RecordNotFoundException;
import com.demo.MeetingRoom.exceptions.TimeIsNotValidException;
import com.demo.MeetingRoom.model.*;
import com.demo.MeetingRoom.operations.FindHour;
import com.demo.MeetingRoom.repository.MeetingHallWorkingTimeRepository;
import com.demo.MeetingRoom.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 *
 * This service control the CRUD operations of the Reservations
 *
 * @author tugbaay
 * @version 1.0.0
 */

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CheckAvailabilityService checkAvailabilityService;

    @Autowired
    private MeetingHallWorkingTimeRepository meetingHallWorkingTimeRepository;

    @Autowired
    private MeetingHallService meetingHallService;

    @Autowired
    private CompanyService companyService;

    public Reservation create(String reservedDate, String startTime, String endTime, Company company, MeetingHall meetingHall, boolean isAvailable) {
        Reservation reservation = new Reservation();
        reservation = new Reservation();
        reservation.setTimeReservedStart(startTime);
        reservation.setTimeReservedEnd(endTime);
        reservation.setDateReserved(reservedDate);
        reservation.setMeetingHalls(meetingHall);
        reservation.setCompany(company);
        reservation.setAvailable(false);
        return reservation;
    }

    /**
     * This method save the reservation.
     *
     * @param companyId Company id
     * @param roomId Meeting Hall id
     * @param reservedDate
     * @param reservationStartTime
     * @param reservationEndTime
     * @return Reservation
     */

    public Reservation save(String companyId,
                            String roomId,
                            String reservedDate,
                            String reservationStartTime,
                            String reservationEndTime) throws IOException {

        Company company = companyService.findByIdIfActive(companyId);
        MeetingHall meetingHall = meetingHallService.findByIdAndActive(roomId);

        // startTime<endTime
        checkIsNegative(reservationStartTime, reservationEndTime);

        // check working hours of meeting hall
        checkTime(reservationStartTime, reservationEndTime, meetingHall);

        // CheckAvailability and save reservation
        return checkAvailability(company, meetingHall, reservedDate, reservationStartTime, reservationEndTime);
    }

    //TO-DO control and change exception
    public List<Rooms> getRoomsByDate(String reservationDate, int capacity) {
        List<Rooms> roomsList = new ArrayList<>();
        List<MeetingHall> meetingRooms = meetingHallService.findByCapacityAndActive(capacity);
        for (int i = 0; i < meetingRooms.size(); i++) {
            Rooms rooms = new Rooms();
            List<Availability> availabilityList = new ArrayList<>();
            rooms.setRoomId(meetingRooms.get(i).getId());
            rooms.setRoomName(meetingRooms.get(i).getMeetingRoomName());
            rooms.setCapacity(meetingRooms.get(i).getCapacity());
            rooms.setWorkingHours(meetingRooms.get(i).getTimeReservedStart() + " - " + meetingRooms.get(i).getTimeReservedEnd());
            Availability availability = null;
            List<Reservation> reservedDateAvailability = reservationRepository.findReservedDate(reservationDate, meetingRooms.get(i).getId());
            if (reservedDateAvailability.isEmpty()) {
                availability = new Availability();
                availability.setMeetingTime("Cannot find any data about this date " + reservationDate);
                availability.setState("Empty");
                availabilityList.add(availability);
                rooms.setAvailability(availabilityList);
            } else {
                System.out.println(reservedDateAvailability);
                Collections.sort(reservedDateAvailability, Reservation.sortTime);
                for (Reservation reservation : reservedDateAvailability) {
                    if (!reservation.isAvailable()) {
                        availability = new Availability();
                        availability.setMeetingTime(reservation.getTimeReservedStart() + " - " + reservation.getTimeReservedEnd());
                        availability.setState("Reserved");
                        availabilityList.add(availability);
                        rooms.setAvailability(availabilityList);
                    }
                }
            }
            roomsList.add(rooms);
        }

        if (roomsList.isEmpty()) {
            throw new RecordNotFoundException("This meeting room’s capacity restricted " +capacity +" people");
        }

        return roomsList;
    }

    public Reservation checkAvailability(Company company,
                                         MeetingHall meetingHall,
                                         String reservedDate,
                                         String reservationStartTime,
                                         String reservationEndTime) throws IOException {
        Reservation reservation = null;
        List<CheckAvailability> checkAvailabilities = checkAvailabilityService.findByDate(reservedDate);
        if (checkAvailabilities.isEmpty()) {
            reservation = create(reservedDate, reservationStartTime, reservationEndTime, company, meetingHall, false);
            for (int i = Integer.valueOf(reservationStartTime.split(":")[0]); i < Integer.valueOf(reservationEndTime.split(":")[0]); i++) {
                checkAvailabilityService.save(reservedDate, String.valueOf(i), reservation, false);
            }
            return reservation;
        } else {
            boolean flag = false;
            for (CheckAvailability checkAvailability_ : checkAvailabilities) {
                for (int i = Integer.valueOf(reservationStartTime.split(":")[0]); i < Integer.valueOf(reservationEndTime.split(":")[0]); i++) {
                    if (checkAvailability_.getTime().equals(String.valueOf(i))) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            if (!flag) {
                reservation = create(reservedDate, reservationStartTime, reservationEndTime, company, meetingHall, false);
                for (int i = Integer.valueOf(reservationStartTime.split(":")[0]); i < Integer.valueOf(reservationEndTime.split(":")[0]); i++) {
                    checkAvailabilityService.save(reservedDate, String.valueOf(i), reservation, false);
                }
                return reservation;
            } else {
                throw new RecordNotFoundException("Meeting room has been reserved. Please try for other times”");
            }
        }


    }

    public void checkTime(String reservationStartTime, String reservationEndTime, MeetingHall meetingHall) {
        List<MeetingHallWorkingTime> meetingHallWorkingTimeList = meetingHallWorkingTimeRepository.findByMeetingHalls(meetingHall.getId());

        int total = Integer.valueOf(reservationEndTime.split(":")[0]) - Integer.valueOf(reservationStartTime.split(":")[0]);
        int count = 0;
        for (int i = Integer.valueOf(reservationStartTime.split(":")[0]); i < Integer.valueOf(reservationEndTime.split(":")[0]); i++) {
            for (MeetingHallWorkingTime meetingHallWorkingTime : meetingHallWorkingTimeList) {
                boolean a = new FindHour().getTimes(meetingHallWorkingTime, i);
                if (a) {
                    count++;
                    break;

                }
            }
        }
        if (!(count == total)) {
            throw new NoMeetingRoom("Meeting room has been reserved. Please try for other times”");
        }
    }

    public void checkIsNegative(String reservationStartTime, String reservationEndTime) {
        if ((Integer.valueOf(reservationStartTime.split(":")[0].trim()) - Integer.valueOf(reservationEndTime.split(":")[0].trim())) > 0 ||
                (Integer.valueOf(reservationStartTime.split(":")[0].trim()) - Integer.valueOf(reservationEndTime.split(":")[0].trim())) == 0) {
            throw new TimeIsNotValidException("StartTime cannot be bigger than endTime");
        }
    }

}


