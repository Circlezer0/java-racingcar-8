package racingcar.model.provider;

import camp.nextstep.edu.missionutils.Randoms;

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
            throw new IllegalArgumentException("최소값은 최대값보다 클 수 없습니다.");
        }
        if (min < 0) {
            throw new IllegalArgumentException("최소값과 최대값은 음수일 수 없습니다.");
        }
    }
}
