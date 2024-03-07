package com.kdu.smarthome.service.impl;

import com.kdu.smarthome.repository.KickstonSequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KickstonSequenceServiceImpl {
    private KickstonSequenceRepository sequenceRepository;
    public String generateKickstonId() {
        Long nextSequenceValue = sequenceRepository.getNextSequenceValue();
        String hexadecimalString = Long.toHexString(nextSequenceValue).toUpperCase();
        return String.format("%6s", hexadecimalString).replace(' ', '0');
    }
}
