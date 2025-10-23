package racingcar.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ThresholdMoveStrategyTest {

    @Test
    void 움직임_전략_테스트() {
        // given
        int[] sequence = new int[] {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        };
        int threshold = 4;

        // when
        IntProvider intProvider = new SequenceIntProvider(sequence);
        MoveStrategy strategy = new ThresholdMoveStrategy(intProvider, threshold);

        // then
        for(int i = 0; i < sequence.length; i++) {
            boolean actualCanMove = strategy.canMove();
            boolean expectedCanMove = (sequence[i] >= threshold);
            assertEquals(expectedCanMove, actualCanMove);
        }
    }

    @ParameterizedTest(name = "value={0}, threshold={1} => canMove={2}")
    @CsvSource({
            "3, 4, false",   // 아래
            "4, 4, true",    // 같음
            "5, 4, true"     // 초과
    })
    void 경계값_비교_테스트(int value, int threshold, boolean expected) {
        // given
        int[] sequence = new int[] {value};

        // when
        IntProvider intProvider = new SequenceIntProvider(sequence);
        MoveStrategy moveStrategy = new ThresholdMoveStrategy(intProvider, threshold);

        // then
        boolean actual = moveStrategy.canMove();
        assertEquals(expected, actual);
    }

    @Test
    void 시퀀스_소진시_예외() {
        // given
        int[] sequence = {1};
        int threshold = 4;

        // when
        IntProvider intProvider = new SequenceIntProvider(sequence);
        MoveStrategy moveStrategy = new ThresholdMoveStrategy(intProvider, threshold);

        // 1회는 정상
        moveStrategy.canMove();

        // 2번째 호출 시 provider가 소진되어 예외
        assertThrows(IllegalArgumentException.class, moveStrategy::canMove);
    }
}
