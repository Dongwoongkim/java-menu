package menu;


import menu.controller.RecommendController;
import menu.view.InputView;
import menu.view.OutputView;

public class Application {
    public static void main(String[] args) {
        RecommendController recommendController = new RecommendController(new InputView(), new OutputView());
        recommendController.run();
    }
}
