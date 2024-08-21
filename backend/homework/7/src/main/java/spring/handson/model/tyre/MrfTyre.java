package spring.handson.model.tyre;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * class for mrf tyre : following open-closed principle
 */
@Component
@Scope("prototype")
@Qualifier("mrfTyre")
public class MrfTyre extends AbstractTyre {
    protected MrfTyre() {
        super(1500, "MRF");
    }
}
