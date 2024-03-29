package chess.domain.chessgame;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.List;

public class StartState implements GameCommandState {

    private StartState() {}

    private static class SingleInstanceHolder {
        private static final StartState INSTANCE = new StartState();
    }

    public static StartState getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    @Override
    public StartState executeStartCommand() {
        throw new IllegalArgumentException();
    }

    @Override
    public MoveState executeMoveCommand(ChessBoard chessBoard, List<String> input) {
        ChessGame chessGame = new ChessGame(chessBoard);
        List<Square> moveSquare = chessGame.settingMoveSquare(input);
        chessGame.executeTurn(moveSquare.get(0), moveSquare.get(1));
        return MoveState.getInstance();
    }
}
