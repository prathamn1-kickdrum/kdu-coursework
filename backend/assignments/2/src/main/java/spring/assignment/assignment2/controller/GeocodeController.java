package spring.assignment.assignment2.controller;

import spring.assignment.assignment2.dto.request.RequestReverseGeoCodingDto;
import spring.assignment.assignment2.dto.response.ResponseForwardGeocoding;
import spring.assignment.assignment2.dto.response.ResponseReverseGeocoding;
import spring.assignment.assignment2.service.GeocodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeocodeController {
    private GeocodeService geocodeService;

    /**
     *
     * @param geocodeService
     */
    @Autowired
    public GeocodeController(GeocodeService geocodeService){
        this.geocodeService = geocodeService;
    }

    /**
     *
     * @param address@Cacheable(cacheNames = "reverse-geocoding",key="{#latitude,#longitude}")
     * @return
     */
    @GetMapping("/geocoding")
    public ResponseEntity<ResponseForwardGeocoding> getCoordinates(@RequestParam String address){
        ResponseForwardGeocoding responseForwardGeocoding = geocodeService.getLatitudeLongitude(address);
        return new ResponseEntity<>(responseForwardGeocoding, HttpStatus.OK);
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @return
     */
    @GetMapping("/reverse-geocoding")
    public ResponseEntity<String> getAddress(@RequestParam String latitude, @RequestParam String longitude){
        RequestReverseGeoCodingDto requestReverseGeoCoding = new RequestReverseGeoCodingDto(latitude,longitude);
        ResponseReverseGeocoding responseReverseGeocoding = geocodeService.getAddressDetails(requestReverseGeoCoding);
        return new ResponseEntity<>(responseReverseGeocoding.getAddress(),HttpStatus.OK);
    }
}
