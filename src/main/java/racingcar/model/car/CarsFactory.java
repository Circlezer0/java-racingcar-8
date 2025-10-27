package racingcar.model.car;

import java.util.List;
import racingcar.exception.RaceException;
import racingcar.exception.code.CarErrorCode;

public class CarsFactory {

    private CarsFactory() {
        // 정적 팩토리 클래스이므로 인스턴스화 방지
    }

    public static Cars of(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new RaceException(CarErrorCode.CAR_NAME_LIST_NOT_EMPTY);
        }

        List<Car> cars = names.stream()
                .map(Car::of)
                .toList();

        return new Cars(cars);
    }
}
