package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import java.util.List;

public class StartState implements GameCommandState {

    private static final int MOVE_SOURCE_SQUARE_INDEX = 0;
    private static final int TARGET_SQUARE_INDEX = 1;

    private StartState() {
    }

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
    public MoveState executeMoveCommand(ChessGame chessGame, List<Square> moveSquare) {
        Square moveSource = moveSquare.get(MOVE_SOURCE_SQUARE_INDEX);
        Square target = moveSquare.get(TARGET_SQUARE_INDEX);
        chessGame.executeTurn(moveSource, target);
        return MoveState.getInstance();
    }

    @Override
    public StatusState executeStatusCommand() {
        return StatusState.getInstance();
    }
}
