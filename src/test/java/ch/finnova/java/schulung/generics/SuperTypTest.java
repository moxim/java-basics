package ch.finnova.java.schulung.generics;

import ch.finnova.java.schulung.people.Handwerker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SuperTypTest {
    @Test
    void someTest() {
        // given
        SuperTyp<Handwerker> superHandwerker = new SuperTyp<>(new Handwerker());

        // when
        Handwerker hw = superHandwerker.getPersonTyp();

        // then
        assertNotNull(superHandwerker, "Einen Superhandwerker zu haben wäre schon nützlich!");
        assertNotNull(hw, "Einen Handwerker zu haben wäre schon nützlich!");
        assertEquals(0, hw.showTools().size(), "Ein Superhandwerker braucht keine Werkzeuge!");
    }
}
