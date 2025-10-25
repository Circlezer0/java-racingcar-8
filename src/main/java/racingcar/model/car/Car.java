package racingcar.model.car;

import racingcar.model.strategy.MoveStrategy;

public class Car {
    private final CarName name;
    private int position;

    private Car(CarName name) {
        this.name = name;
        this.position = 0;
    }

    public static Car of(String name) {
        CarName carName = new CarName(name);
        return new Car(carName);
    }

    public CarName getCarName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    /**
     * 전달 받은 이동 전략에 따라 자동차를 이동시킨다.
     * @param strategy 이동 전략
     */
    public void move(MoveStrategy strategy) {
        if (strategy.canMove()) {
            position++;
        }
    }
}
