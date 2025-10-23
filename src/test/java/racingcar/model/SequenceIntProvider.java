package racingcar.model;

public class SequenceIntProvider implements IntProvider {
    private final int[] numberSequence;
    private int currentPosition;

    public SequenceIntProvider(int[] numberSequence) {
        this.numberSequence = numberSequence;
        this.currentPosition = 0;
    }

    @Override
    public int nextInt() {
        if(currentPosition >= numberSequence.length) {
            throw new IllegalArgumentException("숫자 시퀀스의 범위를 초과했습니다.");
        }
        return numberSequence[currentPosition++];
    }
}
