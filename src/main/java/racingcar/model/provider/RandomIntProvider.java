package racingcar.model.provider;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.exception.RaceException;
import racingcar.exception.code.RandomProviderErrorCode;

// 최소, 최대 범위 내의 랜덤 정수를 제공하는 객체
public class RandomIntProvider implements IntProvider {
    private final int min;
    private final int max;

    public RandomIntProvider(int min, int max) {
        rangeValidation(min, max);
        this.min = min;
        this.max = max;
    }

    @Override
    public int nextInt() {
        return Randoms.pickNumberInRange(min, max);
    }

    private void rangeValidation(int min, int max) {
        if (min > max) {
            throw new RaceException(RandomProviderErrorCode.MIN_BIGGER_THAN_MAX);
        }
        if (min < 0) {
            throw new RaceException(RandomProviderErrorCode.NEGATIVE_RANGE);
        }
    }
}
