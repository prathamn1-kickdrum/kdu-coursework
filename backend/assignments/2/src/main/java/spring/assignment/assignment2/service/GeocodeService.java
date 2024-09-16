package spring.assignment.assignment2.service;

import spring.assignment.assignment2.dto.request.RequestReverseGeoCodingDto;
import spring.assignment.assignment2.dto.response.ResponseForwardGeocoding;
import spring.assignment.assignment2.dto.response.ResponseReverseGeocoding;
import spring.assignment.assignment2.entity.LocationEntity;
import spring.assignment.assignment2.mapping.Mapper;
import spring.assignment.assignment2.repository.ForwardLocationRepository;
import spring.assignment.assignment2.repository.ReverseLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeocodeService {

    private ForwardLocationRepository forwardLocationRepository;
    private ReverseLocationRepository reverseLocationRepository;

    /**
     *
     * @param forwardLocationRepository
     * @param reverseLocationRepository
     */
    @Autowired
    public GeocodeService(ForwardLocationRepository forwardLocationRepository, ReverseLocationRepository reverseLocationRepository){
        this.reverseLocationRepository = reverseLocationRepository;
        this.forwardLocationRepository = forwardLocationRepository;
    }

    /**
     *
     * @param address
     * @return
     */
    public ResponseForwardGeocoding getLatitudeLongitude(String address){
        LocationEntity current = forwardLocationRepository.getCoordinates(address);
        return Mapper.convertToForwardResponse(current);
    }

    /**
     *
     * @param requestReverseGeoCodingDto
     * @return
     */
    public ResponseReverseGeocoding getAddressDetails(RequestReverseGeoCodingDto requestReverseGeoCodingDto){
        LocationEntity current = reverseLocationRepository.getAddressLabel(requestReverseGeoCodingDto.getLatitude(),requestReverseGeoCodingDto.getLongitude());
        return Mapper.convertToReverseResponse(current);
    }
}
