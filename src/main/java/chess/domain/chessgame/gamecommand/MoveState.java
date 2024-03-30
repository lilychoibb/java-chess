package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import java.util.List;

public class MoveState implements GameCommandState {

    private static final int MOVE_SOURCE_SQUARE_INDEX = 0;
    private static final int TARGET_SQUARE_INDEX = 1;

    private MoveState() {
    }

    private static class SingleInstanceHolder {
        private static final MoveState INSTANCE = new MoveState();
    }

    public static MoveState getInstance() {
        return MoveState.SingleInstanceHolder.INSTANCE;
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
