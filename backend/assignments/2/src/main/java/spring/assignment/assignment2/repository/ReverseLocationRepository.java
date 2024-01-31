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
public class ReverseLocationRepository {
    @Value("${api-key}")
    private String apiKey;
    private static final String URLAPI = "http://api.positionstack.com/v1/reverse";
    private final RestTemplate restTemplate = new RestTemplate();

    private static final Logger logger = LoggerFactory.getLogger(ReverseLocationRepository.class);

    /**
     *
     * @param latitude
     * @param longitude
     * @return
     */
    @Cacheable(cacheNames = "reverse-geocoding",key="{#latitude,#longitude}")
    public LocationEntity getAddressLabel(String latitude, String longitude){
        if(checkCoordinates(latitude,longitude)){
            String url = URLAPI + "?access_key=" + apiKey + "&query=" + latitude + "," + longitude;
            JsonNode response = restTemplate.getForObject(url, JsonNode.class);
            if (response == null) {
                throw new LocationNotFoundException("Error while fetching location in reverse geocoding");
            }
            return covertResponsetoLocation(response);
        }else{
            throw new InvalidInputProvidedException("Invalid Coordinates provide");
        }
    }

    /**
     *this method parses the Json respone.
     * @param response
     * @return
     */
    public LocationEntity covertResponsetoLocation(JsonNode response) {
        try {
            if(response.get("message") != null){
                throw new InvalidInputProvidedException("Invalid input provided");
            }
            JsonNode data = response.get("data").get(0);
            double latitude = data.get("latitude").asDouble();
            double longitude = data.get("longitude").asDouble();
            String label = data.get("label").asText();
            String postalCode = data.get("postal_code").asText();
            String region = data.get("region").asText();
            int number = data.get("number").asInt();
                logger.info("{}{}",label,latitude);
                logger.info("Successfully parsed reponse");
                logger.info("Given latitude : {}, Given Longitude : {} --> Address = {}",latitude,longitude,label);
                return new LocationEntity(label, latitude, longitude, postalCode,region,number);
        } catch (Exception e) {
            throw new LocationNotFoundException("Error while fetching and parsing Reverse Geocoding");
        }
    }

    /**
     * this method checks if the user input is right or wrong
     * @param lat
     * @param longi
     * @return
     */
    public boolean checkCoordinates(String lat, String longi){
        try{
            Double latitude = Double.parseDouble(lat);
            Double longitude = Double.parseDouble(longi);
            if((latitude <= 90.0 && latitude >= -90.0) && (longitude <= 180.0 && longitude >= -180.0)){
               return true;
            }else {
                throw  new InvalidInputProvidedException("The coordinate value is invalid");
            }

        }catch (NumberFormatException e){
            throw new InvalidInputProvidedException("String provided in coordinates");
        }
    }
}
