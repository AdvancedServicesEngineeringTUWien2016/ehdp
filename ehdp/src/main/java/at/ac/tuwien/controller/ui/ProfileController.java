package at.ac.tuwien.controller.ui;

import at.ac.tuwien.client.NotificationClient;
import at.ac.tuwien.dao.PaymentModelRepository;
import at.ac.tuwien.dao.UsageRepository;
import at.ac.tuwien.dao.UserRepository;
import at.ac.tuwien.domain.PaymentModel;
import at.ac.tuwien.domain.Usage;
import at.ac.tuwien.domain.User;
import at.ac.tuwien.dtos.Notification;
import at.ac.tuwien.dtos.PaymentModelNotification;
import at.ac.tuwien.dtos.RequestDetails;
import at.ac.tuwien.exception.NotAuthorizedException;
import at.ac.tuwien.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentModelRepository paymentModelRepository;
    @Autowired
    private UsageRepository usageRepository;
    @Autowired
    private MongoOperations mongoOperations;
    @Value("${notificationServiceEndpoint}")
    private String notificationServiceEndpoint;
    private NotificationClient notificationClient;

    @PostConstruct
    private void init() {
        notificationClient = new NotificationClient(notificationServiceEndpoint);
    }

    private String getLoggedInUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    private User getLoggedInUser() throws NotAuthorizedException {
        String name = getLoggedInUsername();
        User u = userRepository.getByEmail(name);

        if (u == null) {
            throw new NotAuthorizedException("Logged in user was not found!");
        }

        return u;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public User getProfile() {
        String name = getLoggedInUsername();
        return userRepository.getByEmail(name);
    }

    @RequestMapping(value = "/requests/{offset}", method = RequestMethod.GET)
    public List<Usage> getLastRequests(@PathVariable int offset) throws NotAuthorizedException {
        User u = getLoggedInUser();

        Criteria criteria = Criteria.where("apikey").is(u.getApiKey());
        Query query = Query.query(criteria);
        query.limit(20);
        query.skip(offset);
        query.with(new Sort(Sort.Direction.DESC, "timestamp"));

        return mongoOperations.find(query, Usage.class);
    }

    @RequestMapping(value = "/requests/details", method = RequestMethod.GET)
    public RequestDetails getRequestDetails() throws NotAuthorizedException {
        User u = getLoggedInUser();

        LocalDateTime last24Hours = LocalDateTime.now().minusHours(24);
        int usages = usageRepository.findByApiKeyAndTimestamp(u.getApiKey(), last24Hours).size();
        int possible = u.getPaymentModel().getRequests();
        if (usages > possible) {
            //e.g. when a user has changed his model
            usages = possible;
        }

        return new RequestDetails(possible, usages);
    }

    @RequestMapping(value = "/paymentModels", method = RequestMethod.GET)
    public List<PaymentModel> getPaymentModels() {
        return paymentModelRepository.findAll();
    }

    @RequestMapping(value = "/paymentModels", method = RequestMethod.POST)
    public void changePaymentModel(@RequestBody PaymentModel paymentModel)
            throws NotAuthorizedException, ValidationException {
        User u = getLoggedInUser();
        PaymentModel m = paymentModelRepository.findOne(paymentModel.getModelId());

        if (m == null) {
            throw new ValidationException("Payment model is invalid");
        }

        u.setPaymentModel(m);

        userRepository.save(u);

        Notification n = new PaymentModelNotification(
                u.getEmail(),
                u.getFirstname() + " " + u.getLastname(),
                m.getRequests(),
                m.getPrice());

        notificationClient.send(n);
    }
}
