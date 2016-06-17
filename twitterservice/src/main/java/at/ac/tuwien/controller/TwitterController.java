package at.ac.tuwien.controller;

import at.ac.tuwien.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
public class TwitterController extends AbstractApiController {
    @Autowired
    private TwitterService twitterService;

    @RequestMapping(value = "/{request:.+}", produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String get(@PathVariable String request) throws UnsupportedEncodingException {
        request = super.decodeQuery(request);
        String result = twitterService.get(request);
        return result;
    }
}
