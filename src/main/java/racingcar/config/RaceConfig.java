package racingcar.config;

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

public class RaceConfig {

    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 9;
    private static final int MOVE_THRESHOLD = 4;

    public static RaceController raceController() {
        RaceService raceService = raceService();
        RaceView raceView = raceView();
        ParseService parseService = new ParseService();

        return new RaceController(raceService, parseService, raceView);
    }

    private static RaceService raceService() {
        IntProvider intProvider = new RandomIntProvider(RANDOM_MIN, RANDOM_MAX);
        MoveStrategy moveStrategy = new ThresholdMoveStrategy(intProvider, MOVE_THRESHOLD);
        return new RaceService(moveStrategy);
    }

    private static RaceView raceView() {
        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        return new RaceView(inputHandler, outputHandler);
    }
}
