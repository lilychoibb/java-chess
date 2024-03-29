package chess.controller;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessgame.EndState;
import chess.domain.chessgame.GameCommand;
import chess.domain.chessgame.GameCommandExecutor;
import chess.dto.ChessBoardDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class ChessGameController {

    private static final Integer GAME_COMMAND_INDEX = 0;

    public void run() {
        OutputView.printStartMessage();
        gameStart();
    }

    private void gameStart() {
        List<String> input = repeatUntilSuccess(InputView::requestGameCommand);
        String inputCommand = input.get(GAME_COMMAND_INDEX);
        GameCommand gameCommand = GameCommand.findGameCommand(inputCommand);
        try {
            executeGameUntilEnd(gameCommand, input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            gameStart();
        }
    }

    private void executeGameUntilEnd(GameCommand initialGameCommand, List<String> initialInput) {
        ChessBoard chessBoard = new ChessBoard();
        GameCommand gameCommand = initialGameCommand;
        List<String> input = initialInput;
        GameCommandExecutor gameCommandExecutor = new GameCommandExecutor(EndState.getInstance());
        while (gameCommand.isNotFinishedGame()) {
            executeGameCommand(chessBoard, gameCommand, gameCommandExecutor, input);
            input = repeatUntilSuccess(InputView::requestGameCommand);
            gameCommand = GameCommand.findGameCommand(input.get(GAME_COMMAND_INDEX));
        }
    }

    private void executeGameCommand(ChessBoard chessBoard, GameCommand gameCommand,
                                    GameCommandExecutor gameCommandExecutor,
                                    List<String> input) {
        if (gameCommand.isGameStarted()) {
            gameCommandExecutor.executeStartCommand();
        }
        if (gameCommand.isMovedChessPiece()) {
            gameCommandExecutor.executeMoveCommand(chessBoard, input);
        }
        printChessBoard(chessBoard);
    }

    private void printChessBoard(ChessBoard chessBoard) {
        ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard);
        OutputView.printChessBoard(chessBoardDto);
    }

    private <T> T repeatUntilSuccess(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            return repeatUntilSuccess(supplier);
        }
    }
}
