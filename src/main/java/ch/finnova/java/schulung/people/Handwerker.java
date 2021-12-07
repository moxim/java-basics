package ch.finnova.java.schulung.people;

import java.util.List;

public class Handwerker extends Person {
    private List<Werkzeug> werkzeuge;

    public List<Werkzeug> showTools() {
        return werkzeuge;
    }
}
