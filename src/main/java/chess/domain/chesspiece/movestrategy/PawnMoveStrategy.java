package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.ChessPiece;
import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {

    private boolean isStartingPosition = true;

    @Override
    public void move(ChessBoard chessBoard, Square startSquare, Square targetSquare) {
        List<Square> moveRange = createMoveRange(chessBoard, startSquare).getMoveRange();

        if (moveRange.contains(targetSquare)) {
            chessBoard.movePiece(startSquare, targetSquare);
            isStartingPosition = false;
        }
    }

    private MoveRange createMoveRange(ChessBoard chessBoard, Square startSquare) {
        MoveRange moveRange = new MoveRange();
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(startSquare);
        if (chessPiece.getCamp().isBlackCamp()) {
            createMoveRangeByCampBlack(moveRange, chessBoard, startSquare);
        }
        if (chessPiece.getCamp().isWhiteCamp()) {
            createMoveRangeByCampWhite(moveRange, chessBoard, startSquare);
        }
        return moveRange;
    }

    private void createMoveRangeByCampBlack(MoveRange moveRange, ChessBoard chessBoard, Square startSquare) {
        moveRange.addBackward(chessBoard, startSquare);
        if (isStartingPosition) {
            moveRange.addBackward(chessBoard, moveRange.firstMoveRange());
        }
        moveRange.addLeftBackwardDiagonal(chessBoard, startSquare);
        moveRange.addRightBackwardDiagonal(chessBoard, startSquare);
    }

    private void createMoveRangeByCampWhite(MoveRange moveRange, ChessBoard chessBoard, Square startSquare) {
        moveRange.addForward(chessBoard, startSquare);
        if (isStartingPosition) {
            moveRange.addForward(chessBoard, moveRange.firstMoveRange());
        }
        moveRange.addLeftForwardDiagonal(chessBoard, startSquare);
        moveRange.addRightForwardDiagonal(chessBoard, startSquare);
    }
}
