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
        return getChessPieceType().isChessPieceTypeNone();
    }

    public boolean isChessPiecePawn() {
        return getChessPieceType().isChessPieceTypePawn();
    }

    public boolean isChessPieceKing() {
        return getChessPieceType().isChessPieceTypeKing();
    }

    public boolean isChessPieceSameCamp(ChessPiece chessPiece) {
        return getCamp() == chessPiece.getCamp();
    }

    public Camp getCamp() {
        return camp;
    }

    public ChessPieceProperty getChessPieceProperty() {
        return chessPieceProperty;
    }
}
