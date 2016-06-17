package at.ac.tuwien.dtos;

import at.ac.tuwien.domain.PaymentModel;
import at.ac.tuwien.domain.User;

import java.util.List;

public class Profile {
    private String userId;
    private String email;
    private String firstname;
    private String lastname;
    private String apiKey;
    private PaymentModel paymentModel;

    public Profile() { }
    public Profile(User u) {
        userId = u.getUserId();
        email = u.getEmail();
        firstname = u.getFirstname();
        lastname = u.getLastname();
        apiKey = u.getApiKey();
        paymentModel = u.getPaymentModel();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public PaymentModel getPaymentModel() {
        return paymentModel;
    }

    public void setPaymentModel(PaymentModel paymentModel) {
        this.paymentModel = paymentModel;
    }
}
