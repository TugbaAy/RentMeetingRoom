package com.demo.MeetingRoom.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * This model expresses the availability of a meeting room.
 *
 * @author tugbaay
 * @version 1.0.0
 */
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Availability {
    private String meetingTime;
    private String state;
}
