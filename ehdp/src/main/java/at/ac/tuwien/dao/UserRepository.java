package at.ac.tuwien.dao;

import at.ac.tuwien.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends MongoRepository<User, String> {
    User getByEmail(String email);
    User getByApiKey(String apiKey);
}
