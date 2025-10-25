package racingcar.config;

import racingcar.controller.RaceController;
import racingcar.model.strategy.MoveStrategy;
import racingcar.service.ParseService;
import racingcar.service.RaceService;
import racingcar.view.RaceView;
import racingcar.view.handler.InputHandler;
import racingcar.view.handler.OutputHandler;

public class TestFixture {
    public static RaceController raceController(RaceService raceService, RaceView raceView) {
        ParseService parseService = new ParseService();
        return new RaceController(raceService, parseService, raceView);
    }
    public static RaceService raceService(MoveStrategy moveStrategy) {
        return new RaceService(moveStrategy);
    }
    public static RaceView raceView(InputHandler inputHandler, OutputHandler outputHandler) {
        return new RaceView(inputHandler, outputHandler);
    }
}
