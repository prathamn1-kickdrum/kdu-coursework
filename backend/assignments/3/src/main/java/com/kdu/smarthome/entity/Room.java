package com.kdu.smarthome.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name="rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private long roomId;
    private String roomName;


    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;


    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private Set<Device> devices;

    @Override
    public String toString() {
        return "Room{" +
                "roomId= " + roomId +
                "roomName= "+ roomName +
                "Devices{" +
                devices.toString()+
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return Objects.equals(roomId, room.roomId);
    }

}
