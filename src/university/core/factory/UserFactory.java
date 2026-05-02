package university.core.factory;


import university.core.*;

public abstract class UserFactory {
    protected UniversitySystem uSystem;
    
    protected UserFactory(UniversitySystem uSystem) {
        this.uSystem = uSystem;
    }

    protected  abstract User createUser(String email, String password);

    public void addUser(String email, String password){
        User newUser = createUser(email, password);
        uSystem.getUsers().add(newUser);
        System.out.println("Successful creation");
        System.out.println(newUser);
    }
}