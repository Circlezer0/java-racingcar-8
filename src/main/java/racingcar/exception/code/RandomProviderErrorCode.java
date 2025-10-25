package racingcar.exception.code;

import racingcar.exception.BaseErrorCode;

public enum RandomProviderErrorCode implements BaseErrorCode {
    MIN_BIGGER_THAN_MAX("최소값은 최대값보다 클 수 없습니다."),
    NEGATIVE_RANGE("최소값과 최대값은 음수일 수 없습니다.")
    ;

    private final String message;

    RandomProviderErrorCode(String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
