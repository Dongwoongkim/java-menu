package menu.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import menu.exception.DuplicateUnEatableFoodException;
import menu.exception.OverMaxUnEatableFoodNameSize;
import menu.model.vo.Food;
import menu.model.vo.Name;

public class Coach {

    private static final Integer MAX_UNEATABLE_FOOD_SIZE = 2;
    private final Name name;
    private List<Food> unEatableFoods;
    private List<Food> plan;

    private Coach(Name name) {
        this.name = name;
        this.unEatableFoods = new ArrayList<Food>();
        this.plan = new ArrayList<>();
    }

    public static Coach createByName(String coachName) {
        Name name = new Name(coachName);
        return new Coach(name);
    }

    public void addUnEatableFood(List<String> unEatableFoodNames) {
        if (isEmpty(unEatableFoodNames)) {
            return;
        }

        validate(unEatableFoodNames);
        this.unEatableFoods = unEatableFoodNames.stream()
                .map(foodName -> new Food(foodName))
                .collect(Collectors.toUnmodifiableList());
    }

    private boolean isEmpty(List<String> unEatableFoodNames) {
        return unEatableFoodNames.get(0).isEmpty();
    }

    private void validate(List<String> foodNames) {
        if (isContainDuplicateFood(foodNames)) {
            throw new DuplicateUnEatableFoodException();
        }
        if (foodNames.size() > MAX_UNEATABLE_FOOD_SIZE) {
            throw new OverMaxUnEatableFoodNameSize(MAX_UNEATABLE_FOOD_SIZE);
        }
    }

    private boolean isContainDuplicateFood(List<String> foodNames) {
        Set<String> uniqueNames = new HashSet<>(foodNames);
        return uniqueNames.size() != foodNames.size();
    }

    public void makeOneDayPlan(final Integer categoryNumber, RandomFoodGenerator randomFoodGenerator) {
        List<String> foodNames = Menu.getFoodBySequence(categoryNumber);
        while (true) {
            String pickedFoodName = randomFoodGenerator.pickRandomFood(foodNames);
            Food food = new Food(pickedFoodName);
            if (!unEatableFoods.contains(food) && !plan.contains(pickedFoodName)) {
                this.plan.add(food);
                break;
            }
        }
    }

    public List<String> getPlan() {
        return plan.stream().map(food -> String.valueOf(food.getName())).collect(Collectors.toUnmodifiableList());
    }

    public String getName() {
        return name.getName();
    }
}
