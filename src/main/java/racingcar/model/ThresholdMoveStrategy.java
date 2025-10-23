package racingcar.model;

public class ThresholdMoveStrategy implements MoveStrategy{

    private final IntProvider intProvider;
    private final int threshold;

    public ThresholdMoveStrategy(IntProvider intProvider, int threshold) {
        this.intProvider = intProvider;
        this.threshold = threshold;
    }

    @Override
    public boolean canMove() {
        return intProvider.nextInt() >= threshold;
    }
}
