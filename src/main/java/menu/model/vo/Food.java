package menu.model.vo;

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
}
