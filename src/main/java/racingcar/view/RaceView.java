package racingcar.view;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.model.car.CarName;
import racingcar.model.car.CarStatus;
import racingcar.view.handler.InputHandler;
import racingcar.view.handler.OutputHandler;

public class RaceView {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    private static final String INPUT_CAR_NAMES = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String INPUT_TRY_COUNT = "시도할 횟수는 몇 회인가요?";
    private static final String ROUND_START_MESSAGE = "실행 결과";
    private static final String OUTPUT_WINNER = "최종 우승자 : ";

    private static final String CAR_STATUS_FORMAT = "%s : %s";
    private static final String POSITION_MARKER = "-";
    private static final String WINNER_DELIMITER = ", ";


    public RaceView(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public String readCarNames() {
        outputHandler.printLine(INPUT_CAR_NAMES);
        return inputHandler.readLine();
    }

    public int readTryCount() {
        outputHandler.printLine(INPUT_TRY_COUNT);
        return inputHandler.readInt();
    }

    public void displayRoundStartMessage() {
        outputHandler.printLine();
        outputHandler.printLine(ROUND_START_MESSAGE);
    }

    public void displayRoundResult(List<CarStatus> carStatuses) {
        carStatuses.stream()
                .map(this::formatCarStatus)
                .forEach(outputHandler::printLine);

        outputHandler.printLine();
    }

    public void displayWinners(List<CarName> winnerNames) {
        String formattedWinners = winnerNames.stream()
                .map(CarName::name)
                .collect(Collectors.joining(WINNER_DELIMITER));

        outputHandler.printLine(OUTPUT_WINNER + formattedWinners);
    }

    private String formatCarStatus(CarStatus status) {
        String positionMarkers = POSITION_MARKER.repeat(Math.max(0, status.position()));
        return String.format(CAR_STATUS_FORMAT, status.carName().name(), positionMarkers);
    }
}
