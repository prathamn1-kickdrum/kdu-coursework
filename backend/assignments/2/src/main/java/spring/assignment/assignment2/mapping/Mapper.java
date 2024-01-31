package spring.assignment.assignment2.mapping;

import spring.assignment.assignment2.dto.response.ResponseForwardGeocoding;
import spring.assignment.assignment2.dto.response.ResponseReverseGeocoding;
import spring.assignment.assignment2.entity.LocationEntity;

public class Mapper {

    private Mapper() {
    }

    /**
     *convert location entity to response for forward geocoding
     * @param location
     * @return
     */
    public static ResponseForwardGeocoding convertToForwardResponse(LocationEntity location){
        return new ResponseForwardGeocoding(location.getLatitude(), location.getLongitude());
    }

    /**
     *convert location entity to response for reverse geocoding
     * @param location
     * @return
     */
    public static ResponseReverseGeocoding convertToReverseResponse(LocationEntity location){
        return new ResponseReverseGeocoding(location.getLabel());
    }

    public static Mapper createMapper() {
        return new Mapper();
    }
}
