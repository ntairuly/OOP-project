package university;

import university.core.UniversitySystem;

public class Main {
    public static void main(String[] args) {
        UniversitySystem uSystem = UniversitySystem.getInstance();
        uSystem.start();
    }
}