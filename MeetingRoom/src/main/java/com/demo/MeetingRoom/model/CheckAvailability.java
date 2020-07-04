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
import java.io.Serializable;

/**
 * This entity checks the availability of the reservation request.
 *
 * @author tugbaay
 * @version 1.0.0
 */
@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel(description = "This entity checks the availability of the reservation request")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheckAvailability implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    @ApiModelProperty(notes = "The database generated checkAvailability ID")
    private String id;

    @Column
    @NotNull(message = "Reserved date cannot be null")
    @ApiModelProperty(notes = "This column shows the date the meeting room was reserved")
    private String dateReserved;

    @Column
    @NotNull
    @NotEmpty(message = "Reserved time start cannot be null")
    @ApiModelProperty(notes = "This column shows the time the meeting room was reserved")
    private String time;

    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Reservation reservation;

    @Column
    private boolean isAvailable;

}
