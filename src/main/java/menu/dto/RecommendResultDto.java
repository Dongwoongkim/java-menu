package menu.dto;

import java.util.Collections;
import java.util.List;

public class RecommendResultDto {

    private final List<String> result;
    private final String coachName;

    public RecommendResultDto(List<String> result, String coachName) {
        this.result = result;
        this.coachName = coachName;
    }

    public static RecommendResultDto create(List<String> result, String coachName) {
        return new RecommendResultDto(result, coachName);
    }

    public List<String> getResult() {
        return Collections.unmodifiableList(result);
    }

    public String getCoachName() {
        return coachName;
    }
}
