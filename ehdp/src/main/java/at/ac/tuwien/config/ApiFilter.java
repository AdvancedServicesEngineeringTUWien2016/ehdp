package at.ac.tuwien.config;

import at.ac.tuwien.filter.AbstractApiFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ApiFilter extends AbstractApiFilter {
    @PostConstruct
    private void init() {
        super.setPrefix("/api/");
        super.setForward("/data/");
        super.setKeepNPathVariables(1);
    }
}
