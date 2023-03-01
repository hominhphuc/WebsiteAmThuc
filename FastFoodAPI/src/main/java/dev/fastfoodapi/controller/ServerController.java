package dev.fastfoodapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
public class ServerController {

    @GetMapping
    String home() {
        return "Server đang chạy " + LocalDateTime.now().getDayOfMonth() +
                "-" + LocalDateTime.now().getMonth().getValue() +
                "-" + LocalDateTime.now().getYear() +
                " " + LocalDateTime.now().getHour() + " giờ " +
                LocalDateTime.now().getMinute() + " phút";
    }

}
