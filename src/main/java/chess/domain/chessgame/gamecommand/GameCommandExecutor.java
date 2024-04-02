package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import chess.service.ChessGameService;
import java.util.List;

public class GameCommandExecutor {

    private GameCommandState gameCommandState;

    public GameCommandExecutor() {
        this.gameCommandState = EndState.getInstance();
    }

    public void setGameCommandState(GameCommandState gameCommandState) {
        this.gameCommandState = gameCommandState;
    }

    public void executeStartCommand(ChessBoard chessBoard) {
        gameCommandState.executeStartCommand(chessBoard);
    }

    public void executeMoveCommand(ChessGame chessGame, List<Square> moveSquare) {
        gameCommandState.executeMoveCommand(chessGame, moveSquare);
    }

    public void executeStatusCommand(ChessBoard chessBoard) {
        gameCommandState.executeStatusCommand(chessBoard);
    }

    public void execute(ChessBoard chessBoard, GameElements gameElements) {
        if (StartState.getInstance().isStartState(gameCommandState)) {
            executeStartCommand(chessBoard);
        }
        if (MoveState.getInstance().isMoveState(gameCommandState)) {
            executeMoveState(chessBoard, gameElements);
        }
        if (StatusState.getInstance().isStatusState(gameCommandState)) {
            executeStatusCommand(chessBoard);
        }
    }

    private void executeMoveState(ChessBoard chessBoard, GameElements gameElements) {
        ChessGame chessGame = new ChessGame(chessBoard);
        ChessGameService chessGameService = new ChessGameService();
        List<Square> moveSquares = gameElements.createMoveSquare();
        chessGameService.updateChessGame(chessBoard, moveSquares);
        executeMoveCommand(chessGame, moveSquares);
    }
}
