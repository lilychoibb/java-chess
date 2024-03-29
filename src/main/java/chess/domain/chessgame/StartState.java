package chess.domain.chessgame;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.List;

public class StartState implements GameCommandState {

    private static final int MOVE_SOURCE_SQUARE_INDEX = 0;
    private static final int TARGET_SQUARE_INDEX = 1;

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
        Square moveSource = moveSquare.get(MOVE_SOURCE_SQUARE_INDEX);
        Square target = moveSquare.get(TARGET_SQUARE_INDEX);
        chessGame.executeTurn(moveSource, target);
        return MoveState.getInstance();
    }
}
