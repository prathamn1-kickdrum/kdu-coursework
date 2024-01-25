package spring.handson.model.speaker;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * class for bose speaker : following open-closed principle
 */

@Component
@Scope("singleton")
@Qualifier("boseSpeaker")
public class BoseSpeaker extends AbstractSpeaker {
    public BoseSpeaker() {
        super(1000, "Bose");
    }
}
