package racingcar.service;

import java.util.List;
import racingcar.model.car.CarName;
import racingcar.model.car.CarStatus;
import racingcar.model.car.Cars;
import racingcar.model.strategy.MoveStrategy;

public class RaceService {

    private final MoveStrategy strategy;

    public RaceService(MoveStrategy strategy) {
        this.strategy = strategy;
    }

    public List<CarStatus> playRound(Cars cars) {
        cars.moveAll(strategy);
        return cars.getCarStatuses();
    }

    public List<CarName> getWinners(Cars cars) {
        return cars.calculateWinners();
    }
}
