package at.ac.tuwien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceOpenWeatherMap implements WeatherService {
    @Value("${apiKey}")
    private String apiKey;

    @Value("${openWeatherPath}")
    private String openWeatherPath;

    @Autowired
    private ThresholdService thresholdService;
    private String serviceName = "weather";

    @Override
    public String get(String request) {
        thresholdService.performRequest();

        request = openWeatherPath + request + "&appid=" + apiKey;

        RestTemplate template = new RestTemplate();
        String result = template.getForObject(request, String.class);
        return result;
    }
}
