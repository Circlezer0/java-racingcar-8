package racingcar.model.strategy;

@FunctionalInterface
public interface MoveStrategy {
    boolean canMove();
}
