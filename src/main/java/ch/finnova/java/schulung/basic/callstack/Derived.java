package ch.finnova.java.schulung.basic.callstack;

public class Derived extends Base {
    public Derived(int parameter) {
        super(parameter);
    }

    public void doIt() {
        Derived d = new Derived(42);
    }
}
