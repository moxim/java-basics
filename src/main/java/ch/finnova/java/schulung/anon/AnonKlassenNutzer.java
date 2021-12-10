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

        Painter o = new Painter() {
            @Override
            public void doIt() {
                super.doIt();
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

class Painter {
    public void doIt()  {
        // cool stuff happens here
    }
}
