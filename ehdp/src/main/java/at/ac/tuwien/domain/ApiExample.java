package at.ac.tuwien.domain;

public class ApiExample {
    private String request;
    private String description;

    public ApiExample() { }
    public ApiExample(String request, String description) {
        this.request = request;
        this.description = description;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
