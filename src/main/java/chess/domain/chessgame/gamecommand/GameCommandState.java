package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.ChessBoard;
import java.util.List;

public interface GameCommandState {

    StartState executeStartCommand();

    MoveState executeMoveCommand(ChessBoard chessBoard, List<String> input);

    StatusState executeStatusCommand();
}
