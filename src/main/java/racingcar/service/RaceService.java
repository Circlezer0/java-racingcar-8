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

    /**
     * 한 라운드 동안 모든 자동차를 이동시키고, 이동 후 자동차 상태를 반환한다.
     * @param cars 이동시킬 자동차들
     * @return 이동 후 자동차 상태 리스트
     */
    public List<CarStatus> playRound(Cars cars) {
        cars.moveAll(strategy);
        return cars.getCarStatuses();
    }

    /**
     * 우승자(가장 멀리 간 자동차) 자동차 이름 리스트를 반환한다.
     * @param cars 자동차들
     * @return 우승자 자동차 이름 리스트
     */
    public List<CarName> getWinners(Cars cars) {
        return cars.calculateWinners();
    }
}
