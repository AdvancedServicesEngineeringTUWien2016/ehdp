package at.ac.tuwien.dtos;

public class RequestDetails {
    private Integer possibleRequests;
    private Integer madeRequests;

    public RequestDetails() { }
    public RequestDetails(Integer possibleRequests, Integer madeRequests) {
        this.possibleRequests = possibleRequests;
        this.madeRequests = madeRequests;
    }

    public Integer getPossibleRequests() {
        return possibleRequests;
    }

    public void setPossibleRequests(Integer possibleRequests) {
        this.possibleRequests = possibleRequests;
    }

    public Integer getMadeRequests() {
        return madeRequests;
    }

    public void setMadeRequests(Integer madeRequests) {
        this.madeRequests = madeRequests;
    }
}
