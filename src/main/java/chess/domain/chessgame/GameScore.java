package chess.domain.chessgame;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Lettering;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.Camp;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.ChessPieceType;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class GameScore {

    private static final double REDUCED_SCORE_FOR_PAWN = 0.5;

    private final double totalScore;

    public GameScore(ChessBoard chessBoard, Camp camp) {
        this.totalScore = calculateTotalScore(chessBoard, camp);
    }

    private double calculateTotalScore(ChessBoard chessBoard, Camp camp) {
        List<ChessPieceType> chessPieceTypes = chessPieceTypesByCamp(chessBoard, camp);
        double totalScore = chessPieceTypes.stream()
                .mapToDouble(ChessPieceType::findChessPieceScore)
                .sum();
        return totalScore - REDUCED_SCORE_FOR_PAWN * pawnCountWhereSameVerticalLine(chessBoard, camp);
    }

    private List<ChessPieceType> chessPieceTypesByCamp(ChessBoard chessBoard, Camp camp) {
        return filterChessPiecesByCamp(chessBoard, camp)
                .map(ChessPiece::getChessPieceType)
                .toList();
    }

    private Stream<ChessPiece> filterChessPiecesByCamp(ChessBoard chessBoard, Camp camp) {
        return chessBoard.getBoard()
                .values()
                .stream()
                .filter(chessPiece -> chessPiece.getCamp().isSameCamp(camp));
    }

    private long pawnCountWhereSameVerticalLine(ChessBoard chessBoard, Camp camp) {
        List<Lettering> letterings = extractPawnLetterings(chessBoard, camp);
        return countDuplicates(letterings);
    }

    private List<Lettering> extractPawnLetterings(ChessBoard chessBoard, Camp camp) {
        return filterChessPiecesByCamp(chessBoard, camp)
                .filter(ChessPiece::isChessPiecePawn)
                .map(chessPiece -> chessBoard.letteringOfSquare(findSquareOfChessPiece(chessBoard, chessPiece)))
                .toList();
    }

    private Square findSquareOfChessPiece(ChessBoard chessBoard, ChessPiece chessPiece) {
        return chessBoard.getBoard()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == chessPiece)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private long countDuplicates(List<Lettering> letterings) {
        return letterings.stream()
                .filter(lettering -> Collections.frequency(letterings, lettering) > 1)
                .count();
    }

    public boolean isTotalScoreHigh(GameScore gameScore) {
        return totalScore > gameScore.totalScore;
    }

    public double getTotalScore() {
        return totalScore;
    }
}
