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

    public boolean isBlackCamp() {
        return this.equals(BLACK);
    }

    public boolean isWhiteCamp() {
        return this.equals(WHITE);
    }

    public boolean isNoneCamp() {
        return this.equals(NONE);
    }
}
