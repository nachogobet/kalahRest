package utils;

/**
 * Created by gobet on 19-3-18.
 */
public class KalahUtils {
    public static boolean isGameFinished(int[] board, int playerId){
        int index = (playerId - 1) * 7;
        for(int i=index; i<index+7; i++){
            if(board[i] != 0)
                return false;
        }

        return true;
    }

    public static String printResult(int[] board, int turn) {
        StringBuilder resultBuilder = new StringBuilder("Player 1: \n");

        for(int i=0; i<6; i++){
            resultBuilder.append("Swell number " + (i + 1) + " has " + board[i] + " stones\n");
        }
        resultBuilder.append("Goal has " + board[6] + " stones\n");

        resultBuilder.append("\nPlayer 2:");

        for(int i=7; i<13; i++){
            resultBuilder.append("Swell number " + (i + 1) + " has " + board[i] + " stones\n");
        }
        resultBuilder.append("Goal has " + board[13] + " stones\n");

        return resultBuilder.toString();
    }
}
