package at.ac.tuwien.dtos;

public class PaymentModelNotification extends Notification {
    public PaymentModelNotification(String receiver, String name, int requests, double price) {
        String msg = "Dear " + name + ",<br />"
                + "your payment model has been changed to " + requests + " per day for "
                + price + " € per month. If you didn't perform this change, "
                + "please contact EHDP.<br /><br />"
                + "Kind regards<br />"
                + "the EHDP team";
        super.setReceiver(receiver);
        super.setSubject("Payment model was changed");
        super.setMessage(msg);
    }
}
