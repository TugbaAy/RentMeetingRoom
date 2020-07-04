package com.demo.MeetingRoom.repository;

import com.demo.MeetingRoom.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    @Query("SELECT u FROM Company u WHERE u.companyName = ?1 and u.isActive=true")
    public Company findByCompanyNameifActive(String category_name);

    @Query("SELECT u FROM Company u WHERE u.companyName = ?1 and u.isActive=false")
    public Company findByCompanyNameifPassive(String category_name);

    @Query("SELECT u FROM Company u WHERE u.id = ?1 and u.isActive=true")
    public Company findByIdAndActive(String id);

    @Query("select m from Company m where m.isActive = true")
    public List<Company> findAllByActive();

    @Query("select m from Company m where m.isActive = false")
    public List<Company> findAllByPassive();

}
