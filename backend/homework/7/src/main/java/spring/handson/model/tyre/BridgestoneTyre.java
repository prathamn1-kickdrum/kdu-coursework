package spring.handson.model.tyre;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * class for bridgestone tyre : following open-closed principle
 */
@Component
@Scope("prototype")
@Qualifier("bridgestoneTyre")
public class BridgestoneTyre extends AbstractTyre {
    protected BridgestoneTyre() {
        super(1000, "Bridgestone");
    }
}
