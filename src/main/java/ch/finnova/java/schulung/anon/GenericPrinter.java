package ch.finnova.java.schulung.anon;

public class GenericPrinter {
    public static void erstelleTollenDruck(Printer aPrinter) {
        aPrinter.print();
    }
}

interface Printer {
    void print();
}

class DruckerUser {
    public void machMalDruck() {
        GenericPrinter.erstelleTollenDruck(new Printer() {
            @Override
            public void print() {
                // drucke als PDF und in Farbe
            }
        });

        GenericPrinter.erstelleTollenDruck(new Printer() {
            @Override
            public void print() {
                // drucke als Postscript in Schwarz-Weiss
            }
        });
    }
}
