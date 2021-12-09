package ch.finnova.java.schulung.anon;

public class AnonKlassenNutzer {
    private void createObjectOfAnonClass() {
        Marker m = new Marker() {
            @Override
            public void mark() {
                // hier passiert etwas tolles
            }
        };

        Pointer p = new Pointer() {
            @Override
            public void point() {
                // hier passiert etwas tolles
            }
        };
   }
}

interface Marker {
    void mark();
}

abstract class Pointer {
    public abstract void point();
}
