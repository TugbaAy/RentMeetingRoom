package com.demo.MeetingRoom.model;

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
import java.io.Serializable;
import java.util.*;

/**
 * Represents a reservation.
 *
 * @author tugbaay
 * @version 1.0.0
 */
@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel(description = "This class contains about reservation table columns for database")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    @ApiModelProperty(notes = "The database generated reservation ID")
    private String id;

    @Column
    @NotNull(message = "Reserved date cannot be null")
    @ApiModelProperty(notes = "This column shows the date the meeting room was reserved")
    private String dateReserved;

    @Column
    @Pattern(
            regexp = "([01]?[0-9]|2[0-3]):[0][0]",
            message = "Only hourly acceptable. For example : 12:00,13:00,14:00.."
    )
    @NotNull
    @NotEmpty(message = "Reserved time start cannot be null")
    @ApiModelProperty(notes = "This column shows the start of time the meeting room was reserved. It should be time of full. For example : 12:00,13:00")
    private String timeReservedStart;

    @Column
    @Pattern(
            regexp = "([01]?[0-9]|2[0-3]):[0][0]",
            message = "Only hourly acceptable. For example : 12:00,13:00,14:00.."
    )
    @NotNull
    @NotEmpty(message = "Reserved time end cannot be null")
    @ApiModelProperty(notes = "This column shows the end of time the meeting room was reserved. It should be time of full. For example : 12:00,13:00")
    private String timeReservedEnd;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Company company;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private MeetingHall meetingHalls;

    @Column
    private boolean isAvailable;

    public static Comparator<Reservation> sortTime = new Comparator<Reservation>() {

        public int compare(Reservation s1, Reservation s2) {
            int start1 = Integer.valueOf(s1.getTimeReservedStart().split(":")[0]);
            int start2 = Integer.valueOf(s2.getTimeReservedStart().split(":")[0]);

            return start1 - start2;
        }
    };
}
