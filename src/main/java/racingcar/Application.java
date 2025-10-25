package racingcar;

import racingcar.config.RaceConfig;
import racingcar.controller.RaceController;

public class Application {

    public static void main(String[] args) {
        RaceController raceController = RaceConfig.raceController();
        raceController.run();
    }
}
