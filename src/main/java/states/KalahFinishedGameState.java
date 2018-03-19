package states;

import org.springframework.beans.factory.annotation.Autowired;
import services.KalahGameServiceImpl;

/**
 * Created by gobet on 19-3-18.
 */
public class KalahFinishedGameState implements KalahState {
    @Autowired
    private KalahGameServiceImpl gameService;

    public KalahFinishedGameState(KalahGameServiceImpl service){
        this.gameService = service;
    }
    public String computeMove(int playerId, int swellNumber) {
        return null;
    }
}
