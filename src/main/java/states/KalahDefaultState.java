package states;

import org.springframework.beans.factory.annotation.Autowired;
import services.KalahGameServiceImpl;
import utils.KalahUtils;

/**
 * Created by gobet on 19-3-18.
 */
public class KalahDefaultState implements KalahState {

    @Autowired
    private KalahGameServiceImpl gameService;

    public KalahDefaultState(KalahGameServiceImpl service){
        this.gameService = service;
    }

    public String computeMove(int playerId, int swellNumber) {
        if(playerId != gameService.turn)
            throw new WrongTurnException();
        if(KalahUtils.isGameFinished(this.gameService.board, playerId)){
            this.gameService.setState(this.gameService.getFinishedGameState());
            return null;
        }

        int swellIndex = getSwellIndex(playerId, swellNumber);
        int stones = gameService.board[swellIndex];
        this.gameService.board[swellIndex] = 0;
        swellIndex++;
        while(stones > 0){
            if(swellIndex == 13 && this.gameService.turn == 1)
                swellIndex = 0;
            else if(swellIndex == 6 && this.gameService.turn == 2)
                swellIndex = 7;

            this.gameService.board[swellIndex]++;
            stones--;
            swellIndex++;
        }

        if(swellIndex != 6 && swellIndex != 13)
            this.gameService.switchTurn();

        return KalahUtils.printResult(this.gameService.board, this.gameService.turn);
    }

    private int getSwellIndex(int playerId, int swellNumber){
        return ((playerId -1) * 7) + swellNumber -1;
    }
}
