package com.demo.MeetingRoom.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

/**
 *
 * Represents a rooms.
 *
 * @author tugbaay
 * @version 1.0.0
 */
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rooms {
    private String roomId;
    private String roomName;
    private int capacity;
    private String workingHours;
    private List<Availability> availability;
}
