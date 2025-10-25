package racingcar.exception;

public class RaceException extends IllegalArgumentException {
    public RaceException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
