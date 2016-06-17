package at.ac.tuwien.dao;

import at.ac.tuwien.domain.Api;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ApiRepository extends MongoRepository<Api, String> {
    Api findByPath(String path);
}