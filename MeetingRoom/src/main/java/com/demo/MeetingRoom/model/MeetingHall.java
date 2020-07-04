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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * MeetingRoom model
 *
 * @author tugbaay
 * @version 1.0.0
 */
@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel(description = "This class contains about meetingRoom table columns for database")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MeetingHall implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    @ApiModelProperty(notes = "The database generated meetingRoom ID")
    private String id;

    @Column
    @NotNull
    @NotEmpty(message = "The meeting room name cannot be empty")
    @Size(min = 5, max = 25, message = "About Me must be between 5 and 25 characters")
    @ApiModelProperty(notes = "The meeting room name")
    private String meetingRoomName;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "Europe/Istanbul")
    private Date insertDate;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "Europe/Istanbul")
    private Date updateDate;

    @Column
    @NotNull
    @NotEmpty(message = "The city information of meeting room cannot be empty")
    @ApiModelProperty(notes = "The meeting room city")
    private String city;

    @Column
    @NotNull
    @NotEmpty(message = "The county information of meeting room cannot be empty")
    @ApiModelProperty(notes = "The meeting room county")
    private String county;

    @Column
    @NotNull(message = "The capacity of meeting room information cannot be empty")
    @ApiModelProperty(notes = "The capacity of meeting room information")
    private int capacity;

    @Column
    private boolean isActive;

    @Column
    private boolean theLastState;

    @Column
    @Pattern(
            regexp = "([01]?[0-9]|2[0-3]):[0][0]",
            message = "Only hourly acceptable. For example : 12:00,13:00,14:00.."
    )
    @NotNull
    private String timeReservedStart;

    @Column
    @Pattern(
            regexp = "([01]?[0-9]|2[0-3]):[0][0]",
            message = "Only hourly acceptable. For example : 12:00,13:00,14:00.."
    )
    @NotNull
    private String timeReservedEnd;

}
