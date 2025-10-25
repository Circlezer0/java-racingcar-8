package racingcar.model.provider;

public class SequenceIntProvider implements IntProvider {
    private final int[] numberSequence;
    private int currentPosition;

    private static final String ERROR_NULL_SEQUENCE = "시퀀스는 null일 수 없습니다.";
    private static final String ERROR_OUT_OF_BOUNDS = "숫자 시퀀스의 범위를 초과했습니다.";

    public SequenceIntProvider(int[] numberSequence) {
        if(numberSequence == null) {
            throw new IllegalArgumentException(ERROR_NULL_SEQUENCE);
        }
        this.numberSequence = numberSequence;
        this.currentPosition = 0;
    }

    @Override
    public int nextInt() {
        if(currentPosition >= numberSequence.length) {
            throw new IllegalArgumentException(ERROR_OUT_OF_BOUNDS);
        }
        return numberSequence[currentPosition++];
    }
}
