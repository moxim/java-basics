package ch.finnova.java.schulung.generics;

import ch.finnova.java.schulung.people.Handwerker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperTypTest {
    @Test
    public void someTest() {
        SuperTyp<Handwerker> superHandwerker = new SuperTyp<>();
        Handwerker hw = superHandwerker.getPersonTyp();
    }
}