package spring.handson.model.speaker;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * class for sony speaker : following open-closed principle
 */
@Component
@Scope("singleton")
@Qualifier("sonySpeaker")
public class SonySpeaker extends AbstractSpeaker {
    public SonySpeaker() {
        super(1500, "Sony");
    }
}
