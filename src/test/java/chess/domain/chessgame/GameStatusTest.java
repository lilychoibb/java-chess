package chess.domain.chessgame;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Lettering;
import chess.domain.chessboard.Numbering;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.Camp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStatusTest {

    @DisplayName("현재 이기고 있는 진영을 올바르게 계산한다")
    @Test
    void testCalculateWinCamp() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(new Square(Lettering.A, Numbering.TWO), new Square(Lettering.A, Numbering.SEVEN));
        GameScore blackCampGameScore = new GameScore(chessBoard, Camp.BLACK);
        GameScore whiteCampGameScore = new GameScore(chessBoard, Camp.WHITE);
        GameStatus gameStatus = new GameStatus();
        Camp winCamp = gameStatus.calculateWinCamp(blackCampGameScore, whiteCampGameScore);
        assertThat(winCamp).isEqualTo(Camp.WHITE);
    }
}
