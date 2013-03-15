package fi.jpalomaki.app.domain

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

class Domain {

    @NotEmpty    
    String name
    
    @NotNull
    Boolean reserved
    
}
