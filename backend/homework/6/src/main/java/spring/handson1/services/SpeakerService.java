package spring.handson1.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import spring.handson1.entity.Speaker;


/**
 * SpeakerService class managed by spring.
 * It has functions which returns objects of different speaker brands
 */
@Service
public class SpeakerService {

    /**
     * Creates speaker object for sony and returns it
     * @return Speaker
     */
    @Bean
    public Speaker generateSonySpeaker() {
        Speaker speaker = new Speaker();
        speaker.setBrand("Sony");
        speaker.setPrice(2000);
        return speaker;
    }

    /**
     * Creates speaker object for Bose and returns it
     * @return Speaker
     */
    @Bean
    public Speaker generateBoseSpeaker() {
        Speaker speaker = new Speaker();
        speaker.setBrand("Bose");
        speaker.setPrice(3000);
        return speaker;
    }
}
