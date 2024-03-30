package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.ChessBoard;
import java.util.List;

public class EndState implements GameCommandState {

    private EndState() {
    }

    private static class SingleInstanceHolder {
        private static final EndState INSTANCE = new EndState();
    }

    public static EndState getInstance() {
        return EndState.SingleInstanceHolder.INSTANCE;
    }

    public StartState executeStartCommand() {
        return StartState.getInstance();
    }

    public MoveState executeMoveCommand(ChessBoard chessBoard, List<String> input) {
        throw new IllegalArgumentException("[ERROR] 게임이 시작되지 않아 움직일 수 없습니다.");
    }
}
