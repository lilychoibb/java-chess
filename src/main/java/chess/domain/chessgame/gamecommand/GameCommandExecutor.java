package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import java.util.List;

public class GameCommandExecutor {

    private GameCommandState gameCommandState;

    public GameCommandExecutor(GameCommandState gameCommandState) {
        this.gameCommandState = gameCommandState;
    }

    public void executeStartCommand() {
        gameCommandState = gameCommandState.executeStartCommand();
    }

    public void executeMoveCommand(ChessGame chessGame, List<Square> moveSquare) {
        gameCommandState = gameCommandState.executeMoveCommand(chessGame, moveSquare);
    }

    public void executeStatusCommand() {
        gameCommandState = gameCommandState.executeStatusCommand();
    }
}
