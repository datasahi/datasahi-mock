package datasahi.mock.redis;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadService {

    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private CustomerLeadGenerator generator;

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(LoadService.class);

    public LoadResponse simulate(LoadRequest loadRequest) {
        LOG.info("Simulating load with request: {}", loadRequest);
        LoadResponse response = new LoadResponse(loadRequest);
        for (int i = 0; i < loadRequest.getTimes(); i++) {
            LOG.info("Simulating load iteration: {}", i);
            try {
                long start = System.currentTimeMillis();
                List<CustomerLead> records = generator.generate(loadRequest.getBatchSize());
                int generatorMillis = (int) (System.currentTimeMillis() - start);

                start = System.currentTimeMillis();
                leadRepository.saveAll(records);
                int redisMillis = (int) (System.currentTimeMillis() - start);

                Thread.sleep(loadRequest.getSleepMillis());

                response.setTotalCount(response.getTotalCount() + records.size());
                response.setSleepMilis(response.getSleepMilis() + loadRequest.getSleepMillis());
                response.setGeneratorMilis(response.getGeneratorMilis() + generatorMillis);
                response.setRedisMilis(response.getRedisMilis() + redisMillis);
            } catch (InterruptedException e) {
                LOG.error("Error while simulating load", e);
            }
        }
        LOG.info("Done with load test : {}", response);
        return response;
    }
}
