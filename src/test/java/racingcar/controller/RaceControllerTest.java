package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.config.TestFixture;
import racingcar.model.provider.IntProvider;
import racingcar.model.provider.SequenceIntProvider;
import racingcar.model.strategy.MoveStrategy;
import racingcar.model.strategy.ThresholdMoveStrategy;
import racingcar.service.RaceService;
import racingcar.view.RaceView;
import racingcar.view.handler.FakeInputHandler;
import racingcar.view.handler.SpyOutputHandler;

@DisplayName("RaceController 흐름 제어 테스트")
class RaceControllerTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("strictScenarios")
    @DisplayName("입력, 라운드, 결과 출력 순서가 올바른가")
    void strictOutputFormatTest(
            String description,
            String carNames,
            String tryCount,
            int[] moveValues,
            String[] expectedSequence
    ) {

        // given
        FakeInputHandler inputHandler = new FakeInputHandler(carNames, tryCount);
        SpyOutputHandler outputHandler = new SpyOutputHandler();

        IntProvider intProvider = new SequenceIntProvider(moveValues);
        MoveStrategy moveStrategy = new ThresholdMoveStrategy(intProvider, 4);

        RaceView raceView = TestFixture.raceView(inputHandler, outputHandler);
        RaceService raceService = TestFixture.raceService(moveStrategy);
        RaceController raceController = TestFixture.raceController(raceService, raceView);

        // when
        raceController.run();

        // then
        assertThat(outputHandler.getLines())
                .containsSequence(expectedSequence);
    }

    private static Stream<Arguments> strictScenarios() {
        return Stream.of(
                Arguments.of(
                        "2대, 2회 시도",
                        "car1, car2", "2",
                        new int[]{4, 3, 5, 9}, // Round1: (T,F), Round2: (T,T)
                        new String[]{
                                "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)",
                                "시도할 횟수는 몇 회인가요?",
                                "실행 결과",
                                "car1 : -",
                                "car2 : ",
                                "car1 : --",
                                "car2 : -",
                                "최종 우승자 : car1"
                        }
                ),
                Arguments.of(
                        "3대, 4회 시도",
                        "a, b, c", "4",
                        new int[]{
                                4, 3, 5,   // Round1: (T,F,T)
                                9, 0, 9,   // Round2: (T,F,T)
                                3, 8, 2,   // Round3: (F,T,F)
                                9, 9, 9    // Round4: (T,T,T)
                        },
                        new String[]{
                                "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)",
                                "시도할 횟수는 몇 회인가요?",
                                "실행 결과",
                                "a : -",
                                "b : ",
                                "c : -",
                                "a : --",
                                "b : ",
                                "c : --",
                                "a : --",
                                "b : -",
                                "c : --",
                                "a : ---",
                                "b : --",
                                "c : ---",
                                "최종 우승자 : a, c"
                        }
                ),
                Arguments.of(
                        "4대, 0회 시도",
                        "car1, car2, car3, car4", "0",
                        new int[]{},
                        new String[]{
                                "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)",
                                "시도할 횟수는 몇 회인가요?",
                                "실행 결과",
                                "최종 우승자 : car1, car2, car3, car4"
                        }
                )
        );
    }


    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleScenarios")
    @DisplayName("흐름이 정상적으로 끝나는지 간단 검증")
    void simpleFlowTest(
            String description,
            String carNames,
            String tryCount,
            int[] moveValues,
            String[] expectedKeywords
    ) {

        // given
        FakeInputHandler inputHandler = new FakeInputHandler(carNames, tryCount);
        SpyOutputHandler outputHandler = new SpyOutputHandler();

        IntProvider intProvider = new SequenceIntProvider(moveValues);
        MoveStrategy moveStrategy = new ThresholdMoveStrategy(intProvider, 4);

        RaceView raceView = TestFixture.raceView(inputHandler, outputHandler);
        RaceService raceService = TestFixture.raceService(moveStrategy);
        RaceController raceController = TestFixture.raceController(raceService, raceView);

        // when
        raceController.run();

        // then
        for (String expected : expectedKeywords) {
            assertThat(outputHandler.getLines())
                    .anySatisfy(line -> assertThat(line).contains(expected));
        }
    }

    private static Stream<Arguments> simpleScenarios() {
        return Stream.of(
                Arguments.of(
                        "3대, 모두 같은 위치 -> 공동 우승",
                        "a, b, c", "2",
                        new int[]{5, 5, 5, 3, 3, 3},
                        new String[]{"최종 우승자 : a, b, c"}
                ),
                Arguments.of(
                        "3대, 한 대만 전진 -> 단독 우승",
                        "x, y, z", "3",
                        new int[]{9, 1, 0, 9, 1, 0, 9, 1, 0},
                        new String[]{"최종 우승자 : x"}
                ),
                Arguments.of(
                        "입력 이름 공백 포함 -> trim 처리 후 정상 동작",
                        "  red , blue  , green ",
                        "1",
                        new int[]{9, 1, 5},
                        new String[]{"최종 우승자 : red, green"}
                )
        );
    }
}
