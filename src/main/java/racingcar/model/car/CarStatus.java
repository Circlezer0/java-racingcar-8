package racingcar.model.car;

public record CarStatus(
        CarName carName,
        int position
) {
    public static CarStatus from(Car car) {
        return new CarStatus(car.getCarName(), car.getPosition());
    }
}
