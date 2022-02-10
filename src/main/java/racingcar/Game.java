package racingcar;

import java.util.Scanner;

public class Game {
    private Input input;
    private Output output;

    public Game() {
        input = new Input();
        output = new Output();
    }

    public void start() {
        inputCarNames();
    }

    private void inputCarNames() {
        output.inputCarNameMessage();
        String[] carNames = input.getNames();
        System.out.println(carNames[0]);
    }

}
