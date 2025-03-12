package datasahi.mock.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    public CustomerLead save(CustomerLead customerLead) {
        Optional<CustomerLead> current = getById(customerLead.getId());
        current.ifPresent(value -> customerLead.setCreatedTs(value.getCreatedTs()));
        customerLead.setUpdatedTs(LocalDateTime.now());
        if (customerLead.getCreatedTs() == null) {
            customerLead.setCreatedTs(customerLead.getUpdatedTs());
        }
        return leadRepository.save(customerLead);
    }

    public void saveAll(List<CustomerLead> customerLeads) {
        leadRepository.saveAll(customerLeads);
    }

    public Optional<CustomerLead> getById(String id) {
        return leadRepository.findById(id);
    }

    public List<CustomerLead> fetch(int count) {
        if (count == 0) return Collections.emptyList();
        Iterable<CustomerLead> iter = leadRepository.findAll();
        List<CustomerLead> records = StreamSupport.stream(iter.spliterator(), false)
                .collect(Collectors.toList());
        return (records.size() <= count) ? records : records.subList(0, count);
    }
}
