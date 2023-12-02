package menu.controller;

import java.util.List;
import java.util.stream.Collectors;
import menu.dto.RecommendResultDto;
import menu.model.Coach;
import menu.model.Coaches;
import menu.model.Menu;
import menu.model.MenuGenerator;
import menu.model.RandomMenuGenerator;
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

        MenuGenerator menuGenerator = new RandomMenuGenerator();
        List<Integer> categoryNumbers = decideCategories(menuGenerator);
        recommendMenu(coaches, categoryNumbers, menuGenerator);

        List<RecommendResultDto> results = getResults(coaches);
        showResult(categoryNumbers, results);
    }

    private List<Integer> decideCategories(MenuGenerator menuGenerator) {
        List<Integer> categoryNumbers = menuGenerator.pickCategory();
        return categoryNumbers;
    }

    private List<RecommendResultDto> getResults(Coaches coaches) {
        List<RecommendResultDto> results = coaches.getCoaches().stream()
                .map(coach -> RecommendResultDto.create(coach.getPlan(), coach.getName()))
                .collect(Collectors.toUnmodifiableList());
        return results;
    }

    private void recommendMenu(Coaches coaches, List<Integer> categoryNumbers, MenuGenerator menuGenerator) {
        for (Integer categoryNumber : categoryNumbers) {
            coaches.getCoaches().forEach(coach -> coach.makeOneDayPlan(categoryNumber, menuGenerator));
        }
    }

    private void showResult(List<Integer> categoryNumbers, List<RecommendResultDto> results) {
        List<String> categoryNames = categoryNumbers.stream()
                .map(categoryNumber -> Menu.getNameBySequence(categoryNumber))
                .collect(Collectors.toUnmodifiableList());
        outputView.printResult(categoryNames, results);
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
