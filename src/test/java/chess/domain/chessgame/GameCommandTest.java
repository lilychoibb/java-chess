package chess.domain.chessgame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.chessgame.gamecommand.GameCommand;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GameCommandTest {

    @Test
    void 입력값에_일치하는_GameCommand를_올바르게_반환한다() {
        assertAll(
                () -> assertThat(GameCommand.findGameCommand("start")).isEqualTo(GameCommand.START),
                () -> assertThat(GameCommand.findGameCommand("move")).isEqualTo(GameCommand.MOVE),
                () -> assertThat(GameCommand.findGameCommand("end")).isEqualTo(GameCommand.END)
        );
    }

    @Test
    void GameCommand가_END가_아닐_경우_true를_반환한다() {
        GameCommand gameCommand = GameCommand.START;
        assertThat(gameCommand.isNotFinishedGame()).isTrue();
    }

    @Test
    void GameCommand가_START일_경우_true를_반환한다() {
        GameCommand gameCommand = GameCommand.START;
        assertThat(gameCommand.isGameStarted()).isTrue();
    }

    @Test
    void GameCommand가_MOVE일_경우_true를_반환한다() {
        GameCommand gameCommand = GameCommand.MOVE;
        assertThat(gameCommand.isMovedChessPiece()).isTrue();
    }
}
