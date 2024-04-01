package chess.controller;

import chess.dao.TurnDao;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.gamecommand.EndState;
import chess.domain.chessgame.gamecommand.GameCommand;
import chess.domain.chessgame.gamecommand.GameCommandExecutor;
import chess.domain.chesspiece.Camp;
import chess.dto.ChessBoardDto;
import chess.service.ChessGameService;
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
        ChessGameService chessGameService = new ChessGameService();
        try {
            chessGameService.initialSetting();
            executeGameUntilEnd(chessGameService, gameCommand, input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            gameStart();
        }
    }

    private void executeGameUntilEnd(ChessGameService chessGameService, GameCommand initialGameCommand,
                                     List<String> initialInput) {
        ChessBoard chessBoard = chessGameService.chessBoardSetting();
        GameCommand gameCommand = initialGameCommand;
        List<String> input = initialInput;
        GameCommandExecutor gameCommandExecutor = new GameCommandExecutor(EndState.getInstance());
        while (gameCommand.isNotFinishedGame()) {
            executeGameRound(chessBoard, gameCommand, gameCommandExecutor, input);
            if (isGameFinished(chessBoard)) {
                break;
            }
            input = repeatUntilSuccess(InputView::requestGameCommand);
            gameCommand = GameCommand.findGameCommand(input.get(GAME_COMMAND_INDEX));
        }
    }

    private void executeGameRound(ChessBoard chessBoard, GameCommand gameCommand,
                                  GameCommandExecutor gameCommandExecutor, List<String> input) {
        executeGameCommand(chessBoard, gameCommand, gameCommandExecutor, input);
        if (chessBoard.isKingDead()) {
            handleKingDead(chessBoard);
        }
        if (!chessBoard.isKingDead() && !gameCommand.isCheckGameStatus()) {
            OutputView.printTurn(new TurnDao().findTurn());
        }
    }

    private boolean isGameFinished(ChessBoard chessBoard) {
        return chessBoard.isKingDead();
    }

    private void handleKingDead(ChessBoard chessBoard) {
        Camp campKingAlive = chessBoard.campKingAlive();
        OutputView.printWhenKingDead(campKingAlive);
        new ChessGameService().resetChessGame();
    }

    private void executeGameCommand(ChessBoard chessBoard, GameCommand gameCommand,
                                    GameCommandExecutor gameCommandExecutor,
                                    List<String> input) {
        if (gameCommand.isGameStarted()) {
            executeStartCommand(gameCommandExecutor, chessBoard);
        }
        if (gameCommand.isMovedChessPiece()) {
            executeMoveCommand(chessBoard, input, gameCommandExecutor);
        }
        if (gameCommand.isCheckGameStatus()) {
            executeStatusCommand(gameCommandExecutor, chessBoard);
        }
    }

    private void executeStartCommand(GameCommandExecutor gameCommandExecutor, ChessBoard chessBoard) {
        gameCommandExecutor.executeStartCommand();
        printChessBoard(chessBoard);
    }

    private void executeMoveCommand(ChessBoard chessBoard, List<String> input,
                                    GameCommandExecutor gameCommandExecutor) {
        ChessGame chessGame = new ChessGame(chessBoard);
        List<Square> moveSquare = chessGame.settingMoveSquare(input);
        ChessGameService chessGameService = new ChessGameService();
        chessGameService.updateChessGame(chessBoard, moveSquare);
        gameCommandExecutor.executeMoveCommand(chessGame, moveSquare);
        printChessBoard(chessBoard);
    }

    private void executeStatusCommand(GameCommandExecutor gameCommandExecutor, ChessBoard chessBoard) {
        gameCommandExecutor.executeStatusCommand();
        OutputView.printGameStatus(chessBoard);
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
