package ch.finnova.java.schulung.db.model;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonTest {
    @Test
    public void testGetNameReturnsName() {
        // given
        final String NAME = "Duke";
        Person sut = new Person(NAME);

        // when
        String name = sut.getName();

        // then
        assertEquals(name, NAME);
    }

    @Test
    public void testMovingToAnAddressUpdatesTheAddressInhabitants() throws Exception {
        // given
        Address address = new Address();
        address.setId(4711L);
        address.setCity("Lenzburg");
        address.setPlz("5600");
        address.setStreet("Merkurstrasse");
        address.setNumber("6");

        Person sut = new Person("Duchess");
        sut.setId(1L);
        sut.setName("Hendrik");

        // when
        sut.moveTo(address);

        // then
        assertTrue(address.getInhabitants().contains(sut));
    }

    @Test
    public void showOffSerialization() throws Exception {
        boolean showOffSerialization = false;
        Assumptions.assumeTrue(showOffSerialization);

        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(System.out));
//                   new FileOutputStream("Beanarchive.xml")));
        ) {
            // given
            Address address = new Address();
            address.setId(4711L);
            address.setCity("Lenzburg");
            address.setPlz("5600");
            address.setStreet("Merkurstrasse");
            address.setNumber("6");

            Person sut = new Person("Duchess");
            sut.setId(1L);
            sut.setName("Hendrik");

            // when
            sut.moveTo(address);

            // then
            assertTrue(address.getInhabitants().contains(sut));

            encoder.writeObject(sut);
            encoder.flush();
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
}
