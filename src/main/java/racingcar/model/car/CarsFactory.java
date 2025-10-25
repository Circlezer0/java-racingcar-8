package racingcar.model.car;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import racingcar.exception.RaceException;
import racingcar.exception.code.CarErrorCode;

public class CarsFactory {

    private static final int MAX_CAR_COUNT = 10;

    private CarsFactory() {
        // 정적 팩토리 클래스이므로 인스턴스화 방지
    }

    public static Cars of(List<String> names) {
        validateNames(names);

        List<Car> cars = names.stream()
                .map(Car::of)
                .toList();

        return new Cars(cars);
    }

    private static void validateNames(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new RaceException(CarErrorCode.CAR_NAME_LIST_NOT_EMPTY);
        }

        if (names.size() > MAX_CAR_COUNT) {
            throw new RaceException(CarErrorCode.CAR_SIZE_EXCEEDED);
        }

        if (hasDuplicate(names)) {
            throw new RaceException(CarErrorCode.CAR_NAME_DUPLICATED);
        }
    }

    private static boolean hasDuplicate(List<String> names) {
        Set<String> uniqueNames = new HashSet<>(names);

        return uniqueNames.size() != names.size();
    }
}
