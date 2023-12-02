package menu.controller;

import java.util.List;
import menu.model.Coach;
import menu.model.Coaches;
import menu.model.vo.Food;
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
        initUnEatableFood(coaches);

    }

    private void initUnEatableFood(Coaches coaches) {
        coaches.getCoaches().forEach(coach -> {
            initUnEatableFoodEachCoach(coach);
        });
    }

    private void initUnEatableFoodEachCoach(Coach coach) {
        while (true) {
            try {
                String unEatableMenu = inputView.inputInedibleMenu(coach.getName());
                List<String> unEatableFoodNames = InputConverter.convertStringToStringListByDelimiter(unEatableMenu);
                coach.addUnEatableFood(unEatableFoodNames);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
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
