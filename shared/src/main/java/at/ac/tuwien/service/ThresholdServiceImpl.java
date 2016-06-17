package at.ac.tuwien.service;

import at.ac.tuwien.client.NotificationClient;
import at.ac.tuwien.dao.ThresholdRepository;
import at.ac.tuwien.domain.Threshold;
import at.ac.tuwien.dtos.ThresholdNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
public class ThresholdServiceImpl implements ThresholdService {
    @Autowired
    private ThresholdRepository thresholdRepository;
    @Value("${service.name}")
    private String serviceName;
    @Value("${service.responsible}")
    private String serviceResponsible;
    @Value("${notificationServiceEndpoint}")
    private String notificationServiceEndpoint;
    private NotificationClient notificationClient;

    @PostConstruct
    private void init() {
        notificationClient = new NotificationClient(notificationServiceEndpoint);
    }

    private void notifiy() {
        notificationClient.send(new ThresholdNotification(serviceResponsible, serviceName, false));
    }

    @Override
    public void performRequest() {
        Threshold t = thresholdRepository.findByService(serviceName);
        if (t == null) {
            t = new Threshold();
            t.setService(serviceName);
            t.setRequests(0);
            t.setThreshold(0);
            t.setLastUpdate(LocalDateTime.now());
            t.setNotified(false);
        }

        if (LocalDateTime.now().minusHours(24).isAfter(t.getLastUpdate())) {
            t.setRequests(1);
        } else {
            t.setRequests(t.getRequests() + 1);
        }
        t.setLastUpdate(LocalDateTime.now());

        if (t.getRequests() >= t.getThreshold() && !t.getNotified()) {
            notifiy();
            t.setNotified(true);
        }

        if (t.getThreshold() == 0) {
            try {
                thresholdRepository.insert(t);
            } catch(Exception e) {
                //was already inserted
            }
        } else {
            thresholdRepository.save(t);
        }
    }
}
