package at.ac.tuwien.dao;

import at.ac.tuwien.domain.PaymentModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface PaymentModelRepository extends MongoRepository<PaymentModel, String> { }