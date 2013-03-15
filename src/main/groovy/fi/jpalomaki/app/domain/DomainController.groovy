package fi.jpalomaki.app.domain

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.web.bind.annotation.RequestMethod.*
import static org.springframework.http.HttpStatus.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
class DomainController {
    
    final List<Domain> domains = new CopyOnWriteArrayList<Domain>()
    
    @RequestMapping(value = "/domains", method = GET)
    def @ResponseBody List<Domain> getAllDomains() {
        Collections.unmodifiableList(domains)
    }
    
    @ResponseStatus(NO_CONTENT)
    @RequestMapping(value = "/domains", method = POST)
    def void addDomain(@Valid @RequestBody Domain domain) {
        domains.add(domain)
    }
}
