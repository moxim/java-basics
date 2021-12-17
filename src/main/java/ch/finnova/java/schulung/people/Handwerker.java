package ch.finnova.java.schulung.people;

import java.util.ArrayList;
import java.util.List;

public class Handwerker extends Person {
    private List<Werkzeug> werkzeuge;

    public Handwerker() {
        werkzeuge = new ArrayList<>();
    }

    public List<Werkzeug> showTools() {
        return werkzeuge;
    }
}
