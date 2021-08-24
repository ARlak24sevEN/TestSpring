package com.example.backend.schedule;

import com.example.backend.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserSchedule {
    private final UserService userService;

    public UserSchedule(UserService userService) {
        this.userService = userService;
    }

    //UTC time in sever
    @Scheduled(cron = "0 * * * * *")
    public void testEveryMinute(){
        log.info("Hello");
    }
    //run every midnight
    @Scheduled(cron = "0 0 0 * * *")
    public void testEveryMidnight(){
        log.info("hello,what's up");
    }

    //set thai time
    //run every 20:11 AM
    @Scheduled(cron = "0 11 20 * * *",zone = "Asia/Bangkok")
    public void testEveryDay(){
        log.info("hello,20:11");
    }
}
