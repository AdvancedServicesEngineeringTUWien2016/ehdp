package at.ac.tuwien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public abstract class AbstractApiController {
    @Autowired
    private HttpServletRequest context;

    public String decodeQuery(String request) throws UnsupportedEncodingException {
        String x = context.getRequestURI();

        String queries = context.getQueryString();
        if (queries != null && queries.length() > 0) {
            request += "?" + queries;
        }

        request = URLDecoder.decode(request, "UTF-8");

        return request;
    }
}
