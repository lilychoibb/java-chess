package chess.domain.chesspiece;

public class ChessPiece {

    private final Camp camp;
    private final ChessPieceProperty chessPieceProperty;

    public ChessPiece(Camp camp, ChessPieceProperty chessPieceProperty) {
        this.camp = camp;
        this.chessPieceProperty = chessPieceProperty;
    }

    public ChessPieceType getChessPieceType() {
        return chessPieceProperty.getChessPieceType();
    }

    public boolean isEmptyChessPiece() {
        return ChessPieceType.isChessPieceTypeNone(getChessPieceType());
    }

    public boolean isChessPiecePawn() {
        return chessPieceProperty.getChessPieceType() == ChessPieceType.PAWN;
    }

    public Camp getCamp() {
        return camp;
    }

    public ChessPieceProperty getChessPieceProperty() {
        return chessPieceProperty;
    }
}
