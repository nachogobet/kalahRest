package states;

import com.fasterxml.jackson.core.JsonProcessingException;
import exceptions.IllegalMoveException;
import exceptions.WrongTurnException;
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

    public String computeMove(int playerId, int swellNumber) throws WrongTurnException, JsonProcessingException, IllegalMoveException {
        if(playerId != gameService.turn)
            throw new WrongTurnException("It's the other player's turn!");
        if(KalahUtils.isGameFinished(this.gameService.board, playerId)){
            this.gameService.setState(this.gameService.getFinishedGameState());
            return this.gameService.computeMove(playerId, swellNumber);
        }

        int swellIndex = getSwellIndex(playerId, swellNumber);

        if(gameService.board[swellIndex] == 0 || swellIndex == 6 || swellIndex == 13){
            throw new IllegalMoveException("That's not a valid move!");
        }

        int stones = gameService.board[swellIndex];
        this.gameService.board[swellIndex] = 0;
        swellIndex++;
        while(stones > 0 && swellIndex < 14){
            if(swellIndex == 13 && this.gameService.turn == 1)
                swellIndex = 0;
            else if(swellIndex == 6 && this.gameService.turn == 2)
                swellIndex++;

            this.gameService.board[swellIndex]++;
            stones--;
            swellIndex++;

            if(stones > 0 && swellIndex == 14)
                swellIndex = 0;
        }

        if(swellIndex != 7 && swellIndex != 14)
            this.gameService.switchTurn();

        if(KalahUtils.isGameFinished(this.gameService.board, this.gameService.turn)){
            this.gameService.setState(this.gameService.getFinishedGameState());
            return this.gameService.computeMove(playerId, swellNumber);
        }

        return KalahUtils.printResult(this.gameService.board, this.gameService.turn, 0);
    }

    private int getSwellIndex(int playerId, int swellNumber) throws IllegalMoveException {
        if(swellNumber <1 || swellNumber > 6)
            throw new IllegalMoveException("That's not a valid move!");
        return ((playerId -1) * 7) + swellNumber -1;
    }
}
