package menu.controller;

import menu.model.Coaches;
import menu.util.InputConverter;
import menu.util.InputValidator;
import menu.view.InputView;
import menu.view.OutputView;

public class RecommendController {

    private final InputView inputView;
    private final OutputView outputView;

    public RecommendController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        Coaches coaches = initCoaches();
    }

    private Coaches initCoaches() {
        while (true) {
            try {
                String coachNames = inputView.inputCoachNames();
                InputValidator.validateCoachNames(coachNames);
                return Coaches.createByNames(InputConverter.convertStringToStringListByDelimiter(coachNames));
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
