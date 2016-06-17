package at.ac.tuwien.controller;

import at.ac.tuwien.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
public class WeatherController extends AbstractApiController {
    @Autowired
    private WeatherService weatherService;

    @RequestMapping(value = "/{request:.+}", produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String get(@PathVariable String request) throws UnsupportedEncodingException {
        request = super.decodeQuery(request);
        String result = weatherService.get(request);
        return result;
    }
}
