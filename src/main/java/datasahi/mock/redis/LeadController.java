package datasahi.mock.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lead")
public class LeadController {
    @Autowired
    private LeadService leadService;
    @Autowired
    private LoadService loadService;

    @PostMapping("/save")
    public CustomerLead savePerson(@RequestBody CustomerLead customerLead) {
        return leadService.save(customerLead);
    }

    @GetMapping("/get/{id}")
    public Optional<CustomerLead> getPerson(@PathVariable String id) {
        return leadService.getById(id);
    }

    @PostMapping("/simulate")
    public LoadResponse simulate(@RequestBody LoadRequest loadRequest) {
        return loadService.simulate(loadRequest);
    }

}
