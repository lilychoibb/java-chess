package chess.domain.chessgame;

import chess.domain.chessboard.ChessBoard;
import java.util.List;

public interface GameCommandState {

    StartState executeStartCommand();

    MoveState executeMoveCommand(ChessBoard chessBoard, List<String> input);
}
