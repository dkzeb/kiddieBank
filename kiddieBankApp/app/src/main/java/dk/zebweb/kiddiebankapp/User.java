package dk.zebweb.kiddiebankapp;

/**
 * Created by Lunding on 12/03/16.
 */
public class User {

    private String name;
    private int bankAccount;

    public User(String name, int bankAccount) {
        this.name = name;
        this.bankAccount = bankAccount;
    }

    public String getName() {
        return name;
    }

    public int getBankAccount() {
        return bankAccount;
    }
}
