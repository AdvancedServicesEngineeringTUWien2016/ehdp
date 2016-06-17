package at.ac.tuwien.dao;

import at.ac.tuwien.domain.Usage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface UsageRepository extends MongoRepository<Usage, String> {

    @Query("{ 'apikey' : ?0, 'timestamp' : { $gte : ?1 } }")
    List<Usage> findByApiKeyAndTimestamp(String apiKey, LocalDateTime date);
}
