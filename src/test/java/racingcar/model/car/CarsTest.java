package racingcar.model.car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.model.provider.IntProvider;
import racingcar.model.provider.SequenceIntProvider;
import racingcar.model.strategy.MoveStrategy;
import racingcar.model.strategy.ThresholdMoveStrategy;

class CarsTest {
    private static final int THRESHOLD = 4;
    private List<String> names;
    private Cars cars;

    @BeforeEach
    void setUp() {
        names = List.of("car1", "car2", "car3");
        cars = CarsFactory.of(names);
    }

    @Test
    void 자동차들_정상생성_테스트() {
        // when & then
        assertDoesNotThrow(() -> CarsFactory.of(names));
    }

    @Test
    void 입력_순서_유지_테스트() {
        // then
        String[] expected = names.toArray(new String[0]);

        assertThat(cars.getCarStatuses())
                .extracting(CarStatus::name)
                .containsExactly(expected);
    }

    @Test
    void 최대_개수_10개_테스트() {
        // given
        List<String> maxNames = List.of(
                "car1", "car2", "car3", "car4", "car5",
                "car6", "car7", "car8", "car9", "car10"
        );

        // when & then
        assertDoesNotThrow(() -> CarsFactory.of(maxNames));
    }

    @Test
    void 움직임_전략_적용_테스트() {
        // given
        IntProvider intProvider = new SequenceIntProvider(new int[]{
                5, 3, 9,    // Round 1 (T F T)
                2, 7, 1     // Round 2 (F T F)
        });
        MoveStrategy moveStrategy = new ThresholdMoveStrategy(intProvider, THRESHOLD);

        // when
        runRounds(cars, moveStrategy, 2);

        // then
        Tuple[] expected = {
                tuple("car1", 1),
                tuple("car2", 1),
                tuple("car3", 1)
        };

        assertThat(cars.getCarStatuses())
                .extracting(CarStatus::name, CarStatus::position)
                .containsExactly(expected);
    }

    @Test
    void 단독_우승_테스트() {
        // given
        IntProvider intProvider = new SequenceIntProvider(new int[]{
                9, 0, 0,    // Round 1 (T F F)
                8, 7, 2,    // Round 2 (T T F)
                6, 3, 1     // Round 3 (T F F)
        });
        MoveStrategy moveStrategy = new ThresholdMoveStrategy(intProvider, THRESHOLD);

        // when
        runRounds(cars, moveStrategy, 3);

        // then
        String expectedWinner = names.get(0);

        List<CarName> winners = cars.calculateWinners(); // 혹은 names() 등 너의 시그니처에 맞게 수정
        assertThat(winners).extracting(CarName::name)
                .containsExactly(expectedWinner);
    }

    @Test
    void 공동_우승자_테스트() {
        // given
        IntProvider intProvider = new SequenceIntProvider(new int[]{
                5, 4, 0,    // Round 1 (T T F)
                3, 6, 1,    // Round 2 (F T F)
                7, 2, 8     // Round 3 (T F T)
        });

        MoveStrategy moveStrategy = new ThresholdMoveStrategy(intProvider, THRESHOLD);

        // when
        runRounds(cars, moveStrategy, 3);

        // then
        String[] expectedWinners = {names.get(0), names.get(1)};

        List<CarName> winners = cars.calculateWinners();
        assertThat(winners).extracting(CarName::name)
                .containsExactly(expectedWinners);
    }

    @Test
    void 시도횟수_0일때_공동우승() {
        // when
        List<CarName> winners = cars.calculateWinners();

        // then
        String[] expectedWinners = {names.get(0), names.get(1), names.get(2)};

        assertThat(winners).extracting(CarName::name)
                .containsExactly(expectedWinners);
    }

    @Test
    void 빈_리스트_IllegalArgumentException() {
        // given
        List<String> emptyNames = List.of();

        // when & then
        assertThrows(IllegalArgumentException.class, () -> CarsFactory.of(emptyNames));
    }

    @Test
    void null_리스트_IllegalArgumentException() {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> CarsFactory.of(null));
    }

    @Test
    void 최대_개수_10개_초과시_IllegalArgumentException() {
        // given
        List<String> namesOverflow = List.of(
                "car1", "car2", "car3", "car4", "car5",
                "car6", "car7", "car8", "car9", "car10", "car11"
        );

        // when & then
        assertThrows(IllegalArgumentException.class, () -> CarsFactory.of(namesOverflow));
    }

    @Test
    void 중복_이름_입력시_IllegalArgumentException() {
        // given
        List<String> duplicatedNames = List.of("car1", "car2", "car1");

        // when & then
        assertThrows(IllegalArgumentException.class, () -> CarsFactory.of(duplicatedNames));
    }

    private void runRounds(Cars cars, MoveStrategy moveStrategy, int rounds) {
        for (int i = 0; i < rounds; i++) {
            cars.moveAll(moveStrategy);
        }
    }
}
