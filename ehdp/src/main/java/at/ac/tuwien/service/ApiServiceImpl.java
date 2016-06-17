package at.ac.tuwien.service;

import at.ac.tuwien.dao.RoutingRepository;
import at.ac.tuwien.dao.UsageRepository;
import at.ac.tuwien.dao.UserRepository;
import at.ac.tuwien.domain.Routing;
import at.ac.tuwien.domain.Usage;
import at.ac.tuwien.domain.User;
import at.ac.tuwien.exception.NotAuthorizedException;
import at.ac.tuwien.exception.PathNotFoundException;
import at.ac.tuwien.exception.UsageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private RoutingRepository routingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UsageRepository usageRepository;
    @Autowired
    private HttpServletRequest servletRequest;

    private void doAuthentication(String path, String request) throws NotAuthorizedException, UsageException {
        String authorization = servletRequest.getHeader("authorization");

        if (authorization == null) {
            throw new NotAuthorizedException("No authorization header");
        }

        User u = userRepository.getByApiKey(authorization);

        if (u == null) {
            throw new NotAuthorizedException("Authorization not recognized!");
        }

        LocalDateTime last24Hours = LocalDateTime.now().minusHours(24);

        List<Usage> usages = usageRepository.findByApiKeyAndTimestamp(authorization, last24Hours);

        if (u.getPaymentModel().getRequests() <= usages.size()) {
            throw new UsageException("Too many requests within the last 24 hours");
        }

        Usage usage = new Usage();
        usage.setPath(path);
        usage.setApikey(authorization);
        usage.setRequest(request);
        usage.setTimestamp(LocalDateTime.now());

        usageRepository.insert(usage);
    }

    @Override
    public String handle(String path, String request) throws PathNotFoundException, NotAuthorizedException, UsageException, UnsupportedEncodingException {
        Routing r = routingRepository.getByPath(path);

        if (r == null) {
            throw new PathNotFoundException("Path " + path + " was not found!");
        }

        doAuthentication(path, request);

        request = URLEncoder.encode(request, "UTF-8");
        request = r.getRoute() + request;

        RestTemplate template = new RestTemplate();
        String result = template.getForObject(request, String.class);
        return result;
    }
}
