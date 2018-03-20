package states;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import services.KalahGameServiceImpl;
import utils.KalahUtils;

/**
 * Created by gobet on 19-3-18.
 */
public class KalahFinishedGameState implements KalahState {
    @Autowired
    private KalahGameServiceImpl gameService;
    private int winner = 0;

    public KalahFinishedGameState(KalahGameServiceImpl service){
        this.gameService = service;
    }
    public String computeMove(int playerId, int swellNumber) throws JsonProcessingException {
        if(this.gameService.winner == 0)
            getWinner();
        return KalahUtils.printResult(this.gameService.board, this.gameService.turn, this.winner);
    }

    private void getWinner(){
        if (gameService.board[6] > gameService.board[13]) {
            this.winner = 1;
            return;
        }
        this.winner = 2;
    }
}
