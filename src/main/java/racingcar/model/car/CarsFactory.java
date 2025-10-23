package racingcar.model.car;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            throw new IllegalArgumentException("자동차 이름 목록은 null이거나 비어 있을 수 없습니다.");
        }

        if (names.size() > MAX_CAR_COUNT) {
            throw new IllegalArgumentException("자동차는 최대 " + MAX_CAR_COUNT + "대까지 생성할 수 있습니다.");
        }

        if (hasDuplicate(names)) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }

    private static boolean hasDuplicate(List<String> names) {
        Set<String> uniqueNames = new HashSet<>(names);

        return uniqueNames.size() != names.size();
    }
}
