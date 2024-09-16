package spring.assignment.assignment2.repository;

import spring.assignment.assignment2.entity.LocationEntity;
import spring.assignment.assignment2.exceptions.custom.InvalidInputProvidedException;
import spring.assignment.assignment2.exceptions.custom.LocationNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Repository
public class ForwardLocationRepository {
    @Value("${api-key}")
    private String apiKey;
    private static final String URLAPI = "http://api.positionstack.com/v1/forward?";
    private final RestTemplate callAPI = new RestTemplate();
    private static final Logger logger = LoggerFactory.getLogger(ForwardLocationRepository.class);

    /**
     *
     * @param address
     * @return
     */
    @Cacheable(cacheNames = "geocoding", key = "#address", unless = "#address.toLowerCase().contains('goa')")
    public LocationEntity getCoordinates(String address){
        String url = URLAPI +"access_key="+apiKey+"&query="+address;
        JsonNode apiResponse = callAPI.getForObject(url,JsonNode.class);
        if(apiResponse == null){
            throw new LocationNotFoundException("The api response is null. Could not fetch the location");
        }
        return covertResponsetoLocation(apiResponse);
    }

    /**
     *
     * @param response
     * @return
     */
    public LocationEntity covertResponsetoLocation(JsonNode response) {
        try {
            if(response.get("message") != null){
                throw new InvalidInputProvidedException("Invalid input provided");
            }
            JsonNode results = response.get("data").get(0);
            double latitude = results.get("latitude").asDouble();
            double longitude = results.get("longitude").asDouble();
            String label = results.get("label").asText();
            String postalCode = results.get("postal_code").asText();
            String region = results.get("region").asText();
            int number = results.get("number").asInt();
            logger.info("Successfully parsed response");
            logger.info("Given Address = {} -->  latitude : {}, Given Longitude : {}", label, latitude, longitude);
            return new LocationEntity(label, latitude, longitude, postalCode, region,number);
        } catch (Exception e) {
            logger.error("Error parsing API response: {}", e.getMessage());
            throw new LocationNotFoundException("Error while Parsing the location");
        }
    }

}
