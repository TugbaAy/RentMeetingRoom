package com.demo.MeetingRoom.controller;

import com.demo.MeetingRoom.model.MeetingHall;
import com.demo.MeetingRoom.service.MeetingHallService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;


/**
 * CRUD Operations of a Meeting Room
 *
 * @author tugbaay
 * @version 1.0.0
 */
@RestController
@RequestMapping("api/meetingRoom")
@Api(value = "MeetingHall Controller", description = "All CRUD operations of a meeting room")
public class MeetingHallController {

    @Autowired
    private MeetingHallService meetingHallService;

    @ApiOperation(value = "save a new meeting room")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created meetingRoom"),
            @ApiResponse(code = 400, message = "Parameter or parameters could be wrong format.Please check")})
    @PostMapping("/save")
    public ResponseEntity save(
            @ApiParam(value = "MeetingHall Object", required = true)
            @Valid @RequestBody MeetingHall meetingHall) throws IOException {
        {
            return new ResponseEntity<>(meetingHallService.save(meetingHall), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "list of all meeting rooms ( active and passive )")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/findAll")
    public ResponseEntity findAll() {
        return new ResponseEntity<>(meetingHallService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "To find meeting room with id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/find/{id}")
    public ResponseEntity findById(
            @ApiParam(value = "meetingRoom id", required = true)
            @PathVariable("id") @Valid String id) {
        return new ResponseEntity(meetingHallService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "To find meetingRoom with room name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/findByNameifActive")
    public ResponseEntity findByCompanyNameifActive(
            @ApiParam(value = "meetingHallName", required = true)
            @RequestParam("meetingHallName") String meetingHallName) {
        return new ResponseEntity(meetingHallService.findByMeetingHallNameifActive(meetingHallName), HttpStatus.OK);
    }


    @ApiOperation(value = "To find meetingRoom with room capacity")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/findByCapacity")
    public ResponseEntity findByCapacity(
            @ApiParam(value = "capacity", required = true)
            @RequestParam("capacity") int capacity) {
        return new ResponseEntity(meetingHallService.findByCapacityAndActive(capacity), HttpStatus.OK);
    }

    @ApiOperation(value = "To find meetingRoom with working hours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/findByWorkingHours")
    public ResponseEntity findByWorkingHours(
            @ApiParam(value = "timeReservedStart", required = true)
            @RequestParam("timeReservedStart") String timeReservedStart,
            @ApiParam(value = "timeReservedEnd", required = true)
            @RequestParam("timeReservedEnd") String timeReservedEnd) {
        return new ResponseEntity(meetingHallService.findByTimeReservedStartAndTimeReservedEnd(timeReservedStart, timeReservedEnd), HttpStatus.OK);
    }


    @ApiOperation(value = "To find meeting room if availability is passive")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/findByNameifPassive")
    public ResponseEntity findByNameifPassive(
            @ApiParam(value = "meetingHallName", required = true)
            @Valid @RequestParam("meetingHallName") String meetingHallName) {
        return new ResponseEntity(meetingHallService.findByMeetingHallNameifPassive(meetingHallName), HttpStatus.OK);
    }

    @ApiOperation(value = "Lists active meetingRooms")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/findActives")
    public ResponseEntity findActives() {
        return new ResponseEntity<>(meetingHallService.findAllByActive(), HttpStatus.OK);
    }

    @ApiOperation(value = "Lists passive meetingRooms")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/findPassives")
    public ResponseEntity findPassives() {
        return new ResponseEntity<>(meetingHallService.findAllByPassive(), HttpStatus.OK);
    }


}
