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
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Represents a meeting hall working time.
 *
 * @author tugbaay
 * @version 1.0.0
 */
@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel(description = "This class contains about meeting hall working hours table columns for database")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MeetingHallWorkingTime implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    @ApiModelProperty(notes = "The database generated meetingHallWorkingTime ID")
    private String id;

    @OneToOne(optional = true, fetch = FetchType.LAZY)
    private MeetingHall meetingHalls;

    @NotNull
    @ApiModelProperty(notes = "for 00:00")
    private boolean zero;

    @NotNull
    @ApiModelProperty(notes = "for 01:00")
    private boolean one;

    @NotNull
    @ApiModelProperty(notes = "for 02:00")
    private boolean two;

    @NotNull
    @ApiModelProperty(notes = "for 03:00")
    private boolean three;

    @NotNull
    @ApiModelProperty(notes = "for 04:00")
    private boolean four;

    @NotNull
    @ApiModelProperty(notes = "for 05:00")
    private boolean five;

    @NotNull
    @ApiModelProperty(notes = "for 06:00")
    private boolean six;

    @NotNull
    @ApiModelProperty(notes = "for 07:00")
    private boolean seven;

    @NotNull
    @ApiModelProperty(notes = "for 08:00")
    private boolean eight;

    @NotNull
    @ApiModelProperty(notes = "for 09:00")
    private boolean nine;

    @NotNull
    @ApiModelProperty(notes = "for 10:00")
    private boolean ten;

    @NotNull
    @ApiModelProperty(notes = "for 11:00")
    private boolean eleven;

    @NotNull
    @ApiModelProperty(notes = "for 12:00")
    private boolean twelve;

    @NotNull
    @ApiModelProperty(notes = "for 13:00")
    private boolean thirteen;

    @NotNull
    @ApiModelProperty(notes = "for 14:00")
    private boolean fourteen;

    @NotNull
    @ApiModelProperty(notes = "for 15:00")
    private boolean fifteen;

    @NotNull
    @ApiModelProperty(notes = "for 16:00")
    private boolean sixteen;

    @NotNull
    @ApiModelProperty(notes = "for 17:00")
    private boolean seventeen;

    @NotNull
    @ApiModelProperty(notes = "for 18:00")
    private boolean eighteen;

    @NotNull
    @ApiModelProperty(notes = "for 19:00")
    private boolean nineteen;

    @NotNull
    @ApiModelProperty(notes = "for 20:00")
    private boolean twenty;

    @NotNull
    @ApiModelProperty(notes = "for 21:00")
    private boolean twentyone;

    @NotNull
    @ApiModelProperty(notes = "for 22:00")
    private boolean twentytwo;

    @NotNull
    @ApiModelProperty(notes = "for 23:00")
    private boolean twentythree;
}
