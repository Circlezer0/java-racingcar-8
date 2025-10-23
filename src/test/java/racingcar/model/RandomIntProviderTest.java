package racingcar.model;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RandomIntProviderTest {

    @ParameterizedTest(name = "min={0}, max={1}")
    @CsvSource({
            "0, 10",
            "5, 15"
    })
    void 최소값_최대값_설정_정상(int min, int max) {
        // when & then
        assertDoesNotThrow(() -> new RandomIntProvider(min, max));
    }

    @Test
    void 랜덤_정수_제공_테스트() {
        // given
        int min = 0, max = 10;
        int repeatCount = 100;
        RandomIntProvider intProvider = new RandomIntProvider(min, max);

        // when
        List<Integer> samples = new ArrayList<>();
        for (int i = 0; i < repeatCount; i++) {
            samples.add(intProvider.nextInt());
        }

        // then
        samples.forEach(v -> assertThat(v).isBetween(min, max));
    }

    @ParameterizedTest(name = "min={0}, max={1}")
    @CsvSource({
            "0, 0",
            "1, 1",
            "5, 5",
            "10, 10"
    })
    void 최소값_최대값_같을_때_항상_그_값만_제공(int min, int max) {
        // given
        RandomIntProvider intProvider = new RandomIntProvider(min, max);
        int repeatCount = 10;

        // when
        List<Integer> samples = new ArrayList<>();
        for (int i = 0; i < repeatCount; i++) {
            samples.add(intProvider.nextInt());
        }

        // then
        samples.forEach(v -> assertEquals(min, v));
    }

    @ParameterizedTest(name = "min={0}, max={1}")
    @CsvSource({
            "10, 5",
            "5, 4"
    })
    void 최소값_최대값_설정_오류_IllegalArgumentException(int min, int max) {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> new RandomIntProvider(min, max));
    }

    @ParameterizedTest(name = "min={0}, max={1}")
    @CsvSource({
            "-1, 5",
            "0, -10",
            "-5, -1"
    })
    void 음수_최소값_최대값_설정_오류_IllegalArgumentException(int min, int max) {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> new RandomIntProvider(min, max));
    }
}
