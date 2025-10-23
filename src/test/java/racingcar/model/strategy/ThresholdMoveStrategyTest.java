package racingcar.model.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racingcar.model.provider.IntProvider;
import racingcar.model.provider.SequenceIntProvider;

public class ThresholdMoveStrategyTest {

    @Test
    @DisplayName("움직임 전략 테스트")
    void moveStrategyTest() {
        // given
        int[] sequence = new int[]{
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        };
        int threshold = 4;

        IntProvider intProvider = new SequenceIntProvider(sequence);
        MoveStrategy strategy = new ThresholdMoveStrategy(intProvider, threshold);

        // when & then
        for (int currentInt : sequence) {
            boolean actualCanMove = strategy.canMove();
            boolean expectedCanMove = (currentInt >= threshold);
            assertEquals(expectedCanMove, actualCanMove);
        }
    }

    @ParameterizedTest(name = "value={0}, threshold={1} => canMove={2}")
    @DisplayName("경계값 비교 테스트")
    @CsvSource({
            "3, 4, false",   // 아래
            "4, 4, true",    // 같음
            "5, 4, true"     // 초과
    })
    void boundaryValueTest(int value, int threshold, boolean expected) {
        // given
        int[] sequence = new int[]{value};

        // when
        IntProvider intProvider = new SequenceIntProvider(sequence);
        MoveStrategy moveStrategy = new ThresholdMoveStrategy(intProvider, threshold);

        // then
        boolean actual = moveStrategy.canMove();
        assertEquals(expected, actual);
    }
}
