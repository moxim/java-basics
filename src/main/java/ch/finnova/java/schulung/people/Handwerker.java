package ch.finnova.java.schulung.people;

import java.util.List;

public class Handwerker extends Person {
    private List werkzeuge;

    public List showTools() {
        return werkzeuge;
    }
}
