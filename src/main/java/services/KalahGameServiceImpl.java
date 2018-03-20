package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import exceptions.IllegalMoveException;
import exceptions.WrongTurnException;
import org.springframework.stereotype.Service;
import states.KalahDefaultState;
import states.KalahFinishedGameState;
import states.KalahState;

/**
 * Created by gobet on 19-3-18.
 */

@Service
public class KalahGameServiceImpl implements KalahGameService {
    private KalahState startState = new KalahDefaultState(this);
    private KalahState finishedGameState = new KalahFinishedGameState(this);
    private KalahState state = startState;
    public int winner = 0;
    public int turn = 1;
    public int[] board = {4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};


    public String computeMove(int playerId, int swellNumber) throws WrongTurnException, JsonProcessingException, IllegalMoveException {
        return this.state.computeMove(playerId, swellNumber);
    }

    public void switchTurn(){
        turn = this.turn == 1 ? 2 : 1;
    }

    public KalahState getFinishedGameState() {
        return finishedGameState;
    }

    public void setState(KalahState state){
        this.state = state;
    }
}
