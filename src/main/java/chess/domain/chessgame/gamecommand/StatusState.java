package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import java.util.List;

public class StatusState implements GameCommandState {

    private static final int MOVE_SOURCE_SQUARE_INDEX = 0;
    private static final int TARGET_SQUARE_INDEX = 1;

    private StatusState() {
    }

    private static class SingleInstanceHolder {
        private static final StatusState INSTANCE = new StatusState();
    }

    public static StatusState getInstance() {
        return StatusState.SingleInstanceHolder.INSTANCE;
    }

    @Override
    public StartState executeStartCommand() {
        throw new IllegalArgumentException("[ERROR] 이미 시작되었습니다.");
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

    @Override
    public StatusState executeStatusCommand() {
        throw new IllegalArgumentException("[ERROR] 이미 현황을 확인했습니다.");
    }
}
