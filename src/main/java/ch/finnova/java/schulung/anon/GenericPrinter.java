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
        GenericPrinter.erstelleTollenDruck(() -> {
            // drucke als PDF und in Farbe
        });

        GenericPrinter.erstelleTollenDruck(() -> {
            // drucke als Postscript in Schwarz-Weiss
        });
    }
}
