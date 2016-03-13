package dk.zebweb.kiddiebankapp;

/**
 * Created by Lunding on 13/03/16.
 */
public class Gift {

    private String id;
    private int amount;
    private String giftId;
    private String giftName;
    private String senderId;
    private String senderName;
    private boolean goBuyLittleGuy;

    public Gift(){

    }

    public Gift(String id, int amount, String giftId, String giftName, String senderId, String senderName, boolean goBuyLittleGuy) {
        this.id = id;
        this.amount = amount;
        this.giftId = giftId;
        this.giftName = giftName;
        this.senderId = senderId;
        this.senderName = senderName;
        this.goBuyLittleGuy = goBuyLittleGuy;
    }

    public String getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public String getGiftId() {
        return giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public boolean isGoBuyLittleGuy() {
        return goBuyLittleGuy;
    }
}
