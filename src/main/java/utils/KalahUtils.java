package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by gobet on 19-3-18.
 */
public class KalahUtils {
    public static boolean isGameFinished(int[] board, int playerId){
        int index = (playerId - 1) * 7;
        for(int i=index; i<index+6; i++){
            if(board[i] != 0)
                return false;
        }

        return true;
    }

    public static String printResult(int[] board, int turn, int winner) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        ArrayNode playerOne = mapper.createArrayNode();
        ArrayNode playerTwo = mapper.createArrayNode();

        ObjectNode aux = mapper.createObjectNode();;

        for(int i=0; i<6; i++){
            aux.put("Swell #" + (i+1) , board[i]);
        }
        playerOne.add(aux);
        aux.put("Goal: ", board[6]);

        aux = mapper.createObjectNode();;

        for(int i=7; i<13; i++){
            aux.put("Swell #" + (i-6) , board[i]);
        }

        playerTwo.add(aux);
        aux.put("Goal: ", board[13]);

        objectNode.putPOJO("Player One", playerOne);
        objectNode.putPOJO("Player Two", playerTwo);

        if(winner == 0)
            objectNode.putPOJO("Next turn", "Player " + turn);
        else{
            objectNode.putPOJO("Winner", "Player " + winner);
        }


        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
    }
}
