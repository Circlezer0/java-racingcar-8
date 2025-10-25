package racingcar.model.strategy;

// 자동차가 이동할 수 있는지 여부를 결정하는 전략 인터페이스
@FunctionalInterface
public interface MoveStrategy {
    boolean canMove();
}
