package at.ac.tuwien.dtos;

public class ThresholdNotification extends Notification {
    public ThresholdNotification(String receiver, String service, boolean below) {
        String msg = "Hello,<br />"
                + "the number of requests for the service " + service + " is "
                + (below ? "below " : "above ") + "the defined threshold! <br />"
                + "Please check the contract!<br /><br />"
                + "Kind regards<br />"
                + "the EHDP team";
        super.setReceiver(receiver);
        super.setSubject("Threshold notification");
        super.setMessage(msg);
    }
}
