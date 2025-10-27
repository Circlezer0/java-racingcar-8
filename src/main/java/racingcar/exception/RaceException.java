package racingcar.exception;

public class RaceException extends IllegalArgumentException {
    public RaceException(RaceErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
