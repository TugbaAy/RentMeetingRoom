package com.demo.MeetingRoom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoMeetingRoom extends RuntimeException
{
    public NoMeetingRoom(String exception) {
        super(exception);
    }
}
