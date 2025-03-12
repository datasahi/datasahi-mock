package datasahi.mock.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends CrudRepository<CustomerLead, String> {
}
