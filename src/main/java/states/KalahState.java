package states;

import com.fasterxml.jackson.core.JsonProcessingException;
import exceptions.IllegalMoveException;
import exceptions.WrongTurnException;

/**
 * Created by gobet on 19-3-18.
 */
public interface KalahState {
    String computeMove(int playerId, int swellNumber) throws WrongTurnException, JsonProcessingException, IllegalMoveException;
}
