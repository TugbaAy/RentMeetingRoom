package com.demo.MeetingRoom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * This entity about a company
 *
 * @author tugbaay
 * @version 1.0.0
 */
@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel(description = "This class contains about company table columns for database")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Company implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    @ApiModelProperty(notes = "The database generated company ID")
    private String id;

    @Column
    @NotNull
    @NotEmpty(message = "The company name cannot be empty")
    @Size(min = 15, max = 25, message
            = "About Me must be between 15 and 25 characters")
    @ApiModelProperty(notes = "The company name")
    private String companyName;

    @Column
    @NotNull
    @NotEmpty(message = "The password cannot be empty")
    @Size(min = 5, max = 25, message
            = "About Me must be between 5 and 25 characters")
    @ApiModelProperty(notes = "The password")
    private String password;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "Europe/Istanbul")
    @Temporal(TemporalType.DATE)
    private Date insertDate;

    @Column
    private String companyImagePath;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "Europe/Istanbul")
    private Date updateDate;

    @Column
    @Email(message = "Email should be valid")
    @NotNull
    @NotEmpty(message = "Company email information cannot be empty")
    @ApiModelProperty(notes = "The company email")
    private String companyEmail;

    @Column
    @NotNull
    @NotEmpty(message = "The city information of company cannot be empty")
    @ApiModelProperty(notes = "The company location")
    private String cityOfCompany;

    @Column
    @NotNull(message = "The number of employees information of company cannot be empty")
    @ApiModelProperty(notes = "The number of employees information of company")
    private int numberOfEmployees;

    @Column
    private boolean isActive;

    @Column
    @ApiModelProperty(notes = "This information about do you want to see all city rooms")
    private boolean doYouWantToSee;
}
