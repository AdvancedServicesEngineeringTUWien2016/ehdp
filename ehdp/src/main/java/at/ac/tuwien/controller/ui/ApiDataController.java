package at.ac.tuwien.controller.ui;

import at.ac.tuwien.dao.ApiRepository;
import at.ac.tuwien.domain.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apidata")
public class ApiDataController {
    @Autowired
    private ApiRepository apiRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Api> getApis() {
        return apiRepository.findAll();
    }

    @RequestMapping(value = "/{path}", method = RequestMethod.GET)
    public Api getApi(@PathVariable String path) {
        return apiRepository.findByPath(path);
    }
}
