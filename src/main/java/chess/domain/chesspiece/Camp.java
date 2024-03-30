package chess.domain.chesspiece;

public enum Camp {

    BLACK,
    WHITE,
    NONE;

    Camp() {
    }

    public boolean isSameCamp(Camp camp) {
        return this.equals(camp);
    }
}
