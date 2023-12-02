package menu.model.vo;

import java.util.Objects;
import menu.exception.NonContainInMenuException;
import menu.model.Menu;

public class Food {

    private final String name;

    public Food(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (!isContainInMenu(name)) {
            throw new NonContainInMenuException();
        }
    }

    private boolean isContainInMenu(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getFood().contains(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }
}
