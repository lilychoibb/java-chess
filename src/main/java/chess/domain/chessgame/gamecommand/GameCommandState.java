package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import java.util.List;

public interface GameCommandState {

    StartState executeStartCommand();

    MoveState executeMoveCommand(ChessGame chessGame, List<Square> moveSquare);

    StatusState executeStatusCommand();
}
