package menu.view;

import java.util.List;
import menu.dto.RecommendResultDto;

public class OutputView {

    public void printStartMessage() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
        System.out.println();
    }

    public void printResult(List<String> categoryNames, List<RecommendResultDto> results) {
        System.out.println("\n메뉴 추천 결과입니다.");
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");
        printRecommendMenu(categoryNames, results);
        System.out.println("\n추천을 완료했습니다.");
    }

    private void printRecommendMenu(List<String> categoryNames, List<RecommendResultDto> results) {
        System.out.println(drawCategoryNames(categoryNames));
        results.forEach(result -> System.out.println(drawEachCoachRecommendMenu(result)));
    }

    private String drawCategoryNames(List<String> categoryNames) {
        String categories = "[ 카테고리";
        for (String categoryName : categoryNames) {
            categories += String.format(" | %s", categoryName);
        }
        categories += " ]";
        return categories;
    }

    private String drawEachCoachRecommendMenu(RecommendResultDto recommendResultDto) {
        String recommended = String.format("[ %s", recommendResultDto.getCoachName());
        List<String> result = recommendResultDto.getResult();
        for (String eachFood : result) {
            recommended += String.format(" | %s", eachFood);
        }
        recommended += " ]";
        return recommended;
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
