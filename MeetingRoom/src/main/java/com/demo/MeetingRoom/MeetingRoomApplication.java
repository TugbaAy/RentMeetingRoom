package com.demo.MeetingRoom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@ComponentScan({"com.demo.MeetingRoom.controller",
  //      "com.demo.MeetingRoom.service"})
@EnableJpaRepositories("com.demo.MeetingRoom.repository")
@EnableSwagger2
public class MeetingRoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetingRoomApplication.class, args);
    }

}


/*
    //TO-DO-ALL
    1- Edit JAVADOC
    2- Edit All Exceptions
 */