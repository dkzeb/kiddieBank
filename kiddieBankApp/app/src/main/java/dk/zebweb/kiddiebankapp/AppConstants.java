package dk.zebweb.kiddiebankapp;

/**
 * Created by Lunding on 12/03/16.
 */
public class AppConstants {

    public static final String CHILD_ID = "child_id";
    public static final String CHILD_NAME = "child_name";
    public static final String ITEM_ID = "item_id";
    public static final String ITEM_NAME = "item_name";
    public static final String ITEM_PRICE = "item_price";
    public static final String ITEM_BALANCE = "item_balance";
    public static final String AMOUNT = "amount";
    public static final String GIFT_OBJECT = "gift_object";



    public static int getNotififyID(String id){
        int hash = 7;
        for (int i = 0; i < id.length(); i++) {
            hash = hash*31 + id.charAt(i);
        }
        return hash;
    }
}
