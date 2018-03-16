package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gobet on 16-3-18.
 */
@RestController
public class KalahRestController {
    @RequestMapping("/")
    public String greeting() {
        return "Holas";
    }
}

