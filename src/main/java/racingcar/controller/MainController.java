package racingcar.controller;

import java.util.*;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.view.Input;
import racingcar.view.Output;

public class MainController {

    final private Input input;
    final private Output output;

    public MainController() {
        input = new Input();
        output = new Output();
    }

    public void start() {
        Cars cars = generateCar(inputCarNames());
        race(inputAttemptCount(), cars);
        output.printWinner(cars.getNamesOfWinners());
    }

    private String[] inputCarNames() {
        output.inputCarNameMessage();
        return input.getNames();
    }

    private int inputAttemptCount() {
        output.inputAttemptCountMessage();
        return input.inputAttemptCount();
    }

    private Cars generateCar(String[] names) {
        List<Car> cars = new ArrayList<>();
        Arrays.stream(names)
            .forEach(name -> cars.add(new Car(name)));
        return new Cars(cars);
    }

    private void race(int attemptCount, Cars cars) {
        output.printResultMessage();
        for (int index = 0; index < attemptCount; index++) {
            cars.moveCar();
            output.printPosition(cars);
        }
    }
}
