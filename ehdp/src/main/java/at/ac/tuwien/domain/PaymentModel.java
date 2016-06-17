package at.ac.tuwien.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PaymentModel {
    @Id
    private String modelId;
    private Integer requests;
    private Double price;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public Integer getRequests() {
        return requests;
    }

    public void setRequests(Integer requests) {
        this.requests = requests;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        price = price;
    }
}
