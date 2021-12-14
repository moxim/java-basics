package ch.finnova.java.schulung.people;

import java.util.List;

public class Handwerker extends Person { // is-A
    private List werkzeuge;              // has-A

    public List showTools() {
        return werkzeuge;
    }
}
