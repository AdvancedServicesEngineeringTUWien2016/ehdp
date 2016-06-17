package at.ac.tuwien.service;

public interface TwitterService {
    /**
     * Parses a request and performs this request
     * @param request for the twitter data
     * @return the requested data
     */
    String get(String request);
}
