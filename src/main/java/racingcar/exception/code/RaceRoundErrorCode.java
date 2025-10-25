package racingcar.exception.code;

import racingcar.exception.BaseErrorCode;

public enum RaceRoundErrorCode implements BaseErrorCode {
    NEGATIVE_ROUND("경기 라운드는 0 이상이어야 합니다."),
    MAX_ROUND_EXCEEDED("경기 라운드의 최대값을 초과했습니다.")
    ;

    private final String message;

    RaceRoundErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
