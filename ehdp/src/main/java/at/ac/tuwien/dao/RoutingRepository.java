package at.ac.tuwien.dao;

import at.ac.tuwien.domain.Routing;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoutingRepository extends MongoRepository<Routing, String> {
    @Cacheable
    Routing getByPath(String path);
}
