package com.demo.MeetingRoom.service;

import com.demo.MeetingRoom.controller.FileController;
import com.demo.MeetingRoom.exceptions.EmptyFieldException;
import com.demo.MeetingRoom.exceptions.RecordNotFoundException;
import com.demo.MeetingRoom.model.Company;
import com.demo.MeetingRoom.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 *
 * This service control the CRUD operations of the companies
 *
 * @author tugbaay
 * @version 1.0.0
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    /**
     * This method save the company.
     *
     * @param company Company
     * @param photo Photo of the company
     * @return Company
     */
    //TO-DO Change the exception
    public Company save(Company company, MultipartFile photo) throws IOException {
        if (company.getNumberOfEmployees() == 0) {
            throw new EmptyFieldException("Employee number could be bigger than 0");
        }
        company.setInsertDate(new Date());
        company.setActive(true);
        String lastPath = "http://localhost:8080/file/resources?fileName=" + FileController.saveFile(photo);
        company.setCompanyImagePath(lastPath);
        company.setDoYouWantToSee(true);
        return companyRepository.save(company);
    }

    /**
     * This method returns company by id.
     *
     * @param id Company id
     * @return Company
     */
    public Company findById(String id) {
        return companyRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Company not found for this id : " + id));
    }

    /**
     * This method returns company by companyName but it should be active.
     *
     * @param companyName
     * @return Company
     */
    public Company findByCompanyNameifActive(String companyName) {
        if (companyRepository.findByCompanyNameifActive(companyName) == null) {
            throw new RecordNotFoundException("This company is not found");
        } else {
            return companyRepository.findByCompanyNameifActive(companyName);
        }
    }

    /**
     * This method returns company by id but it should be active.
     *
     * @param id - Company id
     * @return Company
     */
    public Company findByIdIfActive(String id) {
        if (companyRepository.findByIdAndActive(id) == null) {
            throw new RecordNotFoundException("This company is not found");
        } else {
            return companyRepository.findByIdAndActive(id);
        }
    }

    /**
     * This method returns company by companyName but it should be passive.
     *
     * @param companyName
     * @return Company
     */
    public Company findByCompanyNameifPassive(String companyName) {
        if (companyRepository.findByCompanyNameifPassive(companyName) == null) {
            throw new RecordNotFoundException("This company is not found");
        } else {
            return companyRepository.findByCompanyNameifActive(companyName);
        }
    }

    /**
     * This method returns all company list.
     *
     * @return List<Company>
     */
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    /**
     * This method returns all active company list.
     *
     * @return List<Company>
     */
    public List<Company> findAllByActive() {
        return companyRepository.findAllByActive();
    }

    /**
     * This method returns all passive company list.
     *
     * @return List<Company>
     */
    public List<Company> findAllByPassive() {
        return companyRepository.findAllByPassive();
    }

    //TO-DO
    // update
    // delete

}
