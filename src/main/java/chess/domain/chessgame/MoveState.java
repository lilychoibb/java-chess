package chess.domain.chessgame;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.List;

public class MoveState implements GameCommandState{

    private MoveState() {}

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
        chessGame.executeTurn(moveSquare.get(0), moveSquare.get(1));
        return MoveState.getInstance();
    }
}
