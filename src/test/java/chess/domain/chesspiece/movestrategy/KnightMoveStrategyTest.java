package chess.domain.chesspiece.movestrategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Lettering;
import chess.domain.chessboard.Numbering;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.ChessPieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KnightMoveStrategyTest {

    private final ChessBoard chessBoard = new ChessBoard();

    @BeforeEach
    void setUp() {
        chessBoard.movePiece(new Square(Lettering.B, Numbering.TWO), new Square(Lettering.B, Numbering.THREE));
    }

    @Test
    void 목적지가_Knight가_움직일_수_있는_범위이면_움직인다() {
        chessBoard.move(new Square(Lettering.B, Numbering.ONE), new Square(Lettering.C, Numbering.THREE));
        ChessPiece destinationChessPiece = chessBoard.findChessPieceOnSquare(new Square(Lettering.C, Numbering.THREE));

        assertThat(destinationChessPiece.getChessPieceType()).isEqualTo(ChessPieceType.KNIGHT);
    }

    @Test
    void 목적지가_Knight가_움직일_수_있는_범위가_아니면_움직이지_않는다() {
        chessBoard.move(new Square(Lettering.B, Numbering.ONE), new Square(Lettering.B, Numbering.THREE));
        ChessPiece destinationChessPiece = chessBoard.findChessPieceOnSquare(new Square(Lettering.B, Numbering.THREE));

        assertThat(destinationChessPiece.getChessPieceType()).isNotEqualTo(ChessPieceType.KNIGHT);
    }
}
