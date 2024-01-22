package spring.handson1.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import spring.handson1.entity.Tyre;

/**
 * TyreService class managed by spring and also has two Bean objects
 * It has functions which returns different tyre objects
 */
@Service
public class TyreService {

    /**
     * function for creating tyre object for MRF brand and returning it
     * @return Tyre
     */
    @Bean
    Tyre generateMrfTyre() {
        Tyre tyre = new Tyre();
        tyre.setBrand("MRF");
        tyre.setPrice(1000);
        return tyre;
    }

    /**
     * function for creating tyre object for bridgestone brand and returning it
     * @return Tyre
     */
    @Bean
    Tyre generateBridgestoneTyre() {
        Tyre tyre = new Tyre();
        tyre.setBrand("Bridgestone");
        tyre.setPrice(1500);
        return tyre;
    }
}
