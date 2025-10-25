package racingcar.model.car;

import java.util.List;
import racingcar.model.strategy.MoveStrategy;

public class Cars {
    private final List<Car> carList;

    protected Cars(List<Car> carList) {
        this.carList = List.copyOf(carList);
    }


    public List<CarStatus> getCarStatuses() {
        return carList.stream()
                .map(car -> new CarStatus(car.getCarName(), car.getPosition()))
                .toList();
    }

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

    public void moveAll(MoveStrategy moveStrategy) {
       carList.forEach(car -> car.move(moveStrategy));
    }
}
