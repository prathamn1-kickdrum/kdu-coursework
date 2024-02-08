package com.kdu.smarthome.repository;


import com.kdu.smarthome.entity.KickstonSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KickstonSequenceRepository extends JpaRepository<KickstonSequence, Long> {

    @Query(value = "SELECT nextval('kickston_sequence')", nativeQuery = true)
    Long getNextSequenceValue();
}