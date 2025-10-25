package racingcar.exception.code;

import racingcar.exception.BaseErrorCode;

public enum ViewException implements BaseErrorCode {
    WINNER_CANNOT_BE_EMPTY("우승자는 한 명 이상이어야 합니다.")
    ;

    private final String message;

    ViewException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
