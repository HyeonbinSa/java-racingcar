package racingCar.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingCar.domain.Engine.Engine;
import racingCar.domain.Engine.RandomEngine;
import racingCar.exception.NoWinnerException;

public class RacingGame {

    public static final String ERROR_MESSAGE_NO_WINNER = "우승자가 존재하지 않습니다.";
    public static final String DELIMITER = ",";
    private final List<Car> cars;
    private int numOfRacingRound;

    public RacingGame(String[] carNames, int numOfRacingRound) {
        this.cars = prepareCars(carNames);
        this.numOfRacingRound = numOfRacingRound;
    }

    public static List<Car> prepareCars(String[] carNames) {
        return Arrays.stream(carNames)
            .map(RacingGame::makeCar)
            .collect(Collectors.toList());
    }

    private static Car makeCar(String carName){
        Engine engine = new RandomEngine();
        return new Car(carName,engine);
    }

    public void race() {
        racePerRound();
        numOfRacingRound--;
    }

    private void racePerRound() {
        cars.forEach(Car::run);
    }

    private int findMaxMove() {
        List<Integer> traces = cars.stream()
            .map(Car::getPosition)
            .collect(Collectors.toList());
        return traces.stream()
            .max(Integer::compare)
            .orElseThrow(() -> new NoWinnerException(ERROR_MESSAGE_NO_WINNER));
    }

    public boolean isEnd() {
        return numOfRacingRound == 0;
    }

    public String getWinners() {
        int max = findMaxMove();
        return cars.stream()
            .filter(car -> car.isOn(max))
            .map(Car::getName)
            .collect(Collectors.joining(DELIMITER));
    }

    public List<Car> getCars() {
        return cars;
    }
}