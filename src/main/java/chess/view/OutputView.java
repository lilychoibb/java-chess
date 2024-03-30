package chess.view;

import chess.domain.chessboard.Numbering;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.ChessPiece;
import chess.dto.ChessBoardDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.printf("%s 체스 게임을 시작합니다. %s", ">", LINE_SEPARATOR);
        System.out.printf("%s 게임 시작 %s start %s", ">", ":", LINE_SEPARATOR);
        System.out.printf("%s 게임 종료 %s end %s", ">", ":", LINE_SEPARATOR);
        System.out.printf("%s 게임 이동 %s move source위치 target위치 - 예. move b2 b3 %s", ">", ":", LINE_SEPARATOR);
    }

    public static void printChessBoard(ChessBoardDto chessBoardDto) {
        Map<Square, ChessPiece> chessBoard = chessBoardDto.chessBoard();
        List<Numbering> numbering = reverseNumbering();

        for (Numbering number : numbering) {
            List<Square> chessRow = selectChessRow(number, chessBoard);
            printSquare(chessRow, chessBoard);
            System.out.println();
        }
    }

    private static List<Numbering> reverseNumbering() {
        List<Numbering> numbering = new ArrayList<>(List.of(Numbering.values()));
        Collections.reverse(numbering);
        return numbering;
    }

    private static List<Square> selectChessRow(Numbering number, Map<Square, ChessPiece> chessBoard) {
        return chessBoard.keySet().stream()
                .filter(square -> square.getNumbering() == number)
                .toList();
    }

    private static void printSquare(List<Square> chessRow, Map<Square, ChessPiece> chessBoard) {
        for (Square square : chessRow) {
            ChessPiece chessPiece = chessBoard.get(square);
            printSquareWithChessPiece(chessPiece);
        }
    }

    private static void printSquareWithChessPiece(ChessPiece chessPiece) {
        String chessPieceNotation = ChessPiecePrintFormat.findChessPieceNotation(chessPiece);
        System.out.print(chessPieceNotation);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
