package at.ac.tuwien.controller;

import at.ac.tuwien.exception.NotAuthorizedException;
import at.ac.tuwien.exception.PathNotFoundException;
import at.ac.tuwien.exception.UsageException;
import at.ac.tuwien.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping(value = "/data")
public class ApiController extends AbstractApiController {
    @Autowired
    private ApiService service;

    @RequestMapping(value = "/{path}", produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String get(@PathVariable String path) throws PathNotFoundException, NotAuthorizedException,
            UsageException, UnsupportedEncodingException {
        return service.handle(path, "");
    }

    @RequestMapping(value = "/{path}/{request:.*}", produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String get(@PathVariable String path, @PathVariable String request)
            throws UnsupportedEncodingException, PathNotFoundException, NotAuthorizedException, UsageException {
        request = super.decodeQuery(request);
        return service.handle(path, request);
    }
}
