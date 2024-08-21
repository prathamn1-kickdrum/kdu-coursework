package spring.handson.service.speaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.handson.model.speaker.BoseSpeaker;
import spring.handson.model.speaker.SonySpeaker;
import spring.handson.model.speaker.AbstractSpeaker;

import java.util.Random;

/**
 * class for generating speaker objects
 */
@Service
public class SpeakerService {
    private final SonySpeaker sony;
    private final BoseSpeaker bose;
    Random rand;

    @Autowired
    public SpeakerService(SonySpeaker sony, BoseSpeaker bose) {
        this.sony=sony;
        this.bose=bose;
        rand= new Random();
    }

    public SonySpeaker generateSonySpeaker() {
        return sony;
    }
    public BoseSpeaker generateBoseSpeaker() {
        return bose;
    }

    /**
     * generates random speaker
     * @return bose or sony speaker
     */
    public AbstractSpeaker generateRandomSpeaker() {
        if(rand.nextInt()%2==0) {
            return sony;
        }else {
            return bose;
        }
    }
}
