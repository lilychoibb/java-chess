package chess.domain.chessgame.gamecommand;

import java.util.Arrays;

public enum GameCommand {

    START("start"),
    MOVE("move"),
    END("end"),
    STATUS("status");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand findGameCommand(String input) {
        return Arrays.stream(GameCommand.values())
                .filter(command -> command.isAvailableCommand(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력값과 일치하는 게임 명령이 없습니다."));
    }

    private boolean isAvailableCommand(String input) {
        return command.equals(input);
    }

    public boolean isNotFinishedGame() {
        return !command.equals(END.command);
    }

    public boolean isGameStarted() {
        return command.equals(START.command);
    }

    public boolean isMovedChessPiece() {
        return command.equals(MOVE.command);
    }

    public boolean isCheckGameStatus() {
        return command.equals(STATUS.command);
    }
}
