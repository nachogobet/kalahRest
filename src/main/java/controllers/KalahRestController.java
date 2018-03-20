package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import exceptions.IllegalMoveException;
import exceptions.WrongTurnException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.KalahGameService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by gobet on 16-3-18.
 */
@RestController
public class KalahRestController {

    @Autowired
    KalahGameService gameService;

    @RequestMapping(value = "/move", produces = "application/json")
    public String move(@RequestParam(value="playerId") int playerId, @RequestParam(value="swellNumber") int swellNumber, HttpServletResponse response) {
        String json = "";
        try {
            json = gameService.computeMove(playerId, swellNumber);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (WrongTurnException|IllegalMoveException e){
            json = "HTTP RESPONSE " + HttpServletResponse.SC_FORBIDDEN + " " + e.getMessage();
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } catch (JsonProcessingException e){
            json = "HTTP RESPONSE " + HttpServletResponse.SC_FORBIDDEN + " " + e.getMessage();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return json;
    }
}

