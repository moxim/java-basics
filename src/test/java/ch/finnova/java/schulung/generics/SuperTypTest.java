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

        superHandwerker.getPersonTyp().sayHello(new Handwerker(), "hi");
        // when
        Handwerker hw = superHandwerker.getPersonTyp();

        // then
        String message = "Einen Superhandwerker zu haben wäre schon nützlich!";
        assertNotNull(superHandwerker, message);
        assertNotNull(hw, "Einen Handwerker zu haben wäre schon nützlich!");
        assertEquals(0, hw.showTools().size(), "Ein Superhandwerker braucht keine Werkzeuge!");
    }
}
