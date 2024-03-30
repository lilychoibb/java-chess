package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.ChessBoard;
import java.util.List;

public class GameCommandExecutor {

    private GameCommandState gameCommandState;

    public GameCommandExecutor(GameCommandState gameCommandState) {
        this.gameCommandState = gameCommandState;
    }

    public void executeStartCommand() {
        gameCommandState = gameCommandState.executeStartCommand();
    }

    public void executeMoveCommand(ChessBoard chessBoard, List<String> input) {
        gameCommandState = gameCommandState.executeMoveCommand(chessBoard, input);
    }
}
