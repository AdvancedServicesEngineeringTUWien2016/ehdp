package at.ac.tuwien.service;

public interface WeatherService {
    /**
     * Parses a request and performs this request
     * @param request for the weatherData
     * @return the requested data
     */
    String get(String request);
}
