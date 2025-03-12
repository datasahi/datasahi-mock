package datasahi.mock.redis;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CustomerLeadGenerator {

    // Sample data for random generation
    private static final String[] FIRST_NAMES = {
            "James", "Mary", "John", "Patricia", "Robert", "Jennifer", "Michael", "Linda",
            "William", "Elizabeth", "David", "Barbara", "Richard", "Susan", "Joseph", "Jessica",
            "Thomas", "Sarah", "Charles", "Karen", "Christopher", "Nancy", "Daniel", "Lisa",
            "Matthew", "Betty", "Anthony", "Margaret", "Donald", "Sandra", "Mark", "Ashley"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
            "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
            "Thomas", "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson", "White"
    };

    public List<CustomerLead> generate(int count) {
        final Random random = new Random();
        List<CustomerLead> records = new ArrayList<>(count);
        long sequence = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            CustomerLead lead = new CustomerLead();
            lead.setId(String.format("LEAD_%13d", sequence++));
            String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
            lead.setName(firstName + " " + lastName);
            lead.setAge(random.nextInt(63) + 18);
            lead.setStatus("SUBMITTED");
            records.add(lead);
        }

        records.forEach(r -> {
            LocalDateTime now = LocalDateTime.now();
            r.setCreatedTs(now).setUpdatedTs(now);
        });
        return records;
    }
}
