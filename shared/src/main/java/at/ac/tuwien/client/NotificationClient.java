package at.ac.tuwien.client;

import at.ac.tuwien.dtos.Notification;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class NotificationClient {
    private String endpoint;

    public NotificationClient(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void send(Notification notification) {
        HttpEntity<Notification> entity = new HttpEntity(notification);
        ResponseEntity<?> response = new RestTemplate().postForEntity(this.endpoint, entity, null);
    }
}
