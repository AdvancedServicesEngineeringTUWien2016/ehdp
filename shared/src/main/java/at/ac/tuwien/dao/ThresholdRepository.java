package at.ac.tuwien.dao;

import at.ac.tuwien.domain.Threshold;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ThresholdRepository extends MongoRepository<Threshold, String> {
    Threshold findByService(String service);
}
