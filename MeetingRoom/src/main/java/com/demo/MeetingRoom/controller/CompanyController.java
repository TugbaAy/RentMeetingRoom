package com.demo.MeetingRoom.controller;

import com.demo.MeetingRoom.exceptions.RecordNotFoundException;
import com.demo.MeetingRoom.exceptions.ValidationException;
import com.demo.MeetingRoom.model.Company;
import com.demo.MeetingRoom.service.CompanyService;
import com.demo.MeetingRoom.service.MeetingHallService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;


/**
 * CRUD Operations on Company
 *
 * @author tugbaay
 * @version 1.0.0
 */
@RestController
@RequestMapping("api/company")
@Api(value = "Company Controller", description = "All CRUD operations of a company")
public class CompanyController {


    @Autowired
    private CompanyService companyService;

    @Autowired
    private MeetingHallService meetingHallService;

    @ApiOperation(value = "save a company")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created company"),
            @ApiResponse(code = 400, message = "Parameter or parameters could be wrong format.Please check")})
    @PostMapping("/save")
    public ResponseEntity save(
            @ApiParam(value = "Company object should be this format : " +
                    "{\n" +
                    "    \"companyName\":\"xxxx\",\n" +
                    "    \"companyEmail\":\"xxx\",\n" +
                    "    \"pasword\":\"xxx\",\n" +
                    "    \"cityOfCompany\":\"xxx\",\n" +
                    "    \"numberOfEmployees\":xxx\n" +
                    "}", required = true)
            @RequestParam("companyObject") String companyObject,
            @ApiParam(value = "Company Image", required = true)
            @RequestPart("file") MultipartFile file) throws IOException {
        {
            if (StringUtils.isBlank(companyObject)) {
                throw new ValidationException("Parameter or parameters could be wrong format.Please check");
            }
            Company company = new ObjectMapper().readValue(companyObject, Company.class);
            return new ResponseEntity<>(companyService.save(company, file), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "list of all companies ( active and passive )")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/findAll")
    public ResponseEntity findAll() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "To find company with id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/find/{id}")
    public ResponseEntity findById(
            @ApiParam(value = "company id", required = true)
            @PathVariable("id") @Valid String id) {
        return new ResponseEntity(companyService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "If the company is active, the object returns")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/findByNameifActive")
    public ResponseEntity findByCompanyNameifActive(
            @ApiParam(value = "company name to find company object", required = true)
            @RequestParam("companyName") String companyName) {
        return new ResponseEntity(companyService.findByCompanyNameifActive(companyName), HttpStatus.OK);
    }

    @ApiOperation(value = "If the company is passive, the object returns")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/findByNameifPassive")
    public ResponseEntity findByNameifPassive(
            @ApiParam(value = "companyName", required = true)
            @RequestParam("companyName") String companyName) {
        return new ResponseEntity(companyService.findByCompanyNameifPassive(companyName), HttpStatus.OK);
    }

    @ApiOperation(value = "Lists active companies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/findActives")
    public ResponseEntity findActives() {
        return new ResponseEntity<>(companyService.findAllByActive(), HttpStatus.OK);
    }

    @ApiOperation(value = "Lists passive companies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/findPassives")
    public ResponseEntity findPassives() {
        return new ResponseEntity<>(companyService.findAllByPassive(), HttpStatus.OK);
    }

    @ApiOperation(value = "To find meetingRoom with room name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content available")})
    @GetMapping(value = "/findByCity")
    public ResponseEntity findMeetingRoomByCompanyCity(
            @ApiParam(value = "company id", required = true)
            @RequestParam("id") String id) {

        Company company = companyService.findById(id);
        if (company.isActive()) {
            if (company.isDoYouWantToSee()) {
                return new ResponseEntity(meetingHallService.findAllByActive(), HttpStatus.OK);
            } else {
                return new ResponseEntity(meetingHallService.findByCity(company.getCityOfCompany()), HttpStatus.OK);
            }
        } else {
            throw new RecordNotFoundException("This company not found");
        }
    }
}
