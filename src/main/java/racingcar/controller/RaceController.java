package racingcar.controller;

import java.util.List;
import racingcar.model.car.CarName;
import racingcar.model.car.CarStatus;
import racingcar.model.car.Cars;
import racingcar.model.car.CarsFactory;
import racingcar.service.ParseService;
import racingcar.service.RaceService;
import racingcar.view.RaceView;

public class RaceController {

    private static final int MAX_TRY_COUNT = 20;

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
        int rounds = view.readTryCount();

        validateRounds(rounds);

        Cars cars = CarsFactory.of(names);

        view.displayRoundStartMessage();
        for (int i = 0; i < rounds; i++) {
            List<CarStatus> roundResult = raceService.playRound(cars);
            view.displayRoundResult(roundResult);
        }

        List<CarName> winners = raceService.getWinners(cars);
        view.displayWinners(winners);
    }

    private void validateRounds(int rounds) {
        if (rounds < 1 || rounds > MAX_TRY_COUNT) {
            throw new IllegalArgumentException("시도 횟수는 1 이상 " + MAX_TRY_COUNT + " 이하만 가능합니다.");
        }
    }
}
