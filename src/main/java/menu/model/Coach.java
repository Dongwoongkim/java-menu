package menu.model;

import menu.model.vo.Name;

public class Coach {

    private final Name name;

    private Coach(Name name) {
        this.name = name;
    }

    public static Coach createByName(String coachName) {
        Name name = new Name(coachName);
        return new Coach(name);
    }
}
