package racingcar.model.car;

import java.util.List;
import racingcar.model.strategy.MoveStrategy;

public class Cars {
    private final List<Car> carList;

    protected Cars(List<Car> carList) {
        this.carList = List.copyOf(carList);
    }


    /**
     * 모든 자동차의 상태를 반환한다.
     * @return 모든 자동차의 상태 리스트 (이름, 위치)
     */
    public List<CarStatus> getCarStatuses() {
        return carList.stream()
                .map(CarStatus::from)
                .toList();
    }

    /**
     * 우승자(가장 멀리 간 자동차) 자동차 이름 리스트를 반환한다.
     * @return 우승자 자동차 이름 리스트
     */
    public List<CarName> calculateWinners() {
        int maxPosition = carList.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);

        return carList.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getCarName)
                .toList();
    }

    /**
     * 모든 자동차를 주어진 이동 전략에 따라 이동시킨다.
     * @param moveStrategy 이동 전략
     */
    public void moveAll(MoveStrategy moveStrategy) {
       carList.forEach(car -> car.move(moveStrategy));
    }
}
