package ch.finnova.java.schulung.generics;

import ch.finnova.java.schulung.people.Handwerker;
import ch.finnova.java.schulung.people.Person;
import ch.finnova.java.schulung.people.Werkzeug;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperTypTest {
    @Test
    public void someTest() {
        // given
        SuperTyp<Handwerker> superHandwerker = new SuperTyp<>(new Handwerker());

        // when
        Handwerker hw = superHandwerker.getPersonTyp();

        // then
        assertNotNull(superHandwerker, "Einen Superhandwerker zu haben wäre schon nützlich!");
        assertNotNull(hw, "Einen Handwerker zu haben wäre schon nützlich!");
        assertEquals(hw.showTools().size(), 0, "Ein Superhandwerker braucht keine Werkzeuge!");
    }
}