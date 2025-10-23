package racingcar.model.car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("자동차들 정상생성 테스트")
    void createCarsTest() {
        // when & then
        assertDoesNotThrow(() -> CarsFactory.of(names));
    }

    @Test
    @DisplayName("입력 순서 유지 테스트")
    void createOrderTest() {
        // then
        String[] expected = names.toArray(new String[0]);

        assertThat(cars.getCarStatuses())
                .extracting(CarStatus::name)
                .containsExactly(expected);
    }

    @Test
    @DisplayName("최대 개수 10개 테스트")
    void createMaxCarsTest() {
        // given
        List<String> maxNames = List.of(
                "car1", "car2", "car3", "car4", "car5",
                "car6", "car7", "car8", "car9", "car10"
        );

        // when & then
        assertDoesNotThrow(() -> CarsFactory.of(maxNames));
    }

    @Test
    @DisplayName("움직임 전략 적용 테스트")
    void moveStrategyTest() {
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
    @DisplayName("단독 우승자 테스트")
    void soloWinnerTest() {
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
    @DisplayName("공동 우승자 테스트")
    void multipleWinnersTest() {
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
    @DisplayName("시도 횟수 0일 때 공동 우승 테스트")
    void zeroRoundMultipleWinnersTest() {
        // when
        List<CarName> winners = cars.calculateWinners();

        // then
        String[] expectedWinners = {names.get(0), names.get(1), names.get(2)};

        assertThat(winners).extracting(CarName::name)
                .containsExactly(expectedWinners);
    }

    @Test
    @DisplayName("빈 리스트 입력시 IllegalArgumentException")
    void emptyListErrorTest() {
        // given
        List<String> emptyNames = List.of();

        // when & then
        assertThrows(IllegalArgumentException.class, () -> CarsFactory.of(emptyNames));
    }

    @Test
    @DisplayName("null 리스트 입력시 IllegalArgumentException")
    void nullListErrorTest() {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> CarsFactory.of(null));
    }

    @Test
    @DisplayName("최대 개수 10개 초과시 IllegalArgumentException")
    void exceedMaxCarsErrorTest() {
        // given
        List<String> namesOverflow = List.of(
                "car1", "car2", "car3", "car4", "car5",
                "car6", "car7", "car8", "car9", "car10", "car11"
        );

        // when & then
        assertThrows(IllegalArgumentException.class, () -> CarsFactory.of(namesOverflow));
    }

    @Test
    @DisplayName("중복 이름 입력시 IllegalArgumentException")
    void duplicateNamesErrorTest() {
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
