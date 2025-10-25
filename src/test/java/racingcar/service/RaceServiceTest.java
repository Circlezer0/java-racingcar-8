package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.car.CarName;
import racingcar.model.car.CarStatus;
import racingcar.model.car.Cars;
import racingcar.model.car.CarsFactory;
import racingcar.model.provider.IntProvider;
import racingcar.model.provider.SequenceIntProvider;
import racingcar.model.strategy.MoveStrategy;
import racingcar.model.strategy.ThresholdMoveStrategy;

public class RaceServiceTest {

    private Cars cars;
    private MoveStrategy moveStrategy;
    private List<CarStatus> recentCarStatuses;

    @BeforeEach
    void setUp() {
        List<String> carNames = List.of("car1", "car2", "car3");
        cars = CarsFactory.of(carNames);
        IntProvider intProvider = new SequenceIntProvider(new int[]{
                7, 1, 4,    // Round 1 (T F T)
                0, 3, 9,    // Round 2 (F F T)
                5, 2, 6,    // Round 3 (T F T)
                3, 2, 1,    // Round 4 (F F F)
                0, 9, 0     // Round 5 (F T F)
                // Result : 2, 1, 3
        });
        moveStrategy = new ThresholdMoveStrategy(intProvider, 4);
    }

    @Test
    @DisplayName("한 라운드 진행 테스트")
    void playRoundTest() {
        // given
        RaceService raceService = new RaceService(moveStrategy);

        // when
        recentCarStatuses = raceService.playRound(cars);

        // then
        Tuple[] expected = {
                tuple(new CarName("car1"), 1),
                tuple(new CarName("car2"), 0),
                tuple(new CarName("car3"), 1)
        };

        assertThat(recentCarStatuses)
                .extracting(CarStatus::carName, CarStatus::position)
                .containsExactly(expected);
    }

    @Test
    @DisplayName("모든 라운드 진행 테스트")
    void playAllRoundsTest() {
        // given
        RaceService raceService = new RaceService(moveStrategy);
        int totalRounds = 5;

        // when
        for (int i = 0; i < totalRounds; i++) {
            recentCarStatuses = raceService.playRound(cars);
        }

        // then
        Tuple[] expected = {
                tuple(new CarName("car1"), 2),
                tuple(new CarName("car2"), 1),
                tuple(new CarName("car3"), 3)
        };

        assertThat(recentCarStatuses)
                .extracting(CarStatus::carName, CarStatus::position)
                .containsExactly(expected);
    }

    @Test
    @DisplayName("우승자 결정 테스트")
    void getWinnersTest() {
        // given
        RaceService raceService = new RaceService(moveStrategy);
        int totalRounds = 5;

        // when
        for (int i = 0; i < totalRounds; i++) {
            recentCarStatuses = raceService.playRound(cars);
        }
        List<CarName> winners = raceService.getWinners(cars);

        // then
        assertThat(winners)
                .containsExactly(new CarName("car3"));
    }

    @Test
    @DisplayName("Cars 객체 변경 테스트")
    void changeCarsTest() {
        // given
        RaceService raceService = new RaceService(moveStrategy);
        Cars newCars = CarsFactory.of(List.of("alpha", "beta"));
        List<CarStatus> oldCarStatuses, newCarStatuses;

        // when
        raceService.playRound(cars);
        raceService.playRound(cars);
        oldCarStatuses = raceService.playRound(cars);

        raceService.playRound(newCars);
        raceService.playRound(newCars);
        newCarStatuses = raceService.playRound(newCars);

        // then
        Tuple[] expectedOldCars = {
                tuple(new CarName("car1"), 2),
                tuple(new CarName("car2"), 0),
                tuple(new CarName("car3"), 3)
        };
        assertThat(oldCarStatuses)
                .extracting(CarStatus::carName, CarStatus::position)
                .containsExactly(expectedOldCars);

        Tuple[] expectedNewCars = {
                tuple(new CarName("alpha"), 1),
                tuple(new CarName("beta"), 0)
        };
        assertThat(newCarStatuses)
                .extracting(CarStatus::carName, CarStatus::position)
                .containsExactly(expectedNewCars);
    }
}
