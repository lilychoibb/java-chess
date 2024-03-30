package chess.domain.chessgame;

import chess.domain.chesspiece.Camp;

public class GameStatus {

    public GameStatus() {
    }

    public Camp calculateWinCamp(GameScore blackCampGameScore, GameScore whiteCampGameScore) {
        if (blackCampGameScore.isTotalScoreHigh(whiteCampGameScore)) {
            return Camp.BLACK;
        }
        if (whiteCampGameScore.isTotalScoreHigh(blackCampGameScore)) {
            return Camp.WHITE;
        }
        return Camp.NONE;
    }
}
