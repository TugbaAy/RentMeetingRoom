package com.demo.MeetingRoom.operations;

import com.demo.MeetingRoom.model.MeetingHallWorkingTime;

public class FindHour {

    public static void setTimes(MeetingHallWorkingTime meetingHallWorkingTime, int hour) {
        switch (hour) {
            case 0:
                meetingHallWorkingTime.setZero(true);
                break;
            case 1:
                meetingHallWorkingTime.setOne(true);
                break;
            case 2:
                meetingHallWorkingTime.setTwo(true);
                break;
            case 3:
                meetingHallWorkingTime.setThree(true);
                break;
            case 4:
                meetingHallWorkingTime.setFour(true);
                break;
            case 5:
                meetingHallWorkingTime.setFive(true);
                break;
            case 6:
                meetingHallWorkingTime.setSix(true);
                break;
            case 7:
                meetingHallWorkingTime.setSeven(true);
                break;
            case 8:
                meetingHallWorkingTime.setEight(true);
                break;
            case 9:
                meetingHallWorkingTime.setNine(true);
                break;
            case 10:
                meetingHallWorkingTime.setTen(true);
                break;
            case 11:
                meetingHallWorkingTime.setEleven(true);
                break;
            case 12:
                meetingHallWorkingTime.setTwelve(true);
                break;
            case 13:
                meetingHallWorkingTime.setThirteen(true);
                break;
            case 14:
                meetingHallWorkingTime.setFourteen(true);
                break;
            case 15:
                meetingHallWorkingTime.setFifteen(true);
                break;
            case 16:
                meetingHallWorkingTime.setSixteen(true);
                break;
            case 17:
                meetingHallWorkingTime.setSeventeen(true);
                break;
            case 18:
                meetingHallWorkingTime.setEighteen(true);
                break;
            case 19:
                meetingHallWorkingTime.setNineteen(true);
                break;
            case 20:
                meetingHallWorkingTime.setTwenty(true);
                break;
            case 21:
                meetingHallWorkingTime.setTwentyone(true);
                break;
            case 22:
                meetingHallWorkingTime.setTwentytwo(true);
                break;
            case 23:
                meetingHallWorkingTime.setTwentythree(true);
                break;
        }

    }

    public boolean getTimes(MeetingHallWorkingTime meetingHallWorkingTime, int hour) {
        boolean value = false;
        switch (hour) {
            case 0:
                value = meetingHallWorkingTime.isZero();
                break;
            case 1:
                value = meetingHallWorkingTime.isOne();
                break;
            case 2:
                value = meetingHallWorkingTime.isTwo();
                break;
            case 3:
                value = meetingHallWorkingTime.isThree();
                break;
            case 4:
                value = meetingHallWorkingTime.isFour();
                break;
            case 5:
                value = meetingHallWorkingTime.isFive();
                break;
            case 6:
                value = meetingHallWorkingTime.isSix();
                break;
            case 7:
                value = meetingHallWorkingTime.isSeven();
                break;
            case 8:
                value = meetingHallWorkingTime.isEight();
                break;
            case 9:
                value = meetingHallWorkingTime.isNine();
                break;
            case 10:
                value = meetingHallWorkingTime.isTen();
                break;
            case 11:
                value = meetingHallWorkingTime.isEleven();
                break;
            case 12:
                value = meetingHallWorkingTime.isTwelve();
                break;
            case 13:
                value = meetingHallWorkingTime.isThirteen();
                break;
            case 14:
                value = meetingHallWorkingTime.isFourteen();
                break;
            case 15:
                value = meetingHallWorkingTime.isFifteen();
                break;
            case 16:
                value = meetingHallWorkingTime.isSixteen();
                break;
            case 17:
                value = meetingHallWorkingTime.isSeventeen();
                break;
            case 18:
                value = meetingHallWorkingTime.isEighteen();
                break;
            case 19:
                value = meetingHallWorkingTime.isNineteen();
                break;
            case 20:
                value = meetingHallWorkingTime.isTwenty();
                break;
            case 21:
                value = meetingHallWorkingTime.isTwentyone();
                break;
            case 22:
                value = meetingHallWorkingTime.isTwentytwo();
                break;
            case 23:
                value = meetingHallWorkingTime.isTwentythree();
                break;
        }
        return value;
    }

}
