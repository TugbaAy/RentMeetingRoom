package com.demo.MeetingRoom.controller;

import com.demo.MeetingRoom.exceptions.ValidationException;
import com.demo.MeetingRoom.operations.DateValidator;
import com.demo.MeetingRoom.operations.DateValidatorUsingDateFormat;
import com.demo.MeetingRoom.service.ReservationService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;


/**
 * CRUD operations of a reservation
 *
 * @author tugbaay
 * @version 1.0.0
 */
@RestController
@RequestMapping("api/reservation")
@Api(value = "Reservation Controller", description = "All CRUD operations of a reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @ApiOperation(value = "create a reservation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created reservation"),
            @ApiResponse(code = 400, message = "Parameter or parameters could be wrong format.Please check")})
    @PostMapping("/save")
    public ResponseEntity save(@ApiParam(value = "roomId", required = true)
                               @RequestParam("roomId") String roomId,
                               @ApiParam(value = "companyId", required = true)
                               @RequestParam("companyId") String companyId,
                               @ApiParam(value = "reservedDate should be dd/MM/yyyy pattern", required = true)
                               @RequestParam("reservedDate") String reservedDate,
                               @ApiParam(value = "reservation start time should be HH:mm pattern", required = true)
                               @RequestParam("reservationStartTime") String reservationStartTime,
                               @ApiParam(value = "reservation end time should be HH:mm pattern", required = true)
                               @RequestParam("reservationEndTime") String reservationEndTime) throws IOException, ParseException {
        {

            if (StringUtils.isBlank(roomId) ||
                    StringUtils.isBlank(companyId) ||
                    StringUtils.isBlank(reservationStartTime) ||
                    StringUtils.isBlank(reservationEndTime)) {
                throw new ValidationException("Parameter or parameters could be wrong format.Please check");
            }

            DateValidator validator = new DateValidatorUsingDateFormat("dd/MM/yyyy");
            boolean val = validator.isValid(reservedDate);
            if (val) {
                return new ResponseEntity<>(reservationService.save(
                        companyId,
                        roomId,
                        reservedDate,
                        reservationStartTime,
                        reservationEndTime), HttpStatus.OK);
            } else {
                throw new ValidationException("Date isn't correct format.");
            }
        }
    }

    @ApiOperation(value = "Get meeting rooms by date and capacity")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @PostMapping("/getRoomsByDate")
    public ResponseEntity getRoomsByDate(@ApiParam(value = "reservationDate", required = true)
                                         @RequestParam("reservationDate") String reservationDate,
                                         @ApiParam(value = "reservationDate", required = true)
                                         @RequestParam("capacity") int capacity) throws IOException, ParseException {
        {
            if (StringUtils.isBlank(reservationDate)) {

                throw new ValidationException("Parameter or parameters could be wrong format.Please check");

            }
            DateValidator validator = new DateValidatorUsingDateFormat("dd/MM/yyyy");
            boolean val = validator.isValid(reservationDate);
            if (val) {
                return new ResponseEntity<>(reservationService.getRoomsByDate(reservationDate, capacity), HttpStatus.OK);
            } else {
                throw new ValidationException("Date isn't correct format.");
            }
        }
    }
}
