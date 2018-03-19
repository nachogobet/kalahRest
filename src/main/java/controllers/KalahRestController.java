package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.KalahGameService;

/**
 * Created by gobet on 16-3-18.
 */
@RestController
public class KalahRestController {

    @Autowired
    KalahGameService gameService;

    @RequestMapping("/move")
    public String move(@RequestParam(value="playerId") int playerId, @RequestParam(value="swellNumber") int swellNumber) {
        String elmer = gameService.computeMove(playerId, swellNumber);

        return elmer;
    }
}

