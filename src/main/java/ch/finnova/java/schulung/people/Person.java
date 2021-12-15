package ch.finnova.java.schulung.people;

public class Person {
    public enum GENDER {
        DIVERS("d"),
        FEMALE("f"),
        MALE("m");

        private String gender;

        GENDER(String s) {
            gender = s;
        }

        public String getGenderText() {
            return gender;
        }
    }

    public enum GENDER2 {
        DIVERS,
        FEMALE,
        MALE;
    }

    public static void main(String[] args) {
        Person p = new Person();

        System.out.println("--");
        System.out.println(GENDER.valueOf("MALE"));

        for (GENDER gender : GENDER.values()) {
            System.out.println(gender + " " + gender.getGenderText());
        }

        System.out.println("--");
        System.out.println(GENDER2.valueOf("MALE"));

        for (GENDER2 gender : GENDER2.values()) {
            System.out.println(gender);
        }
    }

}
