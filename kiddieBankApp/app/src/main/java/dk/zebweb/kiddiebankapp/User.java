package dk.zebweb.kiddiebankapp;

/**
 * Created by Lunding on 12/03/16.
 */
public class User {

    private String id;
    private String name;
    private int bankAccount;
    private int gender;

    public User(){

    }

    public User(String id, String name, int bankAccount, int gender) {
        this.id = id;
        this.name = name;
        this.bankAccount = bankAccount;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBankAccount() {
        return bankAccount;
    }

    public int getGender() {
        return gender;
    }
}
