package racingcar;

import racingcar.controller.RaceController;
import racingcar.model.provider.IntProvider;
import racingcar.model.provider.RandomIntProvider;
import racingcar.model.strategy.MoveStrategy;
import racingcar.model.strategy.ThresholdMoveStrategy;
import racingcar.service.ParseService;
import racingcar.service.RaceService;
import racingcar.view.RaceView;
import racingcar.view.handler.InputHandler;
import racingcar.view.handler.OutputHandler;

public class Application {
    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 9;
    private static final int MOVE_THRESHOLD = 4;

    public static void main(String[] args) {

        RaceService raceService = createRaceService(RANDOM_MIN, RANDOM_MAX, MOVE_THRESHOLD);
        RaceView raceView = createRaceView();

        RaceController raceController = createRaceController(raceService, raceView);

        raceController.run();
    }

    private static RaceController createRaceController(RaceService raceService, RaceView raceView) {
        ParseService parseService = new ParseService();

        return new RaceController(raceService, parseService, raceView);
    }

    private static RaceService createRaceService(int randomMin, int randomMax, int moveThreshold) {
        IntProvider intProvider = new RandomIntProvider(randomMin, randomMax);
        MoveStrategy moveStrategy = new ThresholdMoveStrategy(intProvider, moveThreshold);
        return new RaceService(moveStrategy);
    }

    private static RaceView createRaceView() {
        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        return new RaceView(inputHandler, outputHandler);
    }

}
