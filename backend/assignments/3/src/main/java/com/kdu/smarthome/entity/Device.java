package com.kdu.smarthome.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name="devices")
public class Device {
    @Id
    @Column(length = 6)
    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;
    private String manufactureDateTime;
    private String manufactureFactoryPlace;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
