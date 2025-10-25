package racingcar.controller;

import java.util.List;
import racingcar.model.car.CarName;
import racingcar.model.car.CarStatus;
import racingcar.model.car.Cars;
import racingcar.model.car.CarsFactory;
import racingcar.model.car.RaceRound;
import racingcar.service.ParseService;
import racingcar.service.RaceService;
import racingcar.view.RaceView;

public class RaceController {
    private final RaceService raceService;
    private final ParseService parseService;
    private final RaceView view;

    public RaceController(RaceService raceService, ParseService parseService, RaceView view) {
        this.raceService = raceService;
        this.parseService = parseService;
        this.view = view;
    }

    public void run() {
        String rawNames = view.readCarNames();
        List<String> names = parseService.parseCarNames(rawNames);
        RaceRound raceRound = new RaceRound(view.readTryCount());

        Cars cars = CarsFactory.of(names);

        view.displayRoundStartMessage();
        for (int i = 0; i < raceRound.round(); i++) {
            List<CarStatus> roundResult = raceService.playRound(cars);
            view.displayRoundResult(roundResult);
        }

        List<CarName> winners = raceService.getWinners(cars);
        view.displayWinners(winners);
    }
}
