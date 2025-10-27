package racingcar.model.car;

import racingcar.exception.RaceException;
import racingcar.exception.RaceErrorCode;

public record RaceRound(int round) {
    private static final int MIN_ROUND = 0;
    private static final int MAX_ROUND = 100;

    public RaceRound {
        if (round < MIN_ROUND) {
            throw new RaceException(RaceErrorCode.NEGATIVE_ROUND);
        }
        if (round > MAX_ROUND) {
            throw new RaceException(RaceErrorCode.MAX_ROUND_EXCEEDED);
        }
    }
}
