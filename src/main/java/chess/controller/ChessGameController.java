package chess.controller;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.gamecommand.EndState;
import chess.domain.chessgame.gamecommand.GameCommandState;
import chess.domain.chessgame.gamecommand.GameElements;
import chess.domain.chesspiece.Camp;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.function.Supplier;

public class ChessGameController {

    public void run() {
        OutputView.printStartMessage();
        gameStart();
    }

    private void gameStart() {
        ChessGameService chessGameService = new ChessGameService();
        try {
            chessGameService.initialSetting();
            ChessBoard chessBoard = chessGameService.chessBoardSetting();
            executeGameCommand(chessBoard);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            gameStart();
        }
    }

    private void executeGameCommand(ChessBoard chessBoard) {
        ChessGame chessGame = new ChessGame(chessBoard);
        GameElements gameElements = repeatUntilSuccess(InputView::requestGameCommand);
        GameCommandState gameCommandState = gameElements.findGameCommandState();
        while (!EndState.getInstance().isEndState(gameCommandState)) {
            chessGame.executeGame(gameCommandState, gameElements);
            OutputView.printGameExecute(gameCommandState, chessBoard);
            if (chessGame.isGameFinished()) {
                handleKingDead(chessGame);
                new ChessGameService().resetChessGame();
                break;
            }
            gameElements = repeatUntilSuccess(InputView::requestGameCommand);
            gameCommandState = gameElements.findGameCommandState();
        }
    }

    private void handleKingDead(ChessGame chessGame) {
        Camp campKingAlive = chessGame.findCampKingAlive();
        OutputView.printWhenKingDead(campKingAlive);
    }

    private <T> T repeatUntilSuccess(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            return repeatUntilSuccess(supplier);
        }
    }
}
