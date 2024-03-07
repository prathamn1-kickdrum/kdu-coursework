package com.kdu.smarthome.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="devices")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Device {
    @Id
//    @Column(length = 6)
    @JsonProperty("id")
    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;
    private String manufactureDateTime;
    private String manufactureFactoryPlace;
    private boolean registered;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;


    @Override
    public String toString() {
        return "Device{" +
                "kickstonId='" + kickstonId + '\'' +
                ", deviceUsername='" + deviceUsername + '\'' +
                ", devicePassword='" + devicePassword + '\'' +
                ", manufactureDateTime='" + manufactureDateTime + '\'' +
                ", manufactureFactoryPlace='" + manufactureFactoryPlace + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(kickstonId, device.kickstonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kickstonId);
    }
}
