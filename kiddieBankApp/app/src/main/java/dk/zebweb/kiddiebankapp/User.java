package dk.zebweb.kiddiebankapp;

/**
 * Created by Lunding on 12/03/16.
 */
public class User {

    private String id;
    private String name;
    private int bankAccount;

    public User(String id, String name, int bankAccount) {
        this.id = id;
        this.name = name;
        this.bankAccount = bankAccount;
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
}
