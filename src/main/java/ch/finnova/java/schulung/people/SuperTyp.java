package ch.finnova.java.schulung.people;

public class SuperTyp<T extends Person> {
    private T personType;

    public T getPersonType() {
        return personType;
    }
}

class Checker {
    public static void main(String[] args) {
        SuperTyp<Handwerker> superHandwerker = new SuperTyp<>();
        Handwerker hw = superHandwerker.getPersonType();

        SuperTyp<Programmierer> superProgrammierer = new SuperTyp<>();
        Programmierer p = superProgrammierer.getPersonType();


    }
}

class Programmierer extends Person {
    private int linesOfCode;

    public int showLinesOfCode() {
        return linesOfCode;
    }
}
